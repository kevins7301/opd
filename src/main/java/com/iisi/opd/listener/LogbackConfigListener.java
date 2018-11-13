/*    */ package com.iisi.opd.listener;
/*    */ 
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.classic.joran.JoranConfigurator;
/*    */ import ch.qos.logback.core.joran.spi.JoranException;
/*    */ import com.iisi.common.util.TempFileUtils;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogbackConfigListener
/*    */   implements ServletContextListener
/*    */ {
/* 22 */   private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);
/*    */   private static final String CONFIG_LOCATION = "logbackConfigLocation";
/*    */   
/*    */   public void contextInitialized(ServletContextEvent event)
/*    */   {
/* 27 */     String logbackConfigLocation = event.getServletContext().getInitParameter("logbackConfigLocation");
/* 28 */     String fn = event.getServletContext().getRealPath(logbackConfigLocation);
/*    */     try {
/* 30 */       LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
/* 31 */       loggerContext.reset();
/* 32 */       JoranConfigurator joranConfigurator = new JoranConfigurator();
/* 33 */       joranConfigurator.setContext(loggerContext);
/* 34 */       joranConfigurator.doConfigure(fn);
/* 35 */       logger.debug("loaded slf4j configure file from {}", fn);
/*    */     } catch (JoranException e) {
/* 37 */       logger.error("can't loading slf4j configure file from " + fn, e);
/*    */     }
/*    */     
/*    */ 
/* 41 */     new TempFileUtils().clearTmpDirectory();
/*    */   }
/*    */   
/*    */   public void contextDestroyed(ServletContextEvent event) {}
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\listener\LogbackConfigListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */