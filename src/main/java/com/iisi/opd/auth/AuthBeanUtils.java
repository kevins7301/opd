/*     */ package com.iisi.opd.auth;
/*     */ 
/*     */ import com.iisi.opd.auth.dto.UnitDto;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.ui.ModelMap;
/*     */ 
/*     */ public class AuthBeanUtils
/*     */ {
/*  14 */   public static Logger log = org.slf4j.LoggerFactory.getLogger(AuthBeanUtils.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMethodSignature(Method methodInfo)
/*     */   {
/*  22 */     Class[] params = methodInfo.getParameterTypes();
/*  23 */     StringBuffer sb = new StringBuffer();
/*  24 */     sb.append(methodInfo.getClass().getCanonicalName());
/*  25 */     sb.append(".").append(methodInfo.getName()).append("(");
/*  26 */     for (Class param : params) {
/*  27 */       sb.append(param.getName()).append(",");
/*     */     }
/*  29 */     sb.deleteCharAt(sb.length() - 1);
/*  30 */     sb.append(")");
/*  31 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean ancestorOf(UnitDto parent, UnitDto child)
/*     */   {
/*  41 */     boolean back = false;
/*  42 */     if ((parent != null) && (child != null)) {
/*  43 */       if (parent.equals(child.getParent())) {
/*  44 */         back = true;
/*     */       }
/*  46 */       else if (child.isRoot()) {
/*  47 */         back = false;
/*     */       } else {
/*  49 */         UnitDto parentOdChild = child.getParent();
/*  50 */         back = ancestorOf(parent, parentOdChild);
/*     */       }
/*     */     }
/*     */     
/*  54 */     return back;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isMonitorUnit(UnitDto monitor, UnitDto child)
/*     */   {
/*  64 */     boolean back = false;
/*  65 */     if ((monitor != null) && (child != null)) {
/*  66 */       for (UnitDto u : child.getOtherMonitorUnits()) {
/*  67 */         if (monitor.equals(u)) {
/*  68 */           back = true;
/*     */         }
/*     */       }
/*     */     }
/*  72 */     return back;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void printModelMapInfo(ModelMap model)
/*     */   {
/*  80 */     log.info("model裡面有" + model.size() + "個東西");
/*     */     
/*  82 */     java.util.Set keys = model.keySet();
/*  83 */     for (Object key : keys) {
/*  84 */       log.info((String)key);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ArrayList<Class<?>> getClassesForPackage(Package pkg)
/*     */   {
/*  94 */     String pkgname = pkg.getName();
/*  95 */     ArrayList<Class<?>> classes = new ArrayList();
/*     */     
/*  97 */     File directory = null;
/*     */     
/*  99 */     String relPath = pkgname.replace('.', '/');
/* 100 */     log.info("ClassDiscovery: Package: " + pkgname + " becomes Path:" + relPath);
/* 101 */     URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
/* 102 */     log.info("ClassDiscovery: Resource = " + resource);
/* 103 */     if (resource == null) {
/* 104 */       throw new RuntimeException("No resource for " + relPath);
/*     */     }
/* 106 */     String fullPath = resource.getFile();
/* 107 */     log.info("ClassDiscovery: FullPath = " + fullPath);
/*     */     try
/*     */     {
/* 110 */       directory = new File(resource.toURI());
/*     */     } catch (URISyntaxException e) {
/* 112 */       throw new RuntimeException(pkgname + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
/*     */     } catch (IllegalArgumentException e) {
/* 114 */       directory = null;
/*     */     }
/* 116 */     log.info("ClassDiscovery: Directory = " + directory);
/*     */     
/* 118 */     if ((directory != null) && (directory.exists()))
/*     */     {
/* 120 */       String[] files = directory.list();
/* 121 */       for (int i = 0; i < files.length; i++)
/*     */       {
/* 123 */         if (files[i].endsWith(".class"))
/*     */         {
/* 125 */           String className = pkgname + '.' + files[i].substring(0, files[i].length() - 6);
/* 126 */           log.info("ClassDiscovery: className = " + className);
/*     */           try {
/* 128 */             classes.add(Class.forName(className));
/*     */           } catch (ClassNotFoundException e) {
/* 130 */             throw new RuntimeException("ClassNotFoundException loading " + className);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 136 */     return classes;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\AuthBeanUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */