/*    */ package com.iisi.opd.data.out.format.impl;
/*    */ 
/*    */ import com.iisi.opd.data.out.format.OutputFormat;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.sf.json.JSONArray;
/*    */ import net.sf.json.JSONObject;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class JsonOutputFormatImpl
/*    */   implements OutputFormat
/*    */ {
/*    */   public String getOutputData(List<Map<String, Object>> dataList)
/*    */   {
/* 18 */     return getOutputData(dataList, false);
/*    */   }
/*    */   
/*    */   public String getOutputData(List<Map<String, Object>> dataList, boolean isAppend)
/*    */   {
/* 23 */     JSONArray jarray = new JSONArray();
/*    */     
/* 25 */     for (Map<String, Object> dlobj : dataList)
/*    */     {
/* 27 */       JSONObject jobj = new JSONObject();
/* 28 */       jobj.accumulateAll(dlobj);
/* 29 */       jarray.add(jobj);
/*    */     }
/* 31 */     return jarray.toString();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\format\impl\JsonOutputFormatImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */