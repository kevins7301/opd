/*     */ package com.iisi.opd.data.in.check.impl;
/*     */ 
/*     */ import com.iisi.opd.data.in.check.FieldExecutor;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
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
/*     */ public class StringFieldExecutorImpl
/*     */   implements FieldExecutor
/*     */ {
/*  23 */   public final Logger log = LoggerFactory.getLogger(getClass());
/*     */   
/*  25 */   private final Pattern patternRange = Pattern.compile("^([0-9]+),([0-9]+)$");
/*  26 */   private final Pattern patternNotEmpty = Pattern.compile("\\S+");
/*  27 */   private final Pattern patternY = Pattern.compile("^(y)+$");
/*  28 */   private final Pattern patternInteger = Pattern.compile("\\d+");
/*     */   
/*  30 */   private boolean TAIWAN = true;
/*  31 */   private boolean INTERNATIONAL = false;
/*     */   
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
/*  66 */     boolean isMatchRangePattern = isPatternMatch(this.patternRange, rule);
/*  67 */     if (isMatchRangePattern) {
/*  68 */       String[] rules = rule.split(",");
/*  69 */       if ((data.length() >= Integer.valueOf(rules[0]).intValue()) && (data.length() <= Integer.valueOf(rules[1]).intValue()))
/*     */       {
/*  71 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  75 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDate(String data, String rule)
/*     */   {
/*  85 */     if (data == null)
/*  86 */       return false;
/*  87 */     yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*  88 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateTW(String data, String rule)
/*     */   {
/*  98 */     if (data == null)
/*  99 */       return false;
/* 100 */     yearTypeTransformToAD(data, rule, this.TAIWAN);
/* 101 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateBeforeNow(String data, String rule)
/*     */   {
/* 111 */     if (data == null)
/* 112 */       return false;
/* 113 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*     */     
/* 115 */     return calendar.getTime().before(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateBeforeNowTW(String data, String rule)
/*     */   {
/* 125 */     if (data == null)
/* 126 */       return false;
/* 127 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.TAIWAN);
/*     */     
/* 129 */     return calendar.getTime().before(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateAfterNow(String data, String rule)
/*     */   {
/* 139 */     if (data == null)
/* 140 */       return false;
/* 141 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.INTERNATIONAL);
/*     */     
/* 143 */     return calendar.getTime().after(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDateAfterNowTW(String data, String rule)
/*     */   {
/* 153 */     if (data == null)
/* 154 */       return false;
/* 155 */     Calendar calendar = yearTypeTransformToAD(data, rule, this.TAIWAN);
/*     */     
/* 157 */     return calendar.getTime().after(generateNowDate());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Date generateNowDate()
/*     */   {
/* 165 */     return new Date();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isPatternMatch(Pattern pattern, String source)
/*     */   {
/* 175 */     return pattern.matcher(source).matches();
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
/* 186 */     int[] loc = { format.indexOf(121), format.lastIndexOf(121) };
/* 187 */     int startDateIdx = loc[0];
/*     */     
/*     */ 
/* 190 */     int endDateIdx = date.length() - (format.length() - 1 - (loc[1] - 1)) + 1;
/*     */     
/* 192 */     endDateIdx = endDateIdx > startDateIdx + 4 ? startDateIdx + 4 : endDateIdx;
/*     */     
/* 194 */     if (endDateIdx > date.length()) {
/* 195 */       endDateIdx = date.length();
/* 196 */       if (startDateIdx >= endDateIdx) {
/* 197 */         return -1;
/*     */       }
/*     */     }
/* 200 */     String str = format.substring(startDateIdx, loc[1] + 1);
/* 201 */     int year = -1;
/*     */     
/* 203 */     if (this.patternY.matcher(str).matches())
/*     */     {
/* 205 */       String strYear = date.substring(startDateIdx, endDateIdx);
/* 206 */       boolean isNumber = false;
/*     */       
/* 208 */       while (!(isNumber = this.patternInteger.matcher(strYear).matches()))
/*     */       {
/* 210 */         if (strYear.length() <= 1) {
/*     */           break;
/*     */         }
/*     */         
/* 214 */         strYear = strYear.substring(0, strYear.length() - 1);
/*     */       }
/* 216 */       if (isNumber)
/* 217 */         year = Integer.valueOf(strYear).intValue();
/*     */     }
/* 219 */     return year;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Calendar yearTypeTransformToAD(String data, String checkRule, boolean yearType)
/*     */   {
/* 230 */     SimpleDateFormat sdf = new SimpleDateFormat(checkRule);
/* 231 */     Date date = new Date();
/*     */     try
/*     */     {
/* 234 */       date = new Timestamp(sdf.parse(data).getTime());
/*     */     } catch (ParseException e) {
/* 236 */       throw new OpdException(ErrorCodeEnum.ERR_3000001_EXCEPTION, new String[] { data, checkRule });
/*     */     }
/*     */     
/* 239 */     int year = getInt(data, checkRule);
/* 240 */     if (year < 0) {
/* 241 */       throw new OpdException(ErrorCodeEnum.ERR_3000002_EXCEPTION, new String[] { data, checkRule });
/*     */     }
/* 243 */     if (yearType) {
/* 244 */       year += 1911;
/*     */     }
/* 246 */     Calendar calendar = Calendar.getInstance();
/* 247 */     calendar.setTime(date);
/* 248 */     calendar.set(1, year);
/*     */     
/* 250 */     return calendar;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\check\impl\StringFieldExecutorImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */