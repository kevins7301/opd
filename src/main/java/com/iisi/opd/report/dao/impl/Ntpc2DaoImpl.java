package com.iisi.opd.report.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.impl.GenericDaoImpl;
import com.iisi.common.util.Pager;
import com.iisi.opd.report.dao.Ntpc2Dao;
import com.iisi.opd.report.vo.LogAnalysisOptionsVo;

@Repository
public class Ntpc2DaoImpl extends GenericDaoImpl<Object> implements Ntpc2Dao {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private String prerareDataLogBaseSumAnalysisOptions(LogAnalysisOptionsVo analysisOptionsVo) {
        return String.format(" SUM(%1$s + %2$s + %3$s + %4$s) AS 'sum_value'\n",
                new Object[] { analysisOptionsVo.isAccessCount() ? "ISNULL(OLA.[count], 0)" : "0",
                        analysisOptionsVo.isDataOutCount() ? "ISNULL(OLDO.[count], 0)" : "0",
                        analysisOptionsVo.isDataFileCount() ? "ISNULL(OLDF.[count], 0)" : "0",
                        analysisOptionsVo.isDataSrvCount() ? "ISNULL(OLDS.[count], 0)" : "0" });
    }

    private String prerareDataLogBaseColumnAnalysisOptions(LogAnalysisOptionsVo analysisOptionsVo) {
        return String.format(
                " , SUM(%1$s) AS 'view_count', SUM(%2$s) AS 'data_out_count', SUM(%3$s) AS 'download_count', SUM(%4$s) AS 'data_srv_count' \n",
                new Object[] { analysisOptionsVo.isAccessCount() ? "ISNULL(OLA.[count], 0)" : "0",
                        analysisOptionsVo.isDataOutCount() ? "ISNULL(OLDO.[count], 0)" : "0",
                        analysisOptionsVo.isDataFileCount() ? "ISNULL(OLDF.[count], 0)" : "0",
                        analysisOptionsVo.isDataSrvCount() ? "ISNULL(OLDS.[count], 0)" : "0" });
    }

