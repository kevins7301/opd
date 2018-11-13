/*    */ package com.iisi.opd.data.out.dao.impl;
/*    */ 
/*    */ import com.iisi.common.util.SqlParamUtil;
/*    */ import com.iisi.opd.data.out.dao.DataOutDao;
/*    */ import com.iisi.opd.data.out.dto.SqlParamDto;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.sql.DataSource;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataOutDaoImpl
/*    */   extends JdbcDaoSupport
/*    */   implements DataOutDao
/*    */ {
/*    */   @Autowired
/*    */   private SqlParamUtil sqlParamUtil;
/*    */   
/*    */   @Autowired
/*    */   public DataOutDaoImpl(DataSource dataSource)
/*    */   {
/* 28 */     super.setDataSource(dataSource);
/*    */   }
/*    */   
/*    */   public List<String> getColumnName(String tableName)
/*    */   {
/* 33 */     String sqlString = " SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WITH(NOLOCK) WHERE TABLE_NAME = ? ";
/* 34 */     String[] params = { tableName };
/* 35 */     return getJdbcTemplate().queryForList(sqlString, params, String.class);
/*    */   }
/*    */   
/*    */   public List<Map<String, Object>> getData(String sql, Map<String, String> fieldMap, List<SqlParamDto> paramList)
/*    */   {
/* 40 */     Object[] params = new Object[paramList.size()];
/* 41 */     for (int i = 0; i < paramList.size(); i++) {
/* 42 */       SqlParamDto dto = (SqlParamDto)paramList.get(i);
/* 43 */       String value = dto.getValue();
/* 44 */       String type = (String)fieldMap.get(dto.getName());
/* 45 */       params[i] = this.sqlParamUtil.getParameter(value, type);
/*    */     }
/*    */     
/* 48 */     List<Map<String, Object>> dataList = null;
/* 49 */     dataList = getJdbcTemplate().queryForList(sql, params);
/* 50 */     return dataList;
/*    */   }
/*    */   
/*    */   public List<Map<String, Object>> getData(String sql, List<SqlParamDto> paramList)
/*    */   {
/* 55 */     Object[] params = new Object[paramList.size()];
/* 56 */     for (int i = 0; i < paramList.size(); i++) {
/* 57 */       SqlParamDto dto = (SqlParamDto)paramList.get(i);
/* 58 */       params[i] = dto.getValue();
/*    */     }
/*    */     
/* 61 */     List<Map<String, Object>> dataList = null;
/* 62 */     dataList = getJdbcTemplate().queryForList(sql, params);
/* 63 */     return dataList;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\dao\impl\DataOutDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */