/*     */ package com.iisi.opd.data.in.vo;
/*     */ 
/*     */ import com.iisi.opd.cfg.dto.DataFieldCfgDto;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataInProcessVo
/*     */ {
/*     */   private String dataCfgOid;
/*     */   private byte[] fileContext;
/*     */   private String fileType;
/*     */   private JSONObject createJson;
/*     */   private JSONObject insertJson;
/*     */   private List<?> dataFieldCfgPoList;
/*     */   private List<DataFieldCfgDto> dataFieldCfgDtoList;
/*     */   private DataInOptionsVo dataInOptionsVo;
/*     */   private StringBuffer errorMessage;
/*     */   private Date startTime;
/*     */   private Map<Integer, StringBuffer> errorMessageMap;
/*     */   
/*     */   public DataInProcessVo()
/*     */   {
/*  75 */     this.dataInOptionsVo = new DataInOptionsVo();
/*  76 */     this.errorMessage = new StringBuffer();
/*  77 */     this.startTime = new Date();
/*  78 */     this.errorMessageMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataCfgOid()
/*     */   {
/*  86 */     return this.dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgOid(String dataCfgOid)
/*     */   {
/*  94 */     this.dataCfgOid = dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getFileContext()
/*     */   {
/* 102 */     return this.fileContext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFileContext(byte[] fileContext)
/*     */   {
/* 110 */     this.fileContext = fileContext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JSONObject getCreateJson()
/*     */   {
/* 118 */     return this.createJson;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateJson(JSONObject createJson)
/*     */   {
/* 126 */     this.createJson = createJson;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JSONObject getInsertJson()
/*     */   {
/* 134 */     return this.insertJson;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInsertJson(JSONObject insertJson)
/*     */   {
/* 142 */     this.insertJson = insertJson;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataFieldCfgDto> getDataFieldCfgDtoList()
/*     */   {
/* 150 */     return this.dataFieldCfgDtoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFieldCfgDtoList(List<DataFieldCfgDto> dataFieldCfgDtoList)
/*     */   {
/* 158 */     this.dataFieldCfgDtoList = dataFieldCfgDtoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataInOptionsVo getDataInOptionsVo()
/*     */   {
/* 166 */     return this.dataInOptionsVo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataInOptionsVo(DataInOptionsVo dataInOptionsVo)
/*     */   {
/* 174 */     this.dataInOptionsVo = dataInOptionsVo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFileType()
/*     */   {
/* 182 */     return this.fileType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFileType(String fileType)
/*     */   {
/* 190 */     this.fileType = fileType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringBuffer getErrorMessage()
/*     */   {
/* 198 */     if (this.errorMessageMap.size() == 0) {
/* 199 */       return this.errorMessage;
/*     */     }
/* 201 */     this.errorMessage = new StringBuffer();
/*     */     
/* 203 */     Integer[] keySet = (Integer[])this.errorMessageMap.keySet().toArray(new Integer[0]);
/* 204 */     Arrays.sort(keySet);
/*     */     
/* 206 */     for (Integer index : keySet) {
/* 207 */       this.errorMessage.append((StringBuffer)this.errorMessageMap.get(index));
/*     */     }
/* 209 */     return this.errorMessage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void appendErrorMessage(int i, String errorMessage)
/*     */   {
/* 218 */     StringBuffer sb = (StringBuffer)this.errorMessageMap.get(new Integer(i));
/* 219 */     if (sb == null) {
/* 220 */       sb = new StringBuffer();
/* 221 */       this.errorMessageMap.put(new Integer(i), sb);
/* 222 */       sb.append(String.format("第 %1$d 筆<br/>", new Object[] { Integer.valueOf(i) }));
/*     */     }
/* 224 */     sb.append(errorMessage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<?> getDataFieldCfgPoList()
/*     */   {
/* 232 */     return this.dataFieldCfgPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFieldCfgPoList(List<?> dataFieldCfgPoList)
/*     */   {
/* 240 */     this.dataFieldCfgPoList = dataFieldCfgPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 248 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 256 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<Integer, StringBuffer> getErrorMessageMap()
/*     */   {
/* 264 */     return this.errorMessageMap;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\vo\DataInProcessVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */