/*     */ package com.iisi.opd.exception.msg;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class ErrorCodeEnum
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 400809445413800153L;
/*  26 */   public static final ErrorCodeEnum DEFAULT_EXCEPTION = new ErrorCodeEnum("1000000");
/*     */   
/*     */ 
/*  29 */   public static final ErrorCodeEnum ERR_1000100_EXCEPTION = new ErrorCodeEnum("1000100");
/*     */   
/*     */ 
/*  32 */   public static final ErrorCodeEnum ERR_1000500_EXCEPTION = new ErrorCodeEnum("1000500");
/*     */   
/*     */ 
/*  35 */   public static final ErrorCodeEnum ERR_1000600_EXCEPTION = new ErrorCodeEnum("1000600");
/*     */   
/*     */ 
/*  38 */   public static final ErrorCodeEnum ERR_1000700_EXCEPTION = new ErrorCodeEnum("1000700");
/*     */   
/*     */ 
/*  41 */   public static final ErrorCodeEnum ERR_1000800_EXCEPTION = new ErrorCodeEnum("1000800");
/*     */   
/*     */ 
/*  44 */   public static final ErrorCodeEnum ERR_1000900_EXCEPTION = new ErrorCodeEnum("1000900");
/*     */   
/*     */ 
/*  47 */   public static final ErrorCodeEnum ERR_1001000_EXCEPTION = new ErrorCodeEnum("1001000");
/*     */   
/*     */ 
/*  50 */   public static final ErrorCodeEnum ERR_1001100_EXCEPTION = new ErrorCodeEnum("1001100");
/*     */   
/*     */ 
/*  53 */   public static final ErrorCodeEnum ERR_1001200_EXCEPTION = new ErrorCodeEnum("1001200");
/*     */   
/*     */ 
/*  56 */   public static final ErrorCodeEnum ERR_1001300_EXCEPTION = new ErrorCodeEnum("1001300");
/*     */   
/*     */ 
/*  59 */   public static final ErrorCodeEnum ERR_1001301_EXCEPTION = new ErrorCodeEnum("1001301");
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
/*  70 */   public static final ErrorCodeEnum ERR_2010001_EXCEPTION = new ErrorCodeEnum("2010001");
/*     */   
/*  72 */   public static final ErrorCodeEnum ERR_2010002_EXCEPTION = new ErrorCodeEnum("2010002");
/*     */   
/*  74 */   public static final ErrorCodeEnum ERR_2011001_EXCEPTION = new ErrorCodeEnum("2011001");
/*     */   
/*  76 */   public static final ErrorCodeEnum ERR_2012001_EXCEPTION = new ErrorCodeEnum("2012001");
/*     */   
/*  78 */   public static final ErrorCodeEnum ERR_2013001_EXCEPTION = new ErrorCodeEnum("2013001");
/*     */   
/*  80 */   public static final ErrorCodeEnum ERR_2014001_EXCEPTION = new ErrorCodeEnum("2014001");
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
/*  91 */   public static final ErrorCodeEnum ERR_2020001_EXCEPTION = new ErrorCodeEnum("2020001");
/*     */   
/*  93 */   public static final ErrorCodeEnum ERR_2020002_EXCEPTION = new ErrorCodeEnum("2020002");
/*     */   
/*  95 */   public static final ErrorCodeEnum ERR_2020003_EXCEPTION = new ErrorCodeEnum("2020003");
/*     */   
/*  97 */   public static final ErrorCodeEnum ERR_2020004_EXCEPTION = new ErrorCodeEnum("2020004");
/*     */   
/*  99 */   public static final ErrorCodeEnum ERR_2020005_EXCEPTION = new ErrorCodeEnum("2020005");
/*     */   
/*     */ 
/*     */ 
/* 103 */   public static final ErrorCodeEnum ERR_2030001_EXCEPTION = new ErrorCodeEnum("2030001");
/*     */   
/* 105 */   public static final ErrorCodeEnum ERR_2030002_EXCEPTION = new ErrorCodeEnum("2030002");
/*     */   
/*     */ 
/*     */ 
/* 109 */   public static final ErrorCodeEnum ERR_2040001_EXCEPTION = new ErrorCodeEnum("2040001");
/*     */   
/* 111 */   public static final ErrorCodeEnum ERR_2040002_EXCEPTION = new ErrorCodeEnum("2040002");
/*     */   
/*     */ 
/*     */ 
/* 115 */   public static final ErrorCodeEnum ERR_2060001_EXCEPTION = new ErrorCodeEnum("2060001");
/*     */   
/* 117 */   public static final ErrorCodeEnum ERR_2060002_EXCEPTION = new ErrorCodeEnum("2060002");
/*     */   
/* 119 */   public static final ErrorCodeEnum ERR_2060003_EXCEPTION = new ErrorCodeEnum("2060003");
/*     */   
/*     */ 
/*     */ 
/* 123 */   public static final ErrorCodeEnum ERR_2070001_EXCEPTION = new ErrorCodeEnum("2070001");
/*     */   
/* 125 */   public static final ErrorCodeEnum ERR_2070002_EXCEPTION = new ErrorCodeEnum("2070002");
/*     */   
/* 127 */   public static final ErrorCodeEnum ERR_2070003_EXCEPTION = new ErrorCodeEnum("2070003");
/*     */   
/* 129 */   public static final ErrorCodeEnum ERR_2070004_EXCEPTION = new ErrorCodeEnum("2070004");
/*     */   
/* 131 */   public static final ErrorCodeEnum ERR_2070005_EXCEPTION = new ErrorCodeEnum("2070005");
/*     */   
/* 133 */   public static final ErrorCodeEnum ERR_2070006_EXCEPTION = new ErrorCodeEnum("2070006");
/*     */   
/* 135 */   public static final ErrorCodeEnum ERR_2070007_EXCEPTION = new ErrorCodeEnum("2070007");
/*     */   
/* 137 */   public static final ErrorCodeEnum ERR_2070008_EXCEPTION = new ErrorCodeEnum("2070008");
/*     */   
/* 139 */   public static final ErrorCodeEnum ERR_2070009_EXCEPTION = new ErrorCodeEnum("2070009");
/*     */   
/* 141 */   public static final ErrorCodeEnum ERR_2070010_EXCEPTION = new ErrorCodeEnum("2070010");
/*     */   
/* 143 */   public static final ErrorCodeEnum ERR_2070011_EXCEPTION = new ErrorCodeEnum("2070011");
/*     */   
/* 145 */   public static final ErrorCodeEnum ERR_2070012_EXCEPTION = new ErrorCodeEnum("2070012");
/*     */   
/* 147 */   public static final ErrorCodeEnum ERR_2070013_EXCEPTION = new ErrorCodeEnum("2070013");
/*     */   
/* 149 */   public static final ErrorCodeEnum ERR_2070014_EXCEPTION = new ErrorCodeEnum("2070014");
/*     */   
/* 151 */   public static final ErrorCodeEnum ERR_2070015_EXCEPTION = new ErrorCodeEnum("2070015");
/*     */   
/* 153 */   public static final ErrorCodeEnum ERR_2070016_EXCEPTION = new ErrorCodeEnum("2070016");
/*     */   
/* 155 */   public static final ErrorCodeEnum ERR_2070017_EXCEPTION = new ErrorCodeEnum("2070017");
/*     */   
/* 157 */   public static final ErrorCodeEnum ERR_2070018_EXCEPTION = new ErrorCodeEnum("2070018");
/*     */   
/* 159 */   public static final ErrorCodeEnum ERR_2070019_EXCEPTION = new ErrorCodeEnum("2070019");
/*     */   
/* 161 */   public static final ErrorCodeEnum ERR_2070020_EXCEPTION = new ErrorCodeEnum("2070020");
/*     */   
/* 163 */   public static final ErrorCodeEnum ERR_2070021_EXCEPTION = new ErrorCodeEnum("2070021");
/*     */   
/* 165 */   public static final ErrorCodeEnum ERR_2070022_EXCEPTION = new ErrorCodeEnum("2070022");
/*     */   
/* 167 */   public static final ErrorCodeEnum ERR_2070023_EXCEPTION = new ErrorCodeEnum("2070023");
/*     */   
/* 169 */   public static final ErrorCodeEnum ERR_2070024_EXCEPTION = new ErrorCodeEnum("2070024");
/*     */   
/* 171 */   public static final ErrorCodeEnum ERR_2070025_EXCEPTION = new ErrorCodeEnum("2070025");
/*     */   
/* 173 */   public static final ErrorCodeEnum ERR_2070026_EXCEPTION = new ErrorCodeEnum("2070026");
/*     */   
/* 175 */   public static final ErrorCodeEnum ERR_2070027_EXCEPTION = new ErrorCodeEnum("2070027");
/*     */   
/* 177 */   public static final ErrorCodeEnum ERR_2070028_EXCEPTION = new ErrorCodeEnum("2070028");
/*     */   
/* 179 */   public static final ErrorCodeEnum ERR_2070029_EXCEPTION = new ErrorCodeEnum("2070029");
/*     */   
/* 181 */   public static final ErrorCodeEnum ERR_2070030_EXCEPTION = new ErrorCodeEnum("2070030");
/*     */   
/* 183 */   public static final ErrorCodeEnum ERR_2070031_EXCEPTION = new ErrorCodeEnum("2070031");
/*     */   
/* 185 */   public static final ErrorCodeEnum ERR_2070032_EXCEPTION = new ErrorCodeEnum("2070032");
/*     */   
/* 187 */   public static final ErrorCodeEnum ERR_2070033_EXCEPTION = new ErrorCodeEnum("2070033");
/*     */   
/* 189 */   public static final ErrorCodeEnum ERR_2070034_EXCEPTION = new ErrorCodeEnum("2070034");
/*     */   
/*     */ 
/*     */ 
/* 193 */   public static final ErrorCodeEnum ERR_2080000_EXCEPTION = new ErrorCodeEnum("2080000");
/*     */   
/* 195 */   public static final ErrorCodeEnum ERR_2080001_EXCEPTION = new ErrorCodeEnum("2080001");
/*     */   
/* 197 */   public static final ErrorCodeEnum ERR_2080002_EXCEPTION = new ErrorCodeEnum("2080002");
/*     */   
/* 199 */   public static final ErrorCodeEnum ERR_2080003_EXCEPTION = new ErrorCodeEnum("2080003");
/*     */   
/* 201 */   public static final ErrorCodeEnum ERR_2080004_EXCEPTION = new ErrorCodeEnum("2080004");
/*     */   
/* 203 */   public static final ErrorCodeEnum ERR_2080005_EXCEPTION = new ErrorCodeEnum("2080005");
/*     */   
/* 205 */   public static final ErrorCodeEnum ERR_2080006_EXCEPTION = new ErrorCodeEnum("2080006");
/*     */   
/* 207 */   public static final ErrorCodeEnum ERR_2080007_EXCEPTION = new ErrorCodeEnum("2080007");
/*     */   
/* 209 */   public static final ErrorCodeEnum ERR_2080008_EXCEPTION = new ErrorCodeEnum("2080008");
/*     */   
/* 211 */   public static final ErrorCodeEnum ERR_2080009_EXCEPTION = new ErrorCodeEnum("2080009");
/*     */   
/* 213 */   public static final ErrorCodeEnum ERR_2080010_EXCEPTION = new ErrorCodeEnum("2080010");
/*     */   
/* 215 */   public static final ErrorCodeEnum ERR_2080011_EXCEPTION = new ErrorCodeEnum("2080011");
/*     */   
/* 217 */   public static final ErrorCodeEnum ERR_2080012_EXCEPTION = new ErrorCodeEnum("2080012");
/*     */   
/* 219 */   public static final ErrorCodeEnum ERR_2080013_EXCEPTION = new ErrorCodeEnum("2080013");
/*     */   
/* 221 */   public static final ErrorCodeEnum ERR_2080014_EXCEPTION = new ErrorCodeEnum("2080014");
/*     */   
/* 223 */   public static final ErrorCodeEnum ERR_2080015_EXCEPTION = new ErrorCodeEnum("2080015");
/*     */   
/* 225 */   public static final ErrorCodeEnum ERR_2080016_EXCEPTION = new ErrorCodeEnum("2080016");
/*     */   
/* 227 */   public static final ErrorCodeEnum ERR_2080017_EXCEPTION = new ErrorCodeEnum("2080017");
/*     */   
/* 229 */   public static final ErrorCodeEnum ERR_2080018_EXCEPTION = new ErrorCodeEnum("2080018");
/*     */   
/* 231 */   public static final ErrorCodeEnum ERR_2080019_EXCEPTION = new ErrorCodeEnum("2080019");
/*     */   
/*     */ 
/*     */ 
/* 235 */   public static final ErrorCodeEnum ERR_3000000_EXCEPTION = new ErrorCodeEnum("3000000");
/*     */   
/* 237 */   public static final ErrorCodeEnum ERR_3000001_EXCEPTION = new ErrorCodeEnum("3000001");
/*     */   
/* 239 */   public static final ErrorCodeEnum ERR_3000002_EXCEPTION = new ErrorCodeEnum("3000002");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 248 */   public static final ErrorCodeEnum VEW_9000000_EXCEPTION = new ErrorCodeEnum("9000000");
/*     */   
/*     */ 
/* 251 */   public static final ErrorCodeEnum VEW_9001000_EXCEPTION = new ErrorCodeEnum("9001000");
/*     */   
/*     */ 
/*     */   private final String s;
/*     */   
/*     */ 
/*     */ 
/*     */   private ErrorCodeEnum(String s)
/*     */   {
/* 260 */     this.s = s;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 264 */     return this.s;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 268 */     return (this == obj) || (((obj instanceof ErrorCodeEnum)) && (((ErrorCodeEnum)obj).s.equals(this.s)));
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\ErrorCodeEnum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */