/*     */ package com.iisi.opd.data.in.vo;
/*     */ 
/*     */ import com.iisi.opd.cfg.po.DataCfgApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgPo;
/*     */ import java.util.Date;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataInCheckVo
/*     */ {
/*     */   private Date startTime;
/*     */   private JSONObject object;
/*     */   private String dataOid;
/*     */   private JSONArray records;
/*     */   private DataCfgPo dataCfgPo;
/*     */   private DataCfgApplyPo dataCfgApplyPo;
/*     */   private int count;
/*     */   private boolean hasCheckRule;
/*     */   private StringBuffer errorMeg;
/*     */   
/*     */   public DataInCheckVo(String json)
/*     */   {
/*  29 */     this.startTime = new Date();
/*  30 */     this.object = JSONObject.fromObject(json);
/*  31 */     this.dataOid = this.object.getString("data_oid");
/*  32 */     this.records = this.object.getJSONArray("records");
/*  33 */     this.count = this.records.size();
/*  34 */     this.hasCheckRule = false;
/*  35 */     this.errorMeg = new StringBuffer();
/*     */   }
/*     */   
/*     */   public DataInCheckVo(JSONObject object) {
/*  39 */     this.startTime = new Date();
/*  40 */     this.object = object;
/*  41 */     this.dataOid = object.getString("data_oid");
/*  42 */     this.records = object.getJSONArray("records");
/*  43 */     this.count = this.records.size();
/*  44 */     this.hasCheckRule = false;
/*  45 */     this.errorMeg = new StringBuffer();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/*  53 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JSONObject getObject()
/*     */   {
/*  60 */     return this.object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getDataOid()
/*     */   {
/*  67 */     return this.dataOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JSONArray getRecords()
/*     */   {
/*  74 */     return this.records;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/*  81 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/*  88 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getCount()
/*     */   {
/*  95 */     return this.count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isHasCheckRule()
/*     */   {
/* 102 */     return this.hasCheckRule;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setHasCheckRule(boolean hasCheckRule)
/*     */   {
/* 109 */     this.hasCheckRule = hasCheckRule;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StringBuffer getErrorMeg()
/*     */   {
/* 116 */     return this.errorMeg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addErrorMeg(String msg)
/*     */   {
/* 123 */     this.errorMeg.append(msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DataCfgApplyPo getDataCfgApplyPo()
/*     */   {
/* 130 */     return this.dataCfgApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDataCfgApplyPo(DataCfgApplyPo dataCfgApplyPo)
/*     */   {
/* 137 */     this.dataCfgApplyPo = dataCfgApplyPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\vo\DataInCheckVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */