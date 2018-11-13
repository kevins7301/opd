package com.iisi.opd.exception.util;

import org.springframework.context.ApplicationContext;

public class AppContext {
    private static final ThreadLocal<ApplicationContext> threadLocalApplicationContext = new ThreadLocal();

    public static void setApplicationContext(ApplicationContext applicationContext) {
        threadLocalApplicationContext.set(applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        return (ApplicationContext) threadLocalApplicationContext.get();
    }
}
