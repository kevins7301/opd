package com.iisi.opd.data.in.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.iisi.common.util.SqlParamUtil;
import com.iisi.opd.cfg.dao.DataCfgTableInfoDao;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataCfgTableInfoPo;
import com.iisi.opd.cfg.po.DataFieldCfgPo;
import com.iisi.opd.cfg.service.DataCfgService;
import com.iisi.opd.cfg.service.DataCfgTableInfoService;
import com.iisi.opd.data.in.dao.DataInDao;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@Repository
public class DataInDaoImpl extends JdbcDaoSupport implements DataInDao {
    @Autowired
    private Properties dbProperties;
    @Autowired
    private SqlParamUtil sqlParamUtil;
    @Autowired
    private Properties sqlTypeProperties;
    @Autowired
    private DataCfgService dataCfgService;
    @Autowired
    private DataCfgTableInfoService dataCfgTableInfoService;
    @Autowired
    private DataCfgTableInfoDao dataCfgTableInfoDao;

    @Autowired
    public DataInDaoImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void createTable(String json) throws OpdException {
        createTable(json, null);
    }

    @Override
    public void createTable(String json, DataInOptionsVo optionsVo) throws OpdException {
        JSONObject object = JSONObject.fromObject(json);
        String oid = object.getString("data_oid");
        JSONArray fields = object.getJSONArray("fields");

        boolean isTruncate = true;

        if (oid == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070001_EXCEPTION);
        }
        if ((fields == null) || (fields.size() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070002_EXCEPTION);
        }

