/*    */ package com.iisi.opd.cfg.po;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.Lob;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name="od_data_cfg_table_info")
/*    */ public class DataCfgTableInfoPo
/*    */ {
/*    */   @Id
/*    */   @Column(name="oid")
/*    */   @GeneratedValue(strategy=GenerationType.AUTO)
/*    */   private long oid;
/*    */   @Column(name="table_schema")
/*    */   @Lob
/*    */   private String tableSchema;
/*    */   @Column(name="table_name")
/*    */   @Lob
/*    */   private String tableName;
/*    */   @OneToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid", nullable=false)
/*    */   private DataCfgPo dataCfgPo;
/*    */   
/*    */   public long getOid()
/*    */   {
/* 42 */     return this.oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setOid(long oid)
/*    */   {
/* 50 */     this.oid = oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getTableSchema()
/*    */   {
/* 58 */     return this.tableSchema;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getTableName()
/*    */   {
/* 66 */     return this.tableName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setTableName(String tableName)
/*    */   {
/* 74 */     this.tableName = tableName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setTableSchema(String tableSchema)
/*    */   {
/* 82 */     this.tableSchema = tableSchema;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataCfgPo getDataCfgPo()
/*    */   {
/* 90 */     return this.dataCfgPo;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*    */   {
/* 98 */     this.dataCfgPo = dataCfgPo;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgTableInfoPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */