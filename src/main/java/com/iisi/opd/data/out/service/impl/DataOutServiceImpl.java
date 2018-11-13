package com.iisi.opd.data.out.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataCfgTableInfoPo;
import com.iisi.opd.cfg.po.DataFieldCfgPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.data.out.dao.DataOutDao;
import com.iisi.opd.data.out.dto.SqlParamDto;
import com.iisi.opd.data.out.format.OutputFormat;
import com.iisi.opd.data.out.vo.DataOutOptionsVo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@org.springframework.stereotype.Service
@org.springframework.transaction.annotation.Transactional
public class DataOutServiceImpl implements com.iisi.opd.data.out.service.DataOutService {
    protected final org.slf4j.Logger logger;
    @org.springframework.beans.factory.annotation.Autowired
    private DataOutDao dataOutDao;
    @javax.annotation.Resource(name = "opNameMap")
    private Map<String, String> opNameMap;
    @javax.annotation.Resource(name = "gopNameMap")
    private Map<String, String> gopNameMap;
    @javax.annotation.Resource(name = "formatMap")
    private Map<String, OutputFormat> formatMap;
    @org.springframework.beans.factory.annotation.Autowired
    private DataCfgDao dataCfgDao;

    public DataOutServiceImpl() {
        this.logger = org.slf4j.LoggerFactory.getLogger(getClass());
    }

    @Override
    public Pager getData(final Pager pager, final String oid, final String select, final String distinct, final String filter,
            final String orderby, final CheckOptions options) throws OpdException {
        final DataOutOptionsVo dataOutOptionsVo = new DataOutOptionsVo();
        dataOutOptionsVo.setOptions(options);
        return getData(pager, oid, select, distinct, filter, orderby, dataOutOptionsVo);
    }

    @Override
    public Pager getData(final Pager pager, final String oid, final String select, final String distinct, final String filter,
            final String orderby, final DataOutOptionsVo dataOutOptionsVo) throws OpdException {
        final DataCfgPo dataCfgPo = this.dataCfgDao.findById(oid);
        if ((dataCfgPo == null) || (dataCfgPo.getDataCfgTableInfoPo() == null))
            throw new OpdException(ErrorCodeEnum.ERR_2080001_EXCEPTION);
        pager.setTotalRows(dataCfgPo.getDataCount());
        final Pager newPager = pager.clone();
        newPager.setDataObj(getData(oid, select, distinct, String.valueOf(pager.getCurrentStartRowIndex() - 1),
                String.valueOf(pager.getPageSize()), filter, orderby, dataOutOptionsVo));
        return newPager;
    }

    @Override
    public List<Map<String, Object>> getData(final String oid, final String select, final String distinct, final String skip,
            final String top, final String filter, final String orderby) throws OpdException {
        final DataOutOptionsVo dataOutOptionsVo = new DataOutOptionsVo();
        dataOutOptionsVo.setOptions(CheckOptions.NO_CHECK);
        return getData(oid, select, distinct, skip, top, filter, orderby, CheckOptions.NO_CHECK);
    }

    @Override
    public List<Map<String, Object>> getData(final String oid, final String select, final String distinct, final String skip,
            final String top, final String filter, final String orderby, final CheckOptions options) throws OpdException {
        final DataOutOptionsVo dataOutOptionsVo = new DataOutOptionsVo();
        dataOutOptionsVo.setOptions(options);
        return getData(oid, select, distinct, skip, top, filter, orderby, dataOutOptionsVo);
    }

    @Override
    public List<Map<String, Object>> getData(final String oid, final String select, final String distinct, final String skip,
            final String top, final String filter, String orderby, final DataOutOptionsVo dataOutOptionsVo) throws OpdException {
        final DataCfgPo dataCfgPo = this.dataCfgDao.findById(oid);
        if ((dataCfgPo == null) || (dataCfgPo.getDataCfgTableInfoPo() == null))
            throw new OpdException(ErrorCodeEnum.ERR_2080001_EXCEPTION);
        final CheckOptions options = dataOutOptionsVo.getOptions();
        if (options == CheckOptions.DEFAULT) {
            final DataSetPo dataSetPo = dataCfgPo.getDataSetPo();
            if (dataSetPo == null) {
                throw new OpdException(ErrorCodeEnum.ERR_2080000_EXCEPTION);
            }

            final boolean isSatisfy = (dataSetPo.isActive().booleanValue()) && (dataSetPo.isEnable().booleanValue())
                    && (dataSetPo.isPublic().booleanValue()) && (dataCfgPo.isActive().booleanValue())
                    && (dataCfgPo.isEnable().booleanValue()) && (dataCfgPo.isPublic().booleanValue());

            if (!isSatisfy) {
                return null;
            }
        }
        if ((select != null) && (distinct != null) && (select.trim().length() > 0) && (distinct.trim().length() > 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2080002_EXCEPTION);
        }

        if (!dataCfgPo.isStructured().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2080005_EXCEPTION);
        }
        final DataCfgTableInfoPo dataCfgTableInfoPo = dataCfgPo.getDataCfgTableInfoPo();
        Map<String, String> fieldMap = null;
        final List<SqlParamDto> paramList = new ArrayList();

        final List<String> columnNames = this.dataOutDao.getColumnName(dataCfgTableInfoPo.getTableName());

        final String tableSchema = dataCfgTableInfoPo.getTableSchema();
        String where;
        if ((tableSchema != null) && (!StringUtils.equals(tableSchema, ""))) {
            fieldMap = getFieldMap(dataCfgTableInfoPo);

            where = getFilterParam(fieldMap, filter, paramList);
            checkColumnName(fieldMap, select);
            checkColumnName(fieldMap, distinct);
            checkOrderBy(fieldMap, orderby);
        } else {
            where = getFilterParam(filter, paramList);
            checkColumnName(columnNames, select);
            checkColumnName(columnNames, distinct);
            checkOrderBy(columnNames, orderby);
        }

        int nTop = 0;
        int nSkip = 0;
        try {
            if ((top != null) && (!StringUtils.equals(top.trim(), ""))) {
                nTop = Integer.parseInt(top);
            }
        } catch (final NumberFormatException e) {
            throw new OpdException(ErrorCodeEnum.ERR_2080003_EXCEPTION);
        }
        try {
            if ((skip != null) && (!StringUtils.equals(skip.trim(), ""))) {
                nSkip = Integer.parseInt(skip);
            }
        } catch (final NumberFormatException e) {
            throw new OpdException(ErrorCodeEnum.ERR_2080004_EXCEPTION);
        }

        if ((nTop < 0) || (nTop == 0))
            nTop = 1000;
        if (nSkip < 0) {
            nSkip = 0;
        }
        final StringBuffer sql = new StringBuffer("select");
        if ((select != null) && (!StringUtils.equals(select.trim(), ""))) {
            sql.append(" " + select + " ");
        } else if ((distinct != null) && (!StringUtils.equals(distinct.trim(), ""))) {
            sql.append(" distinct " + distinct + " ");
        } else {
            sql.append(" * ");
        }
        sql.append("from ( select * ");
        if ((orderby == null) || (StringUtils.equals(orderby.trim(), "")))
            orderby = columnNames.get(0);
        sql.append(",row_number() over (order by " + orderby + ") as _rowNum ");