        if (!isDataCfgExist(oid)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070003_EXCEPTION);
        }

        if ((optionsVo != null) && (optionsVo.isMatchFieldColumn()))
            checkFieldTitle(oid, json);
        boolean isCoverOldTitle = (optionsVo != null) && (optionsVo.isCoverOldTitle());
        try {
            DataCfgTableInfoPo dataCfgTableInfoPo = this.dataCfgTableInfoService.findByDataCfgOid(oid);
            if (dataCfgTableInfoPo == null) {
                isTruncate = false;
            } else {
                checkSameTitle(oid, json);
            }
        } catch (Exception e) {
            if (isTruncate) {
                isTruncate = false;
            }
        }
        String tableName = getTableName(oid, json, isCoverOldTitle);

        if (isTruncate) {
            getJdbcTemplate().execute("TRUNCATE TABLE " + tableName);
        } else {
            dropAndCreateTable(tableName, fields);
        }
    }

    private void dropAndCreateTable(String tableName, JSONArray fields) {
        Document hbm = DocumentHelper.createDocument();
        hbm.addDocType("hibernate-mapping", "-//Hibernate/Hibernate Mapping DTD 3.0//EN",
                "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd");
        Element mapping = hbm.addElement("hibernate-mapping");
        Element temp = mapping.addElement("class");
        temp.addAttribute("name", "");
        temp.addAttribute("table", "[" + tableName + "]");

        Element id = temp.addElement("id");
        id.addAttribute("name", "id");
        id.addAttribute("type", "int");
        id.addAttribute("column", "_id");
        Element generator = id.addElement("generator");
        generator.addAttribute("class", "native");

        for (int i = 0; i < fields.size(); i++) {
            JSONObject field = fields.getJSONObject(i);
            Object[] keys = field.keySet().toArray();
            for (int j = 0; j < keys.length; j++) {
                String key = (String) keys[j];
                if (!StringUtils.equals(key, "type")) {
                    String name = field.getString(key);
                    String type = field.getString("type");

                    Element property = temp.addElement("property");
                    property.addAttribute("name", "[" + name + "]");
                    property.addAttribute("type", type);

                    String sqlType = this.sqlTypeProperties.getProperty(type);
                    if (StringUtils.equals(sqlType, "")) {
                        property.addAttribute("column", "[" + name + "]");
                    } else {
                        Element column = property.addElement("column");
                        column.addAttribute("name", "[" + name + "]");
                        column.addAttribute("sql-type", this.sqlTypeProperties.getProperty(type));
                    }
                }
            }
        }

        Configuration conf = new Configuration();
        conf.addXML(hbm.asXML());

        StringBuilder dropScript = new StringBuilder();
        StringBuilder createScript = new StringBuilder();
        try {
            String[] dropTemp = conf.generateDropSchemaScript(Dialect.getDialect(this.dbProperties));
            for (int i = 0; i < dropTemp.length; i++) {
                dropScript.append(dropTemp[i]);
            }

            String[] createTemp = conf.generateSchemaCreationScript(Dialect.getDialect(this.dbProperties));
            for (int i = 0; i < createTemp.length; i++) {
                createScript.append(createTemp[i]);
            }
        } catch (HibernateException e) {
            throw new OpdException(ErrorCodeEnum.ERR_2070004_EXCEPTION);
        }

        try {
            getJdbcTemplate().execute(dropScript.toString());
        } catch (DataAccessException e) {
        }

        try {
            getJdbcTemplate().execute(createScript.toString());
        } catch (DataAccessException e) {
            throw new OpdException(ErrorCodeEnum.ERR_2070005_EXCEPTION);
        }
    }

    @Override
    public int insertData(String json) throws OpdException {
        return insertData(json, false);
    }

    @Override
    public int insertData(String json, boolean isRegularize) throws OpdException {
        JSONObject object = JSONObject.fromObject(json);
        String oid = object.getString("data_oid");
        JSONArray records = object.getJSONArray("records");

        if (oid == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070006_EXCEPTION);
        }
        if ((records == null) || (records.size() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070007_EXCEPTION);
        }

        if (!isDataCfgExist(oid)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070008_EXCEPTION);
        }

        Map<String, Object> columnMap = getTableParam(oid);
        if ((columnMap == null) || (columnMap.size() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070009_EXCEPTION);
        }
        if (!columnMap.containsKey("table_schema")) {
            throw new OpdException(ErrorCodeEnum.ERR_2070010_EXCEPTION);
        }
        if (!columnMap.containsKey("oid")) {
            throw new OpdException(ErrorCodeEnum.ERR_2070011_EXCEPTION);
        }

        String table = columnMap.get("table_name").toString();

        if (isRegularize) {
            return regularizeStructer(table, columnMap, records);
        }
        return nonRegularizeStructer(table, columnMap, records);
    }

    @Override
    public int updateData(String json) throws OpdException {
        int count = 0;
        JSONObject object = JSONObject.fromObject(json);
        String oid = object.getString("data_oid");
        JSONArray filters = object.getJSONArray("filters");
        JSONArray records = object.getJSONArray("records");

        if (oid == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070012_EXCEPTION);
        }
        if ((records == null) || (records.size() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070013_EXCEPTION);
        }
        if (filters == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070014_EXCEPTION);
        }

        if (records.size() != filters.size()) {
            throw new OpdException(ErrorCodeEnum.ERR_2070015_EXCEPTION);
        }

        if (!isDataCfgExist(oid)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070016_EXCEPTION);
        }

        Map<String, Object> columnMap = getTableParam(oid);
        if ((columnMap == null) || (columnMap.size() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070017_EXCEPTION);
        }
        if (!columnMap.containsKey("table_schema")) {
            throw new OpdException(ErrorCodeEnum.ERR_2070018_EXCEPTION);
        }
        if (!columnMap.containsKey("oid")) {
            throw new OpdException(ErrorCodeEnum.ERR_2070019_EXCEPTION);
        }

        String table = columnMap.get("table_name").toString();
        StringBuffer update = new StringBuffer("update \"" + table + "\" set ");
        Map<String, String> typeMap = getTypeMap((String) columnMap.get("table_schema"));

        for (int i = 0; i < records.size(); i++) {
            StringBuffer values = new StringBuffer();
            JSONObject record = records.getJSONObject(i);
            JSONObject filter = filters.getJSONObject(i);

            Object[] filterKeys = filter.keySet().toArray();
            Object[] recodrKeys = record.keySet().toArray();
            List<Object> paramList = new ArrayList();
            for (int j = 0; j < recodrKeys.length; j++) {
                String key = (String) recodrKeys[j];
                String value = record.getString(key);
                String type = (String) typeMap.get(key);
                Object param = this.sqlParamUtil.getParameter(value, type);
                paramList.add(param);

                values.append("\"" + key + "\"=?");
                if (j + 1 < recodrKeys.length) {
                    values.append(",");
                }
            }
            StringBuffer where = new StringBuffer(" where ");
            for (int j = 0; j < filterKeys.length; j++) {
                String key = (String) filterKeys[j];
                Object temp = filter.get(key);
                if (temp == JSONNull.getInstance()) {
                    where.append("\"" + key + "\" is null");
                } else {
                    String value = filter.getString(key);
                    String type = (String) typeMap.get(key);
                    Object param = this.sqlParamUtil.getParameter(value, type);
                    paramList.add(param);
                    where.append("\"" + key + "\"=?");
                }

                if (j + 1 < filterKeys.length)
                    values.append(",");
            }
            String sql = update.toString() + values.toString() + where.toString();

            count += getJdbcTemplate().update(sql, paramList.toArray(new Object[0]));
        }
        return count;
    }

    @Override
    public int deleteData(String json) throws OpdException {
        JSONObject object = JSONObject.fromObject(json);
        String oid = object.getString("data_oid");
        JSONObject filters = object.getJSONObject("filters");

        if (oid == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070020_EXCEPTION);
        }
        if (filters == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2070021_EXCEPTION);
        }

        if (!isDataCfgExist(oid)) {
            throw new OpdException(ErrorCodeEnum.ERR_2070022_EXCEPTION);
        }

        Map<String, Object> columnMap = getTableParam(oid);
        if ((columnMap == null) || (columnMap.size() == 0) || (!columnMap.containsKey("oid"))) {
            throw new OpdException(ErrorCodeEnum.ERR_2070023_EXCEPTION);
        }

        String table = columnMap.get("table_name").toString();
        StringBuffer sql = new StringBuffer("delete from \"" + table + "\"");
        Map<String, String> typeMap = getTypeMap((String) columnMap.get("table_schema"));

        List<Object> paramList = new ArrayList();
        Object[] keys = filters.keySet().toArray();
        StringBuffer values = new StringBuffer(" where ");
        for (int i = 0; i < keys.length; i++) {
            String key = (String) keys[i];
            Object temp = filters.get(key);
            if (temp == JSONNull.getInstance()) {
                values.append("\"" + keys[i] + "\" is null");
            } else {
                String value = filters.getString(key);
                String type = (String) typeMap.get(key);
                Object param = this.sqlParamUtil.getParameter(value, type);
                paramList.add(param);
                values.append("\"" + key + "\"=?");
            }

            if (i + 1 < keys.length)
                values.append(" and ");
        }
        if (keys.length > 0) {
            sql.append(values.toString());
        }
        return getJdbcTemplate().update(sql.toString(), paramList.toArray(new Object[0]));
    }

    @Override
    public int count(String oid) throws OpdException {
        Map<String, Object> columnMap = getTableParam(oid);
        if ((columnMap == null) || (columnMap.size() == 0) || (!columnMap.containsKey("oid"))) {
            throw new OpdException(ErrorCodeEnum.ERR_2070024_EXCEPTION);
        }
        String tableName = columnMap.get("table_name").toString();
        String sql = "select count(*) from \"" + tableName + "\"";
        return getJdbcTemplate().queryForInt(sql);
    }

    private boolean isDataCfgExist(String oid) {
        boolean isExist = true;
        String sql = "select count(oid) from od_data_cfg where oid = ?";
        int count = getJdbcTemplate().queryForInt(sql, new Object[] { oid });
        if (count == 0)
            isExist = false;
        return isExist;
    }

    private String getTableSchema(String oid) {
        String sqlSelectTableSchema = "select table_schema from od_data_cfg_table_info where od_data_cfg_oid = ?";
        Map<String, Object> columnMap = getJdbcTemplate().queryForMap(sqlSelectTableSchema, new Object[] { oid });
        return columnMap.get("table_schema").toString();
    }

    private void checkSameTitle(String oid, String json) {
        String oldSchema = getTableSchema(oid);
        JSONArray jsonArrOld = JSONObject.fromObject(oldSchema).getJSONArray("fields");
        JSONArray jsonArrNew = JSONObject.fromObject(json).getJSONArray("fields");

        if (jsonArrOld.size() != jsonArrNew.size()) {
            throw new OpdException(ErrorCodeEnum.ERR_2070026_EXCEPTION);
        }
        ListIterator listIterator = jsonArrNew.listIterator();

        while (listIterator.hasNext()) {
            String newObjName = JSONObject.fromObject(listIterator.next()).get("id").toString();
            boolean sameTitleHappen = false;
            for (int i = 0; (jsonArrOld.size() > 0) && (i < jsonArrOld.size()); i++) {
                String columnName = (String) JSONObject.fromObject(jsonArrOld.get(i)).get("id");

                if (columnName == null) {
                    throw new OpdException(ErrorCodeEnum.ERR_2070027_EXCEPTION);
                }
                if (newObjName.equals(columnName)) {
                    sameTitleHappen = true;
                    jsonArrOld.remove(i--);

                    i = jsonArrOld.size();
                }
            }
            if (!sameTitleHappen) {
                throw new OpdException(ErrorCodeEnum.ERR_2070027_EXCEPTION);
            }
        }
    }

    private void checkFieldTitle(String oid, String json) {
        Map<String, List<DataFieldCfgPo>> columnFiledCfgMap = new HashMap();
        DataCfgPo po = this.dataCfgService.findByOid(oid);

        List<DataFieldCfgPo> fieldList = po.getDataFieldCfgPoList();

        for (DataFieldCfgPo fieldPo : fieldList) {
            String key = fieldPo.getFieldName();
            List<DataFieldCfgPo> tempFieldList;
            if ((tempFieldList = (List) columnFiledCfgMap.get(key)) == null) {
                tempFieldList = new ArrayList();
                columnFiledCfgMap.put(key, tempFieldList);
            }
            tempFieldList.add(fieldPo);
        }

        JSONArray fields = JSONObject.fromObject(json).getJSONArray("fields");
        if (columnFiledCfgMap.size() != fields.size()) {
            throw new OpdException(ErrorCodeEnum.ERR_2070029_EXCEPTION);
        }
        for (int i = 0; i < fields.size(); i++) {
            String fieldName = fields.getJSONObject(i).getString("id");
            if (columnFiledCfgMap.get(fieldName) == null) {
                throw new OpdException(ErrorCodeEnum.ERR_2070030_EXCEPTION);
            }
        }
    }

    //    @Deprecated
    //    private String getCreateSequence(String oid, String json, boolean isCoverOldTitle) {
    //        String sequence = "";
    //        String detectSql = "select count(*) from od_data_cfg_table_info where od_data_cfg_oid = ?";
    //        int count = getJdbcTemplate().queryForInt(detectSql, new Object[] { oid });
    //        if (count == 0) {
    //            String insertSql = "insert into od_data_cfg_table_info (table_schema,od_data_cfg_oid) values (?,?)";
    //            getJdbcTemplate().update(insertSql, new Object[] { json, oid });
    //        } else {
    //            if (!isCoverOldTitle) {
    //                checkSameTitle(oid, json);
    //            }
    //            String insertSql = "update od_data_cfg_table_info set table_schema = ? where od_data_cfg_oid = ?";
    //            getJdbcTemplate().update(insertSql, new Object[] { json, oid });
    //        }
    //        String selectSql = "select * from od_data_cfg_table_info where od_data_cfg_oid = ?";
    //        Map<String, Object> columnMap = getJdbcTemplate().queryForMap(selectSql, new Object[] { oid });
    //        sequence = ((BigDecimal) columnMap.get("oid")).toString();
    //
    //        return sequence;
    //    }

    private DataCfgTableInfoPo getDataCfgTableInfoPo(String oid, String json, boolean isCoverOldTitle) {
        DataCfgTableInfoPo dataCfgTableInfoPo = this.dataCfgTableInfoService.findByDataCfgOid(oid);
        if (dataCfgTableInfoPo == null) {
            String insertSql = "insert into od_data_cfg_table_info (table_schema,od_data_cfg_oid) values (?,?)";
            System.err.println("==tableInfo=json===" + json); // 確認Json

            getJdbcTemplate().update(insertSql, new Object[] { json, oid });

            dataCfgTableInfoPo = this.dataCfgTableInfoService.findByDataCfgOid(oid);

            dataCfgTableInfoPo.setTableName(String.format("data_%s", new Object[] { Long.valueOf(dataCfgTableInfoPo.getOid()) }));
            dataCfgTableInfoPo = this.dataCfgTableInfoService.save(dataCfgTableInfoPo);
        } else {
            if (!isCoverOldTitle) {
                checkSameTitle(oid, json);
            }

            dataCfgTableInfoPo.setTableSchema(json);
            dataCfgTableInfoPo = this.dataCfgTableInfoService.save(dataCfgTableInfoPo);
        }

        return dataCfgTableInfoPo;
    }

    private String getTableName(String oid, String json, boolean isCoverOldTitle) {
        DataCfgTableInfoPo dataCfgTableInfoPo = getDataCfgTableInfoPo(oid, json, isCoverOldTitle);

        return dataCfgTableInfoPo.getTableName();
    }

    private Map<String, Object> getTableParam(String oid) {
        String detectSql = "select * from od_data_cfg_table_info where od_data_cfg_oid = ?";
        Map<String, Object> columnMap = getJdbcTemplate().queryForMap(detectSql, new Object[] { oid });

        if ((columnMap.get("oid") != null) && (columnMap.get("table_name") == null)) {
            columnMap.put("table_name", String.format("data_%s", new Object[] { columnMap.get("oid") }));
        }
        return columnMap;
    }

    private Map<String, String> getTypeMap(String json) {
        JSONObject object = JSONObject.fromObject(json);
        JSONArray fields = object.getJSONArray("fields");
        Map<String, String> typeMap = new HashMap();
        for (int i = 0; i < fields.size(); i++) {
            JSONObject field = fields.getJSONObject(i);
            Object[] keys = field.keySet().toArray();
            for (int j = 0; j < keys.length; j++) {
                String key = (String) keys[j];
                if (!StringUtils.equals(key, "type")) {
                    typeMap.put(field.getString(key), field.getString("type"));
                }
            }
        }

        return typeMap;
    }

    private final String sqlStamentFormat = "insert into \"%1$s\" (%2$s) values (%3$s)";

    private int regularizeStructer(String table, Map<String, Object> columnMap, JSONArray records) {
        Object[] keys = records.getJSONObject(0).keySet().toArray();
        StringBuffer sql = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (Object key : keys) {
            sql.append(",\"" + (String) key + "\"");
            values.append(",?");
        }

        String keyStatment = sql.substring(1);
        String valuesStatment = values.substring(1);

        String sqlStatment = String.format("insert into \"%1$s\" (%2$s) values (%3$s)",
                new Object[] { table, keyStatment, valuesStatment });
        List<Map<String, Object>> listMapRecord = new ArrayList();

        Map<String, String> typeMap = getTypeMap((String) columnMap.get("table_schema"));
        Map<String, Object> mapRecord = new HashMap();

        int insertCount = 0;

        int i = 0;
        for (int idx = 1; i < records.size(); idx++) {
            JSONObject record = records.getJSONObject(i);
            mapRecord = new HashMap();
            listMapRecord.add(mapRecord);
            for (int j = 0; j < keys.length; j++) {
                String key = (String) keys[j];
                String value = record.getString(key);
                String type = (String) typeMap.get(keys[j]);
                mapRecord.put(key, this.sqlParamUtil.getParameter(value, type));
            }

            if ((idx & 0x3E8) == 1000) {
                insertCount += batchUpdate(sqlStatment, listMapRecord, keys);
                listMapRecord.clear();
                idx = 1;
            }
            i++;
        }

        if (!listMapRecord.isEmpty()) {
            insertCount += batchUpdate(sqlStatment, listMapRecord, keys);
        }
        return insertCount;
    }

    private int nonRegularizeStructer(String table, Map<String, Object> columnMap, JSONArray records) {
        Object[] keys = records.getJSONObject(0).keySet().toArray();
        StringBuffer sql = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (Object key : keys) {
            sql.append(",\"" + (String) key + "\"");
            values.append(",?");
        }

        String keyStatment = sql.substring(1);
        String valuesStatment = values.substring(1);

        String sqlStatment = String.format("insert into \"%1$s\" (%2$s) values (%3$s)",
                new Object[] { table, keyStatment, valuesStatment });
        List<Map<String, Object>> listMapRecord = new ArrayList();

        Map<String, String> typeMap = getTypeMap((String) columnMap.get("table_schema"));
        Map<String, Object> mapRecord = new HashMap();

        int insertCount = 0;

        int i = 0;
        for (int idx = 1; i < records.size(); idx++) {
            JSONObject record = records.getJSONObject(i);
            mapRecord = new HashMap();
            listMapRecord.add(mapRecord);
            keys = records.getJSONObject(i).keySet().toArray();
            for (int j = 0; j < keys.length; j++) {
                String key = (String) keys[j];
                String value = record.getString(key);
                String type = (String) typeMap.get(keys[j]);
                mapRecord.put(key, this.sqlParamUtil.getParameter(value, type));
            }

            if ((idx & 0x3E8) == 1000) {
                insertCount += batchUpdate(sqlStatment, listMapRecord, keys);
                listMapRecord.clear();
                idx = 1;
            }
            i++;
        }

        if (!listMapRecord.isEmpty()) {
            insertCount += batchUpdate(sqlStatment, listMapRecord, keys);
        }
        return insertCount;
    }

    private int batchUpdate(String sqlStatment, final List<Map<String, Object>> paramList, final Object[] keys) {
        getJdbcTemplate().setFetchSize(100);
        int[] updateCounts = getJdbcTemplate().batchUpdate(sqlStatment, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int idx) throws SQLException {
                Map<String, Object> data = (Map) paramList.get(idx);
                for (int i = 0; i < keys.length; i++) {
                    ps.setObject(i + 1, data.get(keys[i]));
                }
            }

            @Override
            public int getBatchSize() {
                return paramList.size();
            }

        });
        int total = 0;
        for (int count : updateCounts)
            total += count;
        return total;
    }

    @Override
    public int batchInsertCsvData(String oid, File file, Charset charset) throws OpdException {
        int totalRows = 0;
        String insertString = getInsertString(oid);
        String delimiter = ",";
        int limit = -1;

        BufferedReader reader = null;
        List<String[]> dataList = new ArrayList();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

            reader.readLine();

            for (String line = null; (line = reader.readLine()) != null;) {
                String[] data = line.split(",", -1);
                dataList.add(data);

                if ((dataList.size() & 0x1388) == 5000) {
                    batchInsertData(insertString, dataList);
                    totalRows += dataList.size();
                    dataList.clear();
                }
            }

            if (!dataList.isEmpty()) {
                batchInsertData(insertString, dataList);
                totalRows += dataList.size();
                dataList.clear();
            }

            return totalRows;
        } catch (Exception e) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
                }
            }
        }
    }

    private int[] batchInsertData(String insertString, final List<String[]> dataList) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.setFetchSize(100);

        BatchPreparedStatementSetter batchSetter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int idx) throws SQLException {
                String[] datas = (String[]) dataList.get(idx);
                for (int dataIdx = 0; dataIdx < datas.length; dataIdx++) {
                    String data = datas[dataIdx];
                    statement.setObject(dataIdx + 1, data);
                }
            }

            @Override
            public int getBatchSize() {
                return dataList.size();
            }

        };
        return jdbcTemplate.batchUpdate(insertString, batchSetter);
    }

    private String getInsertString(String oid) {
        String insertString = null;
        String format = "insert into %s (%s) values (%s);";

        Map<String, Object> tableParam = getTableParam(oid);
        String tableName = tableParam.get("table_name").toString();
        String tableSchema = tableParam.get("table_schema").toString();
        JSONObject tableSchemaObject = JSONObject.fromObject(tableSchema);
        JSONArray tableColumns = tableSchemaObject.getJSONArray("fields");

        StringBuilder columnsBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();
        for (int index = 0; index < tableColumns.size();) {
            JSONObject columnDefine = tableColumns.getJSONObject(index);
            String columnName = columnDefine.getString("id");

            columnsBuilder.append(columnName);
            valuesBuilder.append("?");

            index++;
            if (index != tableColumns.size()) {
                columnsBuilder.append(",");
                valuesBuilder.append(",");
            }
        }

        String columns = columnsBuilder.toString();
        String values = valuesBuilder.toString();

        insertString = String.format(format, new Object[] { tableName, columns, values });

        return insertString;
    }
}