    @Override
    @Deprecated
    public List<Object[]> getDataLogAnalysis(boolean isAnalysisByUnit, IntervalBean interval,
            LogAnalysisOptionsVo analysisOptionsVo) {
        String whereByRange = interval.getNativeSQL();
        String dataLogSum = prerareDataLogBaseSumAnalysisOptions(analysisOptionsVo);
        String dataLogColumn = prerareDataLogBaseColumnAnalysisOptions(analysisOptionsVo);

        StringBuffer sqlStatment = new StringBuffer();
        if (isAnalysisByUnit) {
            sqlStatment.append(";WITH CTE AS ( \n ");
            sqlStatment.append("\tSELECT * FROM [dbo].[ntpc_unit] WHERE unit_level = 1 \n ");
            sqlStatment.append(") \n ");
        }

        sqlStatment.append("SELECT " + dataLogSum + " \n");
        if (isAnalysisByUnit) {
            sqlStatment.append("\t , AU.ou, AU.unit_name \n ");
            sqlStatment.append(dataLogColumn);
            sqlStatment.append("FROM CTE AS AU \n ");
        } else {
            sqlStatment.append("\t , ODC.oid, ODC.name \n ");
            sqlStatment.append(dataLogColumn);
            sqlStatment.append("FROM [dbo].[od_data_category] AS ODC \n ");
            sqlStatment.append("LEFT JOIN [dbo].[od_data_set] AS ODS ON ODC.oid = ODS.od_data_category_oid \n ");
        }
        sqlStatment.append("LEFT JOIN ( \n ");
        if (isAnalysisByUnit) {
            sqlStatment.append("\t\tSELECT RC.ou AS 'data_set_unit_oid', COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_file] \n ");
            sqlStatment.append("\t\tLEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = data_set_unit_oid \n ");
            sqlStatment.append("\t\tLEFT JOIN CTE AS RC ON AU.full_ou like '%,' + RC.ou \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY RC.ou \n ");
            sqlStatment.append(") AS OLDF ON OLDF.data_set_unit_oid = AU.ou \n ");
        } else {
            sqlStatment.append("\t\tSELECT data_set_oid, COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_file] \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY data_set_oid \n ");
            sqlStatment.append(") AS OLDF ON OLDF.data_set_oid = ODS.oid \n ");
        }
        sqlStatment.append("LEFT JOIN ( \n ");
        if (isAnalysisByUnit) {
            sqlStatment.append("\t\tSELECT RC.ou AS 'data_set_unit_oid', COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_out] \n ");
            sqlStatment.append("\t\tLEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = data_set_unit_oid \n ");
            sqlStatment.append("\t\tLEFT JOIN CTE AS RC ON AU.full_ou like '%,' + RC.ou \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY RC.ou \n ");
            sqlStatment.append(") AS OLDO ON OLDO.data_set_unit_oid = AU.ou \n ");
        } else {
            sqlStatment.append("\t\tSELECT data_set_oid, COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_out] \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY data_set_oid \n ");
            sqlStatment.append(") AS OLDO ON OLDO.data_set_oid = ODS.oid \n ");
        }
        sqlStatment.append("LEFT JOIN ( \n ");
        if (isAnalysisByUnit) {
            sqlStatment.append("\t\tSELECT RC.ou AS 'data_set_unit_oid', COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_srv] \n ");
            sqlStatment.append("\t\tLEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = data_set_unit_oid \n ");
            sqlStatment.append("\t\tLEFT JOIN CTE AS RC ON AU.full_ou like '%,' + RC.ou \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY RC.ou \n ");
            sqlStatment.append(") AS OLDS ON OLDS.data_set_unit_oid = AU.ou \n ");
        } else {
            sqlStatment.append("\t\tSELECT data_set_oid, COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_data_srv] \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY data_set_oid \n ");
            sqlStatment.append(") AS OLDS ON OLDS.data_set_oid = ODS.oid \n ");
        }
        sqlStatment.append("LEFT JOIN ( \n ");
        if (isAnalysisByUnit) {
            sqlStatment.append("\t\tSELECT RC.ou AS 'data_set_unit_oid', COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_access] \n ");
            sqlStatment.append("\t\tLEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = data_set_unit_oid \n ");
            sqlStatment.append("\t\tLEFT JOIN CTE AS RC ON AU.full_ou like '%,' + RC.ou \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY RC.ou \n ");
            sqlStatment.append(") AS OLA ON OLA.data_set_unit_oid = AU.ou \n ");
        } else {
            sqlStatment.append("\t\tSELECT data_set_oid, COUNT(oid) AS 'count' \n ");
            sqlStatment.append("\t\tFROM [dbo].[od_log_access] \n ");
            sqlStatment.append("\t\tWHERE " + whereByRange + "\n");
            sqlStatment.append("\t\tGROUP BY data_set_oid \n ");
            sqlStatment.append(") AS OLA ON OLA.data_set_oid = ODS.oid \n ");
            sqlStatment.append("WHERE ODS.is_enable = 1 \n ");
            sqlStatment.append("OR ODS.oid IS NULL \n ");
        }
        sqlStatment.append("GROUP BY \n ");
        if (isAnalysisByUnit) {
            sqlStatment.append("\tAU.ou, AU.unit_name \n ");
        } else {
            sqlStatment.append("\tODC.oid, ODC.name \n ");
        }
        sqlStatment.append("ORDER BY sum_value DESC \n");

        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public List<Object[]> getKindOfDataLogAnalysis(boolean isAnalysisByUnit, IntervalBean interval,
            LogAnalysisOptionsVo analysisOptionsVo) {
        String whereByRange = interval.getNativeSQL();

        StringBuffer sqlStatment = new StringBuffer();
        if (isAnalysisByUnit) {
            sqlStatment.append("-------------------------- \n ");
            sqlStatment.append("--- ntpc_unit \n ");
            sqlStatment.append("-------------------------- \n ");
            sqlStatment.append(";WITH CTE AS ( \n ");
            sqlStatment.append("\tSELECT ou, unit_name FROM [dbo].[ntpc_unit] WHERE unit_level = 1 \n ");
            sqlStatment.append(") \n ");
            sqlStatment.append(
                    "SELECT SUM(ISNULL(UC.view_count, 0) + ISNULL(UC.data_out_count, 0) + ISNULL(UC.download_count, 0) + ISNULL(UC.data_srv_count, 0)) AS 'sum_value' \n ");
            sqlStatment.append("\t , AU.ou, AU.unit_name \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.view_count, 0)) AS 'view_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.data_out_count, 0)) AS 'data_out_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.download_count, 0)) AS 'download_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.data_srv_count, 0)) AS 'data_srv_count' \n ");
            sqlStatment.append("FROM CTE AS AU \n ");
            sqlStatment.append(
                    "LEFT JOIN [dbo].[ntpc_unit] AS RC ON SUBSTRING(RC.[full_ou], LEN(RC.[full_ou])-6, LEN(RC.[full_ou])) = AU.ou \n ");
            sqlStatment.append("LEFT JOIN ( \n ");
            sqlStatment.append(
                    "\t\tSELECT NULL 'data_set_unit_oid', 0 'view_count', 0 'data_out_count', 0 'download_count', 0 'data_srv_count' \n ");
            if (analysisOptionsVo.isAccessCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_unit_oid, COUNT(oid), 0, 0, 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_access] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_unit_oid \n ");
            }
            if (analysisOptionsVo.isDataOutCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_unit_oid, 0, COUNT(oid), 0, 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_out] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_unit_oid \n ");
            }
            if (analysisOptionsVo.isDataFileCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_unit_oid, 0, 0, COUNT(oid), 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_file] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_unit_oid \n ");
            }
            if (analysisOptionsVo.isDataSrvCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_unit_oid, 0, 0, 0, COUNT(oid) \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_srv] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_unit_oid \n ");
            }
            sqlStatment.append(") UC ON UC.data_set_unit_oid = RC.ou \n ");
            sqlStatment.append("GROUP BY AU.ou, AU.unit_name \n ");
        } else {
            sqlStatment.append("-------------------------- \n ");
            sqlStatment.append("--- category \n ");
            sqlStatment.append("-------------------------- \n ");
            sqlStatment.append(
                    "SELECT SUM(ISNULL(UC.view_count, 0) + ISNULL(UC.data_out_count, 0) + ISNULL(UC.download_count, 0) + ISNULL(UC.data_srv_count, 0)) AS 'sum_value' \n ");
            sqlStatment.append("\t , ODC.oid, ODC.name \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.view_count, 0)) AS 'view_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.data_out_count, 0)) AS 'data_out_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.download_count, 0)) AS 'download_count' \n ");
            sqlStatment.append("\t , SUM(ISNULL(UC.data_srv_count, 0)) AS 'data_srv_count' \n ");
            sqlStatment.append("FROM [dbo].[od_data_category] AS ODC \n ");
            sqlStatment.append("LEFT JOIN [dbo].[od_data_set] AS ODS ON ODS.od_data_category_oid = ODC.oid \n ");
            sqlStatment.append("LEFT JOIN ( \n ");
            sqlStatment.append(
                    "\t\tSELECT NULL 'data_set_oid', 0 'view_count', 0 'data_out_count', 0 'download_count', 0 'data_srv_count' \n ");
            if (analysisOptionsVo.isAccessCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_oid, COUNT(oid), 0, 0, 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_access] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_oid \n ");
            }
            if (analysisOptionsVo.isDataOutCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_oid, 0, COUNT(oid), 0, 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_out] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_oid \n ");
            }
            if (analysisOptionsVo.isDataFileCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_oid, 0, 0, COUNT(oid), 0 \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_file] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_oid \n ");
            }
            if (analysisOptionsVo.isDataSrvCount()) {
                sqlStatment.append("\tUNION ALL \n ");
                sqlStatment.append("\tSELECT data_set_oid, 0, 0, 0, COUNT(oid) \n ");
                sqlStatment.append("\tFROM [dbo].[od_log_data_srv] \n ");
                sqlStatment.append("\tWHERE " + whereByRange + " \n ");
                sqlStatment.append("\tGROUP BY data_set_oid \n ");
            }
            sqlStatment.append(") UC ON UC.data_set_oid = ODS.oid \n ");
            sqlStatment.append("GROUP BY ODC.oid, ODC.name \n ");
        }
        sqlStatment.append("-------------------------- \n ");
        sqlStatment.append("--- common \n ");
        sqlStatment.append("-------------------------- \n ");
        sqlStatment.append("ORDER BY sum_value DESC \n ");

        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public List<Object[]> getFreqOfDataLogAnalysis(IntervalBean interval, LogAnalysisOptionsVo analysisOptionsVo,
            List<String> unitOidList, List<String> dataSetOidList, int frequency) {
        Date startTime = (Date) interval.getStart();
        if (startTime == null) {
            Calendar now = Calendar.getInstance();
            now.add(5, -1);
            startTime = now.getTime();
        }
        Date endTime = (Date) interval.getEnd();
        String dataSetOidLists = listToString(dataSetOidList);

        String frequencyType;
        if (frequency == 1) {
            frequencyType = "DAY";
        } else {
            if (frequency == 2) {
                frequencyType = "WEEK";
            } else {
                if (frequency == 3) {
                    frequencyType = "MONTH";
                } else {
                    if (frequency == 4) {
                        frequencyType = "QUARTER";
                    } else
                        frequencyType = "YEAR";
                }
            }
        }
        StringBuffer sqlStatment = new StringBuffer();
        sqlStatment.append(";WITH RANG AS ( \n ");
        sqlStatment.append(
                "\tSELECT CAST('" + this.simpleDateFormat.format(startTime) + "' AS DATE) AS 'base_date', 1 AS 'rounds' \n ");
        sqlStatment.append("\tUNION ALL \n ");
        sqlStatment.append("\tSELECT DATEADD(" + frequencyType + ", 1, base_date), rounds + 1 \n ");
        sqlStatment.append("\tFROM RANG \n ");
        sqlStatment.append("\tWHERE rounds <= 100 \n ");
        sqlStatment.append("\tAND DATEDIFF(" + frequencyType + ", base_date, GETDATE()) > 0 \n ");
        if (endTime != null)
            sqlStatment.append(
                    "\tAND DATEDIFF(" + frequencyType + ", base_date, '" + this.simpleDateFormat.format(endTime) + "') >= 0 \n ");
        sqlStatment.append("), BT AS ( \n ");
        sqlStatment.append("\tSELECT MAX(base_date) AS 'max_bound', MIN(base_date) AS 'min_bound' \n ");
        sqlStatment.append("\tFROM RANG \n ");
        sqlStatment.append("), ODS AS ( \n ");
        sqlStatment.append("\t\tSELECT oid, name \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_data_set] \n ");
        if (dataSetOidLists != null)
            sqlStatment.append("\t\tWHERE oid IN (" + dataSetOidLists + ") \n ");
        sqlStatment.append("), CTERANGE AS ( \n ");
        sqlStatment.append("\tSELECT * FROM ODS, RANG \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append(
                "SELECT SUM(UC.view_count + UC.data_out_count + UC.download_count + UC.data_srv_count) AS 'sum_value' \n ");
        sqlStatment.append("\t , UC.oid, UC.name \n ");
        sqlStatment.append("\t , SUM(UC.view_count) AS 'view_count' \n ");
        sqlStatment.append("\t , SUM(UC.data_out_count) AS 'data_out_count' \n ");
        sqlStatment.append("\t , SUM(UC.download_count) AS 'download_count' \n ");
        sqlStatment.append("\t , SUM(UC.data_srv_count) AS 'data_srv_count' \n ");
        sqlStatment.append("\t , R.base_date \n ");
        sqlStatment.append("FROM ( \n ");
        sqlStatment.append(
                "\t\tSELECT NULL 'oid', NULL 'name', 0 'view_count', 0 'data_out_count', 0 'download_count', 0 'data_srv_count', 0 'years', '' 'options_type' \n ");
        if (analysisOptionsVo.isAccessCount()) {
            sqlStatment.append("\tUNION ALL \n ");
            sqlStatment.append("\tSELECT ODS.oid, ODS.name, COUNT(OLA.oid), 0, 0, 0, DATENAME(YEAR, OLA.log_time), DATENAME("
                    + frequencyType + ", OLA.log_time) \n ");
            sqlStatment.append("\tFROM ODS \n ");
            sqlStatment.append("\tLEFT JOIN [dbo].[od_log_access] AS OLA ON OLA.data_set_oid = ODS.oid \n ");
            sqlStatment.append("\tJOIN BT ON 1=1 \n ");
            sqlStatment.append("\tWHERE OLA.log_time BETWEEN BT.min_bound AND BT.max_bound \n ");
            sqlStatment.append("\tGROUP BY ODS.oid, ODS.name, DATENAME(YEAR, OLA.log_time), DATENAME(" + frequencyType
                    + ", OLA.log_time) \n ");
        }
        if (analysisOptionsVo.isDataOutCount()) {
            sqlStatment.append("\tUNION ALL \n ");
            sqlStatment.append("\tSELECT ODS.oid, ODS.name, 0, COUNT(OLDO.oid), 0, 0, DATENAME(YEAR, OLDO.log_time), DATENAME("
                    + frequencyType + ", OLDO.log_time) \n ");
            sqlStatment.append("\tFROM ODS \n ");
            sqlStatment.append("\tLEFT JOIN [dbo].[od_log_data_out] AS OLDO ON OLDO.data_set_oid = ODS.oid \n ");
            sqlStatment.append("\tJOIN BT ON 1=1 \n ");
            sqlStatment.append("\tWHERE log_time BETWEEN BT.min_bound AND BT.max_bound \n ");
            sqlStatment.append("\tGROUP BY ODS.oid, ODS.name, DATENAME(YEAR, OLDO.log_time), DATENAME(" + frequencyType
                    + ", OLDO.log_time) \n ");
        }
        if (analysisOptionsVo.isDataFileCount()) {
            sqlStatment.append("\tUNION ALL \n ");
            sqlStatment.append("\tSELECT ODS.oid, ODS.name, 0, 0, COUNT(OLDF.oid), 0, DATENAME(YEAR, OLDF.log_time), DATENAME("
                    + frequencyType + ", OLDF.log_time) \n ");
            sqlStatment.append("\tFROM ODS \n ");
            sqlStatment.append("\tLEFT JOIN [dbo].[od_log_data_file] AS OLDF ON OLDF.data_set_oid = ODS.oid \n ");
            sqlStatment.append("\tJOIN BT ON 1=1 \n ");
            sqlStatment.append("\tWHERE log_time BETWEEN BT.min_bound AND BT.max_bound \n ");
            sqlStatment.append("\tGROUP BY ODS.oid, ODS.name, DATENAME(YEAR, OLDF.log_time), DATENAME(" + frequencyType
                    + ", OLDF.log_time) \n ");
        }
        if (analysisOptionsVo.isDataSrvCount()) {
            sqlStatment.append("\tUNION ALL \n ");
            sqlStatment.append("\tSELECT ODS.oid, ODS.name, 0, 0, 0, COUNT(OLDS.oid), DATENAME(YEAR, OLDS.log_time), DATENAME("
                    + frequencyType + ", OLDS.log_time) \n ");
            sqlStatment.append("\tFROM ODS \n ");
            sqlStatment.append("\tLEFT JOIN [dbo].[od_log_data_srv] AS OLDS ON OLDS.data_set_oid = ODS.oid \n ");
            sqlStatment.append("\tJOIN BT ON 1=1 \n ");
            sqlStatment.append("\tWHERE log_time BETWEEN BT.min_bound AND BT.max_bound \n ");
            sqlStatment.append("\tGROUP BY ODS.oid, ODS.name, DATENAME(YEAR, OLDS.log_time), DATENAME(" + frequencyType
                    + ", OLDS.log_time) \n ");
        }
        sqlStatment.append("\t\tUNION ALL \n ");
        sqlStatment.append(
                "\t\tSELECT oid, name, 0, 0, 0, 0, DATENAME(YEAR, base_date), DATENAME(" + frequencyType + ", base_date) \n ");
        sqlStatment.append("\t\tFROM CTERANGE \n ");
        sqlStatment.append(") UC \n ");
        sqlStatment.append("LEFT JOIN RANG AS R ON DATENAME(YEAR, R.base_date) = years AND DATENAME(" + frequencyType
                + ", R.base_date) = options_type \n ");
        sqlStatment.append("WHERE UC.oid IS NOT NULL AND R.base_date IS NOT NULL \n ");
        sqlStatment.append("GROUP BY UC.oid, UC.name, R.base_date \n ");
        sqlStatment.append("ORDER BY UC.oid, R.base_date \n ");

        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public List<Object[]> getDataUsedStatistic(IntervalBean interval, String unitOid, String dataSetName) {
        String whereByRange = interval.getNativeSQL();
        StringBuffer sqlStatment = new StringBuffer();

        sqlStatment.append("SELECT ODS.oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t , ODS.name AS 'data_set_name' \n ");
        sqlStatment.append("\t , AU.ou AS 'unit_oid' \n ");
        sqlStatment.append("\t , AU.full_name AS 'full_name' \n ");
        sqlStatment.append("\t , ODSC.oid AS 'category_oid' \n ");
        sqlStatment.append("\t , ODSC.name AS 'category_name' \n ");
        sqlStatment.append("\t , SUMARY.data_count AS 'data_count' \n ");
        sqlStatment.append("\t , SUMARY.access_count AS 'access_count' \n ");
        sqlStatment.append("\t , SUMARY.download_count AS 'download_count' \n ");
        sqlStatment.append("\t , SUMARY.data_out_count AS 'data_out_count' \n ");
        sqlStatment.append("\t , SUMARY.service_count AS 'service_count' \n ");
        sqlStatment.append(
                "\t , SUMARY.data_count + SUMARY.access_count + SUMARY.download_count + SUMARY.data_out_count + SUMARY.service_count AS 'uesd_count' \n ");
        sqlStatment.append("FROM [dbo].[od_data_set] AS ODS \n ");
        sqlStatment.append("LEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = ODS.unit_oid \n ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_category] AS ODSC ON ODSC.oid = ODS.od_data_category_oid \n ");
        sqlStatment.append("LEFT JOIN ( \n ");
        sqlStatment.append("\t\tSELECT od_data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t , SUM(data_count) AS 'data_count' \n ");
        sqlStatment.append("\t\t\t , SUM(access_count) AS 'access_count' \n ");
        sqlStatment.append("\t\t\t , SUM(download_count) AS 'download_count' \n ");
        sqlStatment.append("\t\t\t , SUM(data_out_count) AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t , SUM(service_count) AS 'service_count' \n ");
        sqlStatment.append("\t\tFROM ( \n ");
        sqlStatment.append("\t\t\t\tSELECT od_data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t\t\t , data_count AS 'data_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'access_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'download_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'service_count' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_data_cfg] \n ");
        sqlStatment.append("\t\t\t\tUNION ALL \n ");
        sqlStatment.append("\t\t\t\tSELECT data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'access_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 1 AS 'download_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'service_count' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_log_data_file] \n ");
        sqlStatment.append("\t\t\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\t\t\tUNION ALL \n ");
        sqlStatment.append("\t\t\t\tSELECT data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'access_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'download_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 1 AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'service_count' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_log_data_out] \n ");
        sqlStatment.append("\t\t\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\t\t\tUNION ALL \n ");
        sqlStatment.append("\t\t\t\tSELECT data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'access_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'download_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 1 AS 'service_count' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_log_data_srv] \n ");
        sqlStatment.append("\t\t\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\t\t\tUNION ALL \n ");
        sqlStatment.append("\t\t\t\tSELECT data_set_oid AS 'od_data_set_oid' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 1 AS 'access_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'download_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'data_out_count' \n ");
        sqlStatment.append("\t\t\t\t\t , 0 AS 'service_count' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_log_access] \n ");
        sqlStatment.append("\t\t\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\t) AS TEMP \n ");
        sqlStatment.append("\t\tGROUP BY TEMP.od_data_set_oid \n ");
        sqlStatment.append(") AS SUMARY ON SUMARY.od_data_set_oid = ODS.oid \n ");
        sqlStatment.append("WHERE ODS.is_enable = 1 \n ");

        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlStatment.append("AND ODS.name like '%" + dataSetName + "%' \n ");
        if ((unitOid != null) && (unitOid.trim().length() != 0)) {
            sqlStatment.append("AND AU.full_ou like '%" + unitOid + "%' \n ");
        }
        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public Pager getDataUsedSearch(Pager pager, IntervalBean interval, String dataSetName, String ip, Boolean isPublic) {
        String whereByRange = interval.getNativeSQL();
        StringBuffer sqlCountStatment = new StringBuffer();
        StringBuffer sqlStatment = new StringBuffer();
        sqlCountStatment.append("------ \n ");
        sqlCountStatment.append("-- 算筆數 \n ");
        sqlCountStatment.append("------ \n ");
        sqlCountStatment.append(";WITH ODS AS ( \n ");
        sqlCountStatment.append("\t\tSELECT oid, name \n ");
        sqlCountStatment.append("\t\tFROM [dbo].[od_data_set] \n ");
        sqlCountStatment.append("\t\tWHERE is_enable = 1 \n ");
        if (isPublic != null) {
            if (isPublic.booleanValue()) {
                sqlCountStatment.append(" AND is_public = 1 \n ");
            } else
                sqlCountStatment.append(" AND is_public = 0 \n ");
        }
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlCountStatment.append("\t\tAND name like '%" + dataSetName + "%' \n ");
        sqlCountStatment.append("), UC AS ( \n ");
        sqlCountStatment.append("\t\tSELECT COUNT(oid) AS 'count' \n ");
        sqlCountStatment.append("\t\tFROM [dbo].[od_log_data_file] \n ");
        sqlCountStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlCountStatment.append("\t\tAND data_set_oid IN (SELECT oid FROM ODS) \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlCountStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlCountStatment.append("\t\tGROUP BY data_set_oid \n ");
        sqlCountStatment.append("\t\tUNION ALL \n ");
        sqlCountStatment.append("\t\tSELECT COUNT(oid) AS 'count' \n ");
        sqlCountStatment.append("\t\tFROM [dbo].[od_log_data_out] \n ");
        sqlCountStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlCountStatment.append("\t\tAND data_set_oid IN (SELECT oid FROM ODS) \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlCountStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlCountStatment.append("\t\tGROUP BY data_set_oid \n ");
        sqlCountStatment.append("\t\tUNION ALL \n ");
        sqlCountStatment.append("\t\tSELECT COUNT(oid) AS 'count' \n ");
        sqlCountStatment.append("\t\tFROM [dbo].[od_log_access] \n ");
        sqlCountStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlCountStatment.append("\t\tAND data_set_oid IN (SELECT oid FROM ODS) \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlCountStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlCountStatment.append("\t\tGROUP BY data_set_oid \n ");
        sqlCountStatment.append(") \n ");
        sqlCountStatment.append("SELECT \n ");
        sqlCountStatment.append("\t   ISNULL(SUM(UC.[count]), 0) \n ");
        sqlCountStatment.append("FROM UC AS UC \n ");

        int totalSize = Integer.parseInt(getNativeSqlQueryList(sqlCountStatment.toString()).get(0).toString());
        pager.setTotalRows(totalSize);
        Pager newPager = pager.clone();

        sqlStatment.append("------ \n ");
        sqlStatment.append("-- 取資料 \n ");
        sqlStatment.append("------ \n ");
        sqlStatment.append(";WITH ODS AS ( \n ");
        sqlStatment.append("\t\tSELECT oid, name \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_data_set] \n ");
        sqlStatment.append("\t\tWHERE is_enable = 1 \n ");
        if (isPublic != null) {
            if (isPublic.booleanValue()) {
                sqlCountStatment.append(" AND is_public = 1 \n ");
            } else
                sqlCountStatment.append(" AND is_public = 0 \n ");
        }
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlStatment.append("\t\tAND name like '%" + dataSetName + "%' \n ");
        sqlStatment.append("), UC AS ( \n ");
        sqlStatment.append("\t\tSELECT \n ");
        sqlStatment.append("\t\t\t   ODS.oid \n ");
        sqlStatment.append("\t\t\t , ODS.name \n ");
        sqlStatment.append("\t\t\t , data_set_oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t\t\t , client_ip AS 'client_ip' \n ");
        sqlStatment.append("\t\t\t , log_time AS 'log_time' \n ");
        sqlStatment.append("\t\t\t , '檔案下載' AS 'action' \n ");
        sqlStatment.append("\t\t\t , 0 AS 'data_cnt' \n ");
        sqlStatment.append("\t\t\t , exec_result AS 'exec_result' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_log_data_file] AS OLDF \n ");
        sqlStatment.append("\t\tLEFT JOIN ODS AS ODS ON ODS.oid = OLDF.data_set_oid \n ");
        sqlStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\tAND ODS.oid IS NOT NULL \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlStatment.append("\t\tUNION ALL \n ");
        sqlStatment.append("\t\tSELECT \n ");
        sqlStatment.append("\t\t\t   ODS.oid \n ");
        sqlStatment.append("\t\t\t , ODS.name \n ");
        sqlStatment.append("\t\t\t , data_set_oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t\t\t , client_ip AS 'client_ip' \n ");
        sqlStatment.append("\t\t\t , log_time AS 'log_time' \n ");
        sqlStatment.append("\t\t\t , 'API介接' AS 'action' \n ");
        sqlStatment.append("\t\t\t , data_cnt AS 'data_cnt' \n ");
        sqlStatment.append("\t\t\t , exec_result AS 'exec_result' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_log_data_out] AS OLDO \n ");
        sqlStatment.append("\t\tLEFT JOIN ODS AS ODS ON ODS.oid = OLDO.data_set_oid \n ");
        sqlStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\tAND ODS.oid IS NOT NULL \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlStatment.append("\t\tUNION ALL \n ");
        sqlStatment.append("\t\tSELECT \n ");
        sqlStatment.append("\t\t\t   ODS.oid \n ");
        sqlStatment.append("\t\t\t , ODS.name \n ");
        sqlStatment.append("\t\t\t , data_set_oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t\t\t , client_ip AS 'client_ip' \n ");
        sqlStatment.append("\t\t\t , log_time AS 'log_time' \n ");
        sqlStatment.append("\t\t\t , '點擊' AS 'action' \n ");
        sqlStatment.append("\t\t\t , 0 AS 'data_cnt' \n ");
        sqlStatment.append("\t\t\t , 0 AS 'exec_result' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_log_access] AS OLA \n ");
        sqlStatment.append("\t\tLEFT JOIN ODS AS ODS ON ODS.oid = OLA.data_set_oid \n ");
        sqlStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append("\t\tAND ODS.oid IS NOT NULL \n ");
        if ((ip != null) && (ip.trim().length() != 0))
            sqlStatment.append("\t\tAND client_ip like '%" + ip + "%' \n ");
        sqlStatment.append("), UCR AS ( \n ");
        sqlStatment.append("\t\tSELECT * \n ");
        sqlStatment.append("\t\t\t , ROW_NUMBER() OVER(ORDER BY log_time DESC) AS id \n ");
        sqlStatment.append("\t\tFROM UC \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append("SELECT \n ");
        sqlStatment.append("\t   UC.oid AS 'data_oid' \n ");
        sqlStatment.append("\t , UC.name AS 'data_name' \n ");
        sqlStatment.append("\t , ISNULL(UC.client_ip, '') AS 'client_ip' \n ");
        sqlStatment.append("\t , REPLACE(CONVERT(VARCHAR(25), CAST(UC.log_time AS datetime), 120), '-', '/') AS 'log_time' \n ");
        sqlStatment.append("\t , UC.[action] AS 'action' \n ");
        sqlStatment.append("\t , UC.data_cnt AS 'data_cnt' \n ");
        sqlStatment.append("\t , CASE UC.exec_result \n ");
        sqlStatment.append("\t\tWHEN 0 THEN '成功' \n ");
        sqlStatment.append("\t\tELSE '失敗' \n ");
        sqlStatment.append("\t   END AS 'exec_result' \n ");
        sqlStatment.append("FROM UCR AS UC \n ");
        sqlStatment.append("WHERE UC.id BETWEEN " + newPager.getCurrentStartRowIndex() + " AND "
                + newPager.getCurrentEndRowIndex() + " \n ");
        sqlStatment.append("ORDER BY UC.id ASC \n ");

        newPager.setDataObj(getNativeSqlQueryList(sqlStatment.toString()));

        return newPager;
    }

    @Override
    public List<Object[]> getDataInStatus(IntervalBean interval, String unitOid, String dataSetName, String dataInType) {
        String whereByRange = interval.getNativeSQL();
        StringBuffer sqlStatment = new StringBuffer();
        sqlStatment.append("SELECT ODS.oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t , ODS.name AS 'data_set_name' \n ");
        sqlStatment.append("\t , AU.ou AS 'unit_oid' \n ");
        sqlStatment.append("\t , AU.full_name AS 'full_name' \n ");
        sqlStatment.append("\t , CONVERT(VARCHAR(10), ODS.published_date, 111 ) AS 'published_date' \n ");
        sqlStatment.append("\t , CASE ODCM1.dataInType \n ");
        sqlStatment.append("\t\tWHEN '0' THEN '上傳檔案' \n ");
        sqlStatment.append("\t\tWHEN '1' THEN '系統介接轉入' \n ");
        sqlStatment.append("\t\tWHEN '3' THEN '服務提供' \n ");
        sqlStatment.append("\t\tELSE '' \n ");
        sqlStatment.append("\t   END AS 'data_in_type' \n ");
        sqlStatment.append("\t , NULL AS 'has_file' \n ");
        sqlStatment.append("\t , ISNULL(CAST(SDP.schedule AS VARCHAR), '') AS 'schedule' \n ");
        sqlStatment.append("\t , ISNULL(REPLACE(CONVERT(VARCHAR(25), ODC.update_time, 120 ), '-', '/'), '') AS 'updateTime' \n ");
        sqlStatment.append("\t , CASE ODCM2.dataCfgUpdateFreq \n ");
        sqlStatment.append("\t\tWHEN 'hour' THEN '每小時' \n ");
        sqlStatment.append("\t\tWHEN 'day' THEN '每日' \n ");
        sqlStatment.append("\t\tWHEN 'week' THEN '每週' \n ");
        sqlStatment.append("\t\tWHEN 'month' THEN '每月' \n ");
        sqlStatment.append("\t\tWHEN 'year' THEN '每年' \n ");
        sqlStatment.append("\t\tWHEN 'season' THEN '每季' \n ");
        sqlStatment.append("\t\tWHEN 'x' THEN '即時' \n ");
        sqlStatment.append("\t\tELSE '' \n ");
        sqlStatment.append("\t   END AS 'data_cfg_update_freq' \n ");
        sqlStatment.append("\t , '' AS 'isDelat' \n ");
        sqlStatment.append("FROM [dbo].[od_data_set] AS ODS \n ");
        sqlStatment.append("LEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = ODS.unit_oid \n ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid \n ");
        sqlStatment.append("LEFT JOIN [dbo].[st_data_in_schedule] AS SDP ON SDP.od_data_cfg_oid = ODC.oid \n ");
        sqlStatment.append("LEFT JOIN ( \n ");
        sqlStatment.append("\t\tSELECT od_data_cfg_oid, metadata_key, metadata_value AS 'dataInType' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_data_cfg_metadata] \n ");
        sqlStatment.append(") AS ODCM1 ON ODCM1.od_data_cfg_oid = ODC.oid \n ");
        sqlStatment.append("LEFT JOIN ( \n ");
        sqlStatment.append("\t\tSELECT od_data_cfg_oid, metadata_key, metadata_value AS 'dataCfgUpdateFreq' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_data_cfg_metadata] \n ");
        sqlStatment.append(") AS ODCM2 ON ODCM2.od_data_cfg_oid = ODC.oid \n ");
        sqlStatment.append("WHERE ODS.is_enable = 1 \n ");
        sqlStatment.append("AND ODCM1.metadata_key = 'dataInType' \n ");
        sqlStatment.append("AND ODCM2.metadata_key = 'dataCfgUpdateFreq' \n ");
        sqlStatment.append("AND " + whereByRange + " \n ");
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlStatment.append("AND ODS.name like '%" + dataSetName + "%' \n ");
        if ((unitOid != null) && (unitOid.trim().length() != 0))
            sqlStatment.append("AND AU.full_ou like '%" + unitOid + "%' \n ");
        if ((dataInType != null) && (dataInType.trim().length() != 0))
            sqlStatment.append("AND ODCM1.dataInType = '" + dataInType + "' \n ");
        sqlStatment.append("ORDER BY ODS.published_date DESC\n ");

        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public List<Object[]> getDataInDiff(IntervalBean interval, String unitOid, String dataSetName, String dataInType) {
        String whereByRange = interval.getNativeSQL();
        StringBuffer sqlStatment = new StringBuffer();
        sqlStatment.append(";WITH CTE (data_set_oid, log_time, insert_cnt) AS ( \n ");
        sqlStatment.append("\t\tSELECT data_set_oid, log_time, insert_cnt \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_log_data_in] \n ");
        sqlStatment.append("\t\tWHERE " + whereByRange + " \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append("SELECT ODS.oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t , ODS.name AS 'data_set_name' \n ");
        sqlStatment.append("\t , AU.ou AS 'unit_oid' \n ");
        sqlStatment.append("\t , AU.full_name AS 'full_name' \n ");
        sqlStatment.append("\t , CONVERT(VARCHAR(10), ODS.published_date, 111 ) AS 'published_date' \n ");
        sqlStatment.append("\t , CASE ODCM.dataInType \n ");
        sqlStatment.append("\t\tWHEN '0' THEN '上傳檔案' \n ");
        sqlStatment.append("\t\tWHEN '1' THEN '系統介接轉入' \n ");
        sqlStatment.append("\t\tWHEN '3' THEN '服務提供' \n ");
        sqlStatment.append("\t   END AS 'data_in_type' \n ");
        sqlStatment.append("\t , ISNULL(REPLACE(CONVERT(VARCHAR(25), ODC.update_time, 120 ), '-', '/'), '') AS 'this_time' \n ");
        sqlStatment.append("\t , CASE \n ");
        sqlStatment.append("\t\tWHEN ODC.update_time IS NOT NULL THEN CAST(ODC.data_count AS varchar) \n ");
        sqlStatment.append("\t\tELSE '' \n ");
        sqlStatment.append("\t   END AS 'this_cnt' \n ");
        sqlStatment.append("\t , ISNULL(REPLACE(CONVERT(VARCHAR(25), ( \n ");
        sqlStatment.append("\t\t\t\tSELECT TEMP1.log_time \n ");
        sqlStatment.append("\t\t\t\tFROM ( \n ");
        sqlStatment.append("\t\t\t\t\t\tSELECT ROW_NUMBER() OVER(ORDER BY log_time DESC) AS idx, log_time \n ");
        sqlStatment.append("\t\t\t\t\t\tFROM CTE \n ");
        sqlStatment.append("\t\t\t\t\t\tWHERE data_set_oid = ODS.oid) AS TEMP1 \n ");
        sqlStatment.append("\t\t\t\tWHERE idx = 2 \n ");
        sqlStatment.append("\t\t), 120 ), '-', '/'), '') AS 'last_time' \n ");
        sqlStatment.append("\t , ISNULL(( \n ");
        sqlStatment.append("\t\t\t\tSELECT TOP 1 CAST(TEMP2.insert_cnt AS varchar) \n ");
        sqlStatment.append("\t\t\t\tFROM ( \n ");
        sqlStatment.append("\t\t\t\t\t\tSELECT ROW_NUMBER() OVER(ORDER BY log_time DESC) AS idx, insert_cnt \n ");
        sqlStatment.append("\t\t\t\t\t\tFROM CTE \n ");
        sqlStatment.append("\t\t\t\t\t\tWHERE data_set_oid = ODS.oid) AS TEMP2 \n ");
        sqlStatment.append("\t\t\t\tWHERE idx = 2 \n ");
        sqlStatment.append("\t\t), '') AS 'last_cnt' \n ");
        sqlStatment.append("\t , '' \n ");
        sqlStatment.append("FROM [dbo].[od_data_set] AS ODS \n ");
        sqlStatment.append("LEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = ODS.unit_oid \n ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid \n ");
        sqlStatment.append("LEFT JOIN ( \n ");
        sqlStatment.append("\t\tSELECT od_data_cfg_oid, metadata_key, metadata_value AS 'dataInType' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_data_cfg_metadata] \n ");
        sqlStatment.append(") AS ODCM ON ODCM.od_data_cfg_oid = ODC.oid \n ");
        sqlStatment.append("WHERE ODS.is_enable = 1 \n ");
        sqlStatment.append("AND ODCM.metadata_key = 'dataInType' \n ");
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlStatment.append("AND ODS.name like '%" + dataSetName + "%' \n ");
        if ((unitOid != null) && (unitOid.trim().length() != 0))
            sqlStatment.append("AND AU.full_ou like '%" + unitOid + "%' \n ");
        if ((dataInType != null) && (dataInType.trim().length() != 0))
            sqlStatment.append("AND ODCM.dataInType = '" + dataInType + "' \n ");
        sqlStatment.append("ORDER BY ODS.published_date DESC\n ");

        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public Pager getDataInSearch(Pager pager, IntervalBean interval, String unitOid, String dataSetName, String dataInType,
            Boolean execResult, Boolean isPublic) {
        String whereByRange = interval.getNativeSQL();
        StringBuffer sqlCountStatment = new StringBuffer();
        StringBuffer sqlStatment = new StringBuffer();
        sqlCountStatment.append("------------ \n ");
        sqlCountStatment.append("-- 計算筆數\n ");
        sqlCountStatment.append("------------ \n ");
        sqlCountStatment.append("SELECT ISNULL(COUNT(OLDI.oid), 0) \n ");
        sqlCountStatment.append("FROM [dbo].[od_log_data_in] AS OLDI \n ");
        sqlCountStatment.append("LEFT JOIN [dbo].[od_data_set] AS ODS ON ODS.oid = OLDI.data_set_oid \n ");
        sqlCountStatment.append("LEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = ODS.unit_oid \n ");
        sqlCountStatment.append("LEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid \n ");
        sqlCountStatment.append("LEFT JOIN ( \n ");
        sqlCountStatment.append("\t\tSELECT od_data_cfg_oid, metadata_key, metadata_value AS 'dataInType' \n ");
        sqlCountStatment.append("\t\tFROM [dbo].[od_data_cfg_metadata] \n ");
        sqlCountStatment.append(") AS ODCM ON ODCM.od_data_cfg_oid = ODC.oid \n ");
        sqlCountStatment.append("WHERE ODS.is_enable = 1 \n ");
        if (isPublic != null) {
            if (isPublic.booleanValue()) {
                sqlCountStatment.append(" AND ODS.is_public = 1 \n ");
            } else
                sqlCountStatment.append(" AND ODS.is_public = 0 \n ");
        }
        sqlCountStatment.append("AND ODCM.metadata_key = 'dataInType' \n ");
        sqlCountStatment.append("AND " + whereByRange + " \n ");
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlCountStatment.append("AND ODS.name like '%" + dataSetName + "%' \n ");
        if ((unitOid != null) && (unitOid.trim().length() != 0))
            sqlCountStatment.append("AND AU.full_ou like '%" + unitOid + "%' \n ");
        if (execResult != null) {
            if (execResult.booleanValue()) {
                sqlCountStatment.append("\t\tAND OLDI.exec_result = 0 \n ");
            } else
                sqlCountStatment.append("\t\tAND OLDI.exec_result != 0 \n ");
        }
        if ((dataInType != null) && (dataInType.trim().length() != 0)) {
            sqlCountStatment.append("AND ODCM.dataInType = '" + dataInType + "' \n ");
        }

        int totalSize = Integer.parseInt(getNativeSqlQueryList(sqlCountStatment.toString()).get(0).toString());
        pager.setTotalRows(totalSize);
        Pager newPager = pager.clone();
        sqlStatment.append("------------ \n ");
        sqlStatment.append("-- 取回資料\n ");
        sqlStatment.append("------------ \n ");
        sqlStatment.append(";WITH CTE AS ( \n ");
        sqlStatment.append("\t\tSELECT \n ");
        sqlStatment.append("\t\t   ODS.oid AS 'data_set_oid' \n ");
        sqlStatment.append("\t\t , ODS.name AS 'data_set_name' \n ");
        sqlStatment.append("\t\t , AU.ou AS 'unit_oid' \n ");
        sqlStatment.append("\t\t , AU.full_name AS 'full_name' \n ");
        sqlStatment.append("\t\t\t , CASE ODCM.dataInType \n ");
        sqlStatment.append("\t\t\t\tWHEN '0' THEN '上傳檔案' \n ");
        sqlStatment.append("\t\t\t\tWHEN '1' THEN '系統介接轉入' \n ");
        sqlStatment.append("\t\t\t\tWHEN '3' THEN '服務提供' \n ");
        sqlStatment.append("\t\t\t\tELSE '' \n ");
        sqlStatment.append("\t\t\t   END AS 'data_in_type' \n ");
        sqlStatment.append("\t\t\t , ISNULL(REPLACE(CONVERT(VARCHAR(25), OLDI.log_time, 120 ), '-', '/'), '') AS 'log_time' \n ");
        sqlStatment.append("\t\t\t , CASE OLDI.check_result \n ");
        sqlStatment.append("\t\t\t\tWHEN 0 THEN '檢核正常' \n ");
        sqlStatment.append("\t\t\t\tWHEN 1 THEN '無檢核' \n ");
        sqlStatment.append("\t\t\t\tWHEN 2 THEN '檢核異常' \n ");
        sqlStatment.append("\t\t\t\tELSE '' \n ");
        sqlStatment.append("\t\t\t   END AS 'check_result' \n ");
        sqlStatment.append("\t\t\t , CASE OLDI.exec_result \n ");
        sqlStatment.append("\t\t\t\tWHEN 0 THEN '正常' \n ");
        sqlStatment.append("\t\t\t\tWHEN 1 THEN '檢核異常' \n ");
        sqlStatment.append("\t\t\t\tWHEN 2 THEN '其他異常' \n ");
        sqlStatment.append("\t\t\t\tWHEN 3 THEN '錯誤結構' \n ");
        sqlStatment.append("\t\t\t\tELSE '' \n ");
        sqlStatment.append("\t\t\t   END AS 'exec_result' \n ");
        sqlStatment.append("\t\t\t , ISNULL(OLDI.err_msg, '') AS 'err_msg' \n ");
        sqlStatment.append("\t\t\t , OLDI.oid AS 'idx' \n ");
        sqlStatment.append("\t\tFROM [dbo].[od_log_data_in] AS OLDI \n ");
        sqlStatment.append("\t\tLEFT JOIN [dbo].[od_data_set] AS ODS ON ODS.oid = OLDI.data_set_oid \n ");
        sqlStatment.append("\t\tLEFT JOIN [dbo].[ntpc_unit] AS AU ON AU.ou = ODS.unit_oid \n ");
        sqlStatment.append("\t\tLEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid \n ");
        sqlStatment.append("\t\tLEFT JOIN ( \n ");
        sqlStatment.append("\t\t\t\tSELECT od_data_cfg_oid, metadata_key, metadata_value AS 'dataInType' \n ");
        sqlStatment.append("\t\t\t\tFROM [dbo].[od_data_cfg_metadata] \n ");
        sqlStatment.append("\t\t) AS ODCM ON ODCM.od_data_cfg_oid = ODC.oid \n ");
        sqlStatment.append("\t\tWHERE ODS.is_enable = 1 \n ");
        if (isPublic != null) {
            if (isPublic.booleanValue()) {
                sqlStatment.append(" AND ODS.is_public = 1 \n ");
            } else
                sqlStatment.append(" AND ODS.is_public = 0 \n ");
        }
        sqlStatment.append("\t\tAND ODCM.metadata_key = 'dataInType' \n ");
        sqlStatment.append("\t\tAND " + whereByRange + " \n ");
        if ((dataSetName != null) && (dataSetName.trim().length() != 0))
            sqlStatment.append("\t\tAND ODS.name like '%" + dataSetName + "%' \n ");
        if ((unitOid != null) && (unitOid.trim().length() != 0))
            sqlStatment.append("\t\tAND AU.full_ou like '%" + unitOid + "%' \n ");
        if (execResult != null) {
            if (execResult.booleanValue()) {
                sqlStatment.append("\t\tAND OLDI.exec_result = 0 \n ");
            } else
                sqlStatment.append("\t\tAND OLDI.exec_result != 0 \n ");
        }
        if ((dataInType != null) && (dataInType.trim().length() != 0))
            sqlStatment.append("\t\tAND ODCM.dataInType = '" + dataInType + "' \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append("SELECT \n ");
        sqlStatment.append("\t   CTE.data_set_oid \n ");
        sqlStatment.append("\t , CTE.data_set_name \n ");
        sqlStatment.append("\t , CTE.unit_oid \n ");
        sqlStatment.append("\t , CTE.full_name \n ");
        sqlStatment.append("\t , CTE.data_in_type \n ");
        sqlStatment.append("\t , CTE.log_time \n ");
        sqlStatment.append("\t , CTE.check_result \n ");
        sqlStatment.append("\t , CTE.exec_result \n ");
        sqlStatment.append("\t , CTE.err_msg \n ");
        sqlStatment.append("FROM CTE \n ");
        sqlStatment.append("WHERE idx IN ( \n ");
        sqlStatment.append("\tSELECT X.idx FROM  \n ");
        sqlStatment.append("\t(SELECT TOP " + newPager.getCurrentEndRowIndex() + " idx FROM CTE ORDER BY log_time DESC) \n ");
        sqlStatment.append("\tAS X \n ");
        int startIdx = newPager.getCurrentStartRowIndex() - 1;
        sqlStatment.append("\tWHERE X.idx NOT IN (SELECT TOP " + (startIdx >= 0 ? startIdx : 0)
                + " idx FROM CTE ORDER BY log_time DESC) \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append("ORDER BY CTE.log_time DESC \n ");

        newPager.setDataObj(getNativeSqlQueryList(sqlStatment.toString()));
        return newPager;
    }

    @Override
    public List<Object[]> getEachUnitDataStstisticStatus() {
        StringBuffer sqlStatment = new StringBuffer();
        sqlStatment.append(";WITH RAU (oid, unit_name) AS ( \n ");
        sqlStatment.append("\t\tSELECT ou, unit_name \n ");
        sqlStatment.append("\t\tFROM [dbo].[ntpc_unit] \n ");
        sqlStatment.append("\t\tWHERE unit_level = 1 \n ");
        sqlStatment.append(") \n ");
        sqlStatment.append("SELECT \n ");
        sqlStatment.append("\t\tUR.oid AS 'oid' \n ");
        sqlStatment.append("\t\t, UR.unit_name AS 'unit_name' \n ");
        sqlStatment.append("\t\t, COUNT(ODS.oid) AS 'sum_data_set' \n ");
        sqlStatment.append("\t\t, SUM(CASE ODC.is_structured \n ");
        sqlStatment.append("\t\t\tWHEN 1 THEN 1 \n ");
        sqlStatment.append("\t\t\tELSE 0 \n ");
        sqlStatment.append("\t\tEND) AS 'sum_of_structured' \n ");
        sqlStatment.append("\t\t, SUM(CASE \n ");
        sqlStatment.append("\t\t\tWHEN (ODS.data_out_count + ODS.view_count + ODS.download_count) > 1000 THEN 1 \n ");
        sqlStatment.append("\t\t\tELSE 0 \n ");
        sqlStatment.append("\t\tEND) AS 'high_used' \n ");
        sqlStatment.append("FROM RAU AS UR \n ");
        sqlStatment.append("LEFT JOIN [dbo].[ntpc_unit] AS NC ON NC.full_ou like '%,' + UR.oid \n ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_set] AS ODS ON ODS.unit_oid = NC.ou \n ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid \n ");
        sqlStatment.append("GROUP BY UR.oid, UR.unit_name \n ");
        sqlStatment.append(" \n ");
        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    private String listToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        String tempString = null;
        if ((stringList != null) && (stringList.size() != 0)) {
            stringBuffer.append("'" + stringList.get(0) + "'");
            for (int i = 1; i < stringList.size(); i++)
                stringBuffer.append(",'" + stringList.get(i) + "'");
            tempString = stringBuffer.toString();
        }
        return tempString;
    }
}