        sql.append("from " + dataCfgTableInfoPo.getTableName() + " with (nolock)");
        sql.append(where);
        sql.append(" ) as _dataTable ");
        sql.append(" where _rowNum > " + nSkip);
        if (nTop > 0)
            sql.append(" and _rowNum <= " + (nSkip + nTop));
        List<Map<String, Object>> dataList;
        if (fieldMap != null) {
            dataList = this.dataOutDao.getData(sql.toString(), fieldMap, paramList);
        } else {
            dataList = this.dataOutDao.getData(sql.toString(), paramList);
        }
        final List<DataFieldCfgPo> dataFieldCfgPoList = dataCfgPo.getDataFieldCfgPoList();
        for (final java.util.Iterator i$ = dataList.iterator(); i$.hasNext();) {
            final Map dataMap = (Map) i$.next();
            dataMap.remove("_id");
            dataMap.remove("_rowNum");
            if (dataFieldCfgPoList != null) {
                for (final DataFieldCfgPo po : dataFieldCfgPoList)
                    if (!po.isPublic().booleanValue())
                        dataMap.remove(po.getFieldName());
            }
        }
        final Map<String, Object> dataMap;
        return dataList;
    }

    @Override
    public String getOutputData(final String format, final List<Map<String, Object>> dataList) throws OpdException {
        return getOutputData(format, dataList, false);
    }

    @Override
    public String getOutputData(final String format, final List<Map<String, Object>> dataList,
            final boolean isAppend) throws OpdException {
        String data = "";
        if ((dataList != null) && (!dataList.isEmpty())) {
            final String temp = format.toLowerCase();
            if (this.formatMap.containsKey(temp)) {
                final OutputFormat outputFormat = this.formatMap.get(temp);
                try {
                    data = outputFormat.getOutputData(dataList, isAppend);
                } catch (final Exception e) {
                    this.logger.error(e.getMessage(), e);

                    throw new OpdException(ErrorCodeEnum.ERR_2080014_EXCEPTION);
                }
            } else {
                throw new OpdException(ErrorCodeEnum.ERR_2080015_EXCEPTION);
            }
        }
        return data;
    }

    private Map<String, String> getFieldMap(final DataCfgTableInfoPo po) {
        final Map<String, String> map = new java.util.HashMap();
        final JSONObject json = JSONObject.fromObject(po.getTableSchema());
        final JSONArray fields = json.getJSONArray("fields");
        for (int i = 0; i < fields.size(); i++) {
            final JSONObject field = fields.getJSONObject(i);
            final Object[] keys = field.keySet().toArray();
            for (int j = 0; j < keys.length; j++) {
                final String key = (String) keys[j];
                if (!StringUtils.equals(key, "type")) {
                    final String name = field.getString(key);
                    final String type = field.getString("type");
                    map.put(name, type);
                }
            }
        }
        return map;
    }

    private void checkColumnName(final Map<String, String> fieldMap, final String target) {
        if ((target != null) && (!StringUtils.equals(target, ""))) {
            final String[] column = target.split(",");
            for (int i = 0; i < column.length; i++) {
                if (!fieldMap.containsKey(column[i].trim())) {
                    throw new OpdException(ErrorCodeEnum.ERR_2080006_EXCEPTION);
                }
            }
        }
    }

    private void checkColumnName(final List<String> fields, final String target) {
        if ((target != null) && (!StringUtils.equals(target, ""))) {
            final String[] column = target.split(",");
            for (int i = 0; i < column.length; i++) {
                if (!fields.contains(column[i].trim())) {
                    throw new OpdException(ErrorCodeEnum.ERR_2080006_EXCEPTION);
                }
            }
        }
    }

    private void checkOrderBy(final Map<String, String> fieldMap, final String target) {
        if ((target != null) && (!StringUtils.equals(target.trim(), ""))) {
            final String[] expression = target.split(",");
            for (int i = 0; i < expression.length; i++) {
                final StringTokenizer tokenizer = new StringTokenizer(expression[i], " ");
                final int count = tokenizer.countTokens();
                if (count > 2) {
                    throw new OpdException(ErrorCodeEnum.ERR_2080007_EXCEPTION);
                }
                if (fieldMap.containsKey(tokenizer.nextToken())) {
                    if (count == 2) {
                        final String token = tokenizer.nextToken().toLowerCase();
                        if ((!StringUtils.equals(token, "desc")) && (!StringUtils.equals(token, "asc"))) {
                            throw new OpdException(ErrorCodeEnum.ERR_2080008_EXCEPTION);
                        }

                    }
                } else {
                    throw new OpdException(ErrorCodeEnum.ERR_2080009_EXCEPTION);
                }
            }
        }
    }

    private void checkOrderBy(final List<String> fields, final String target) {
        if ((target != null) && (!StringUtils.equals(target.trim(), ""))) {
            final String[] expression = target.split(",");
            for (int i = 0; i < expression.length; i++) {
                final StringTokenizer tokenizer = new StringTokenizer(expression[i], " ");
                final int count = tokenizer.countTokens();
                if (count > 2) {
                    throw new OpdException(ErrorCodeEnum.ERR_2080007_EXCEPTION);
                }
                if (fields.contains(tokenizer.nextToken())) {
                    if (count == 2) {
                        final String token = tokenizer.nextToken().toLowerCase();
                        if ((!StringUtils.equals(token, "desc")) && (!StringUtils.equals(token, "asc"))) {
                            throw new OpdException(ErrorCodeEnum.ERR_2080008_EXCEPTION);
                        }

                    }
                } else {
                    throw new OpdException(ErrorCodeEnum.ERR_2080009_EXCEPTION);
                }
            }
        }
    }

    private String getFilterParam(final Map<String, String> fieldMap, final String target, final List<SqlParamDto> paramList) {
        final StringBuffer where = new StringBuffer();
        if ((target != null) && (!StringUtils.equals(target, ""))) {
            final StringTokenizer tokenizer = new StringTokenizer(target, " ");
            final int count = tokenizer.countTokens();
            final int remainder = count % 4;
            if (remainder == 3) {
                where.append(" where ");
                final List<String> columnList = new ArrayList();
                String operand = "";
                for (int i = 0; i < count; i++) {
                    final String token = tokenizer.nextToken();
                    final int temp = i % 4;
                    switch (temp) {
                        case 0:
                            if (fieldMap.containsKey(token)) {
                                columnList.add(token);
                                where.append(token);
                                operand = token;
                            } else {
                                throw new OpdException(ErrorCodeEnum.ERR_2080010_EXCEPTION);
                            }
                            break;
                        case 1:
                            if (this.opNameMap.containsKey(token)) {
                                final String op = this.opNameMap.get(token);
                                where.append(op);
                            } else {
                                throw new OpdException(ErrorCodeEnum.ERR_2080011_EXCEPTION);
                            }
                            break;
                        case 2:
                            where.append("?");
                            final SqlParamDto dto = new SqlParamDto();
                            dto.setName(operand);
                            dto.setValue(token);
                            paramList.add(dto);
                            break;
                        case 3:
                            if (this.gopNameMap.containsKey(token)) {
                                final String gop = this.gopNameMap.get(token);
                                where.append(gop);
                            } else {
                                throw new OpdException(ErrorCodeEnum.ERR_2080012_EXCEPTION);
                            }
                            break;
                    }
                }
            } else {
                throw new OpdException(ErrorCodeEnum.ERR_2080013_EXCEPTION);
            }
        }

        return where.toString();
    }

    private String getFilterParam(final String target, final List<SqlParamDto> paramList) {
        final StringBuffer where = new StringBuffer();
        if ((target != null) && (!StringUtils.equals(target, ""))) {
            final StringTokenizer tokenizer = new StringTokenizer(target, " ");
            final int count = tokenizer.countTokens();
            final int remainder = count % 4;
            if (remainder == 3) {
                where.append(" where ");
                final List<String> columnList = new ArrayList();
                String operand = "";
                for (int i = 0; i < count; i++) {
                    final String token = tokenizer.nextToken().toLowerCase();
                    final int temp = i % 4;
                    switch (temp) {
                        case 0:
                            columnList.add(token);
                            where.append(token);
                            operand = token;
                            break;
                        case 1:
                            if (this.opNameMap.containsKey(token)) {
                                final String op = this.opNameMap.get(token);
                                where.append(op);
                            } else {
                                throw new OpdException(ErrorCodeEnum.ERR_2080011_EXCEPTION);
                            }
                            break;
                        case 2:
                            where.append("?");
                            final SqlParamDto dto = new SqlParamDto();
                            dto.setName(operand);
                            dto.setValue(token);
                            paramList.add(dto);
                            break;
                        case 3:
                            if (this.gopNameMap.containsKey(token)) {
                                final String gop = this.gopNameMap.get(token);
                                where.append(gop);
                            } else {
                                throw new OpdException(ErrorCodeEnum.ERR_2080012_EXCEPTION);
                            }
                            break;
                    }
                }
            } else {
                throw new OpdException(ErrorCodeEnum.ERR_2080013_EXCEPTION);
            }
        }

        return where.toString();
    }

    public static enum CheckOptions {
        DEFAULT(0), NO_CHECK(1);

        private int checkOptions;

        private CheckOptions(final int checkOptions) {
            this.checkOptions = checkOptions;
        }

        public int getcCheckOptions() {
            return this.checkOptions;
        }
    }
}
