/*     */ package com.iisi.common.bean;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.Criterion;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntervalBean
/*     */ {
/*  17 */   private static SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
/*     */   
/*     */ 
/*     */   private Object start;
/*     */   
/*     */ 
/*     */   private Object end;
/*     */   
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */   public IntervalBean() {}
/*     */   
/*     */ 
/*     */   public IntervalBean(Object start, Object end)
/*     */   {
/*  35 */     this.start = start;
/*  36 */     this.end = end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalBean(String name, Object start, Object end)
/*     */   {
/*  46 */     this.name = name;
/*  47 */     this.start = start;
/*  48 */     this.end = end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Criterion getRestriction()
/*     */   {
/*  56 */     if (this.name == null) {
/*  57 */       return Restrictions.sqlRestriction(" 1=1 ");
/*     */     }
/*  59 */     if ((this.start != null) && (this.end != null))
/*  60 */       return Restrictions.between(this.name, this.start, this.end);
/*  61 */     if (this.start != null)
/*  62 */       return Restrictions.ge(this.name, this.start);
/*  63 */     if (this.end != null) {
/*  64 */       return Restrictions.le(this.name, this.end);
/*     */     }
/*  66 */     return Restrictions.sqlRestriction(" 1=1 ");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNativeSQL()
/*     */   {
/*  74 */     if (this.name == null) {
/*  75 */       return " 1=1 ";
/*     */     }
/*  77 */     if ((this.start != null) && (this.end != null))
/*  78 */       return String.format(" %1$s between '%2$s' AND '%3$s' ", new Object[] { this.name, getString(this.start), getString(this.end) });
/*  79 */     if (this.start != null)
/*  80 */       return String.format(" %1$s >= '%2$s' ", new Object[] { this.name, getString(this.start) });
/*  81 */     if (this.end != null) {
/*  82 */       return String.format(" %1$s <= '%2$s' ", new Object[] { this.name, getString(this.end) });
/*     */     }
/*  84 */     return " 1=1 ";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getString(Object obj)
/*     */   {
/*  93 */     if (obj == null) {
/*  94 */       return null;
/*     */     }
/*  96 */     if ((obj instanceof java.util.Date)) {
/*  97 */       return sdFormat.format(obj);
/*     */     }
/*  99 */     if ((obj instanceof java.sql.Date)) {
/* 100 */       return sdFormat.format(obj);
/*     */     }
/* 102 */     return obj.toString();
/*     */   }
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
/*     */   public String toString()
/*     */   {
/* 117 */     return String.format("%1$s:[%2$s,%3$s]", new Object[] { StringUtils.defaultString(this.name, "NULL"), StringUtils.defaultString(getString(this.start), "-∞"), StringUtils.defaultString(getString(this.end), "∞") });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getStart()
/*     */   {
/* 128 */     return this.start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStart(Object start)
/*     */   {
/* 136 */     this.start = start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getEnd()
/*     */   {
/* 144 */     return this.end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnd(Object end)
/*     */   {
/* 152 */     this.end = end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 160 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 168 */     this.name = name;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\common\bean\IntervalBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */