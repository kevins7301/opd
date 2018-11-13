/*     */ package com.iisi.opd.data.in.check.impl;
/*     */ 
/*     */ import com.iisi.opd.data.in.check.FieldExecutor;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class NumberFieldExecutorImpl
/*     */   implements FieldExecutor
/*     */ {
/*  24 */   public final Logger log = LoggerFactory.getLogger(getClass());
/*     */   
/*  26 */   private final Pattern patternRange = Pattern.compile("^[0-9]+(\\.([0-9]+))?,[0-9]+(\\.([0-9]+))?$");
/*  27 */   private final Pattern patternNumber = Pattern.compile("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$");
/*  28 */   private final Pattern patternNotEmpty = Pattern.compile("\\S+");
/*  29 */   private final Pattern patternY = Pattern.compile("^(y)+$");
/*     */   
/*  31 */   private boolean TAIWAN = true;
/*  32 */   private boolean INTERNATIONAL = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean skip(String data, String rule)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNotEmpty(String data, String rule)
/*     */   {
/*  51 */     if (data == null)
/*  52 */       return false;
/*  53 */     return isPatternMatch(this.patternNotEmpty, data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean length(String data, String rule)
/*     */   {
/*  63 */     if (data == null) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     boolean isMatchRangePattern = isPatternMatch(this.patternRange, rule.trim());
/*  68 */     if (isMatchRangePattern) {
/*  69 */       String[] rules = rule.split(",");
/*     */       
/*     */ 
/*  72 */       boolean isValueMatch = isPatternMatch(this.patternNumber, data.trim());
/*  73 */       if (isValueMatch) {
/*  74 */         BigDecimal newValue = new BigDecimal(data.trim().toString().toCharArray());
/*  75 */         if ((newValue.compareTo(new BigDecimal(rules[0].toCharArray())) >= 0) && (newValue.compareTo(new BigDecimal(rules[1].toCharArray())) <= 0))
/*     */         {
/*  77 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDate(String data, String rule)
/*     */   {
/*  92 */     if (data == null)
/*  93 */       return false;
/*  94 */     yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*  95 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateTW(String data, String rule)
/*     */   {
/* 105 */     if (data == null)
/* 106 */       return false;
/* 107 */     yearTypeTransformToAD(data, rule, this.TAIWAN);
/* 108 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateBeforeNow(String data, String rule)
/*     */   {
/* 118 */     if (data == null)
/* 119 */       return false;
/* 120 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*     */     
/* 122 */     return calendar.getTime().before(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateBeforeNowTW(String data, String rule)
/*     */   {
/* 132 */     if (data == null)
/* 133 */       return false;
/* 134 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.TAIWAN);
/*     */     
/* 136 */     return calendar.getTime().before(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateAfterNow(String data, String rule)
/*     */   {
/* 146 */     if (data == null)
/* 147 */       return false;
/* 148 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*     */     
/* 150 */     return calendar.getTime().after(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateAfterNowTW(String data, String rule)
/*     */   {
/* 160 */     if (data == null)
/* 161 */       return false;
/* 162 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.TAIWAN);
/*     */     
/* 164 */     return calendar.getTime().after(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Date generateNowDate()
/*     */   {
/* 172 */     return new Date();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isPatternMatch(Pattern pattern, String source)
/*     */   {
/* 182 */     return pattern.matcher(source).matches();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getInt(String date, String format)
/*     */   {
/* 193 */     int[] loc = { format.indexOf(121), format.lastIndexOf(121) };
/* 194 */     int startDateIdx = loc[0];
/*     */     
/*     */ 
/* 197 */     int endDateIdx = date.length() - (format.length() - 1 - (loc[1] - 1)) + 1;
/*     */     
/* 199 */     endDateIdx = endDateIdx > startDateIdx + 4 ? startDateIdx + 4 : endDateIdx;
/*     */     
/* 201 */     if (endDateIdx > date.length()) {
/* 202 */       endDateIdx = date.length();
/* 203 */       if (startDateIdx >= endDateIdx) {
/* 204 */         return -1;
/*     */       }
/*     */     }
/* 207 */     String str = format.substring(startDateIdx, loc[1] + 1);
/* 208 */     int year = -1;
/*     */     
/* 210 */     if (this.patternY.matcher(str).matches())
/*     */     {
/* 212 */       String strYear = date.substring(startDateIdx, endDateIdx);
/* 213 */       year = Integer.valueOf(strYear).intValue();
/*     */     }
/* 215 */     return year;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Calendar yearTypeTransformToAD(String data, String checkRule, boolean yearType)
/*     */   {
/* 227 */     SimpleDateFormat sdf = new SimpleDateFormat(checkRule);
/* 228 */     Date date = new Date();
/*     */     try
/*     */     {
/* 231 */       date = new Timestamp(sdf.parse(data).getTime());
/*     */     } catch (ParseException e) {
/* 233 */       throw new OpdException(ErrorCodeEnum.ERR_3000001_EXCEPTION, new String[] { data, checkRule });
/*     */     }
/*     */     
/* 236 */     int year = getInt(data, checkRule);
/* 237 */     if (year < 0) {
/* 238 */       throw new OpdException(ErrorCodeEnum.ERR_3000002_EXCEPTION, new String[] { data, checkRule });
/*     */     }
/* 240 */     if (yearType) {
/* 241 */       year += 1911;
/*     */     }
/* 243 */     Calendar calendar = Calendar.getInstance();
/* 244 */     calendar.setTime(date);
/* 245 */     calendar.set(1, year);
/*     */     
/* 247 */     return calendar;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\check\impl\NumberFieldExecutorImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */