/*    */ package com.iisi.opd.data.out.format.impl;
/*    */ 
/*    */ import au.com.bytecode.opencsv.CSVWriter;
/*    */ import com.iisi.opd.data.out.format.OutputFormat;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class CsvOutputFormatImpl implements OutputFormat
/*    */ {
/*    */   public String getOutputData(List<Map<String, Object>> dataList) throws IOException
/*    */   {
/* 19 */     return getOutputData(dataList, false);
/*    */   }
/*    */   
/*    */   public String getOutputData(List<Map<String, Object>> dataList, boolean isAppend)
/*    */     throws IOException
/*    */   {
/* 25 */     if ((dataList == null) || (dataList.isEmpty())) {
/* 26 */       return "";
/*    */     }
/*    */     
/* 29 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 30 */     CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos, "UTF-8"));
/*    */     
/* 32 */     Set<String> keys = ((Map)dataList.get(0)).keySet();
/* 33 */     ArrayList<String> values = new ArrayList();
/* 34 */     for (String key : keys) {
/* 35 */       values.add(key);
/*    */     }
/* 37 */     if (!isAppend) {
/* 38 */       writer.writeNext((String[])values.toArray(new String[0]));
/*    */     }
/*    */     
/* 41 */     for (Map<String, Object> dlobj : dataList) {
/* 42 */       values = new ArrayList();
/* 43 */       for (Object dlmobj : dlobj.values()) {
/* 44 */         if (dlmobj != null) {
/* 45 */           values.add(dlmobj.toString());
/*    */         } else
/* 47 */           values.add("");
/*    */       }
/* 49 */       writer.writeNext((String[])values.toArray(new String[0]));
/*    */     }
/* 51 */     writer.close();
/* 52 */     return new String(baos.toByteArray(), "UTF-8");
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\format\impl\CsvOutputFormatImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */