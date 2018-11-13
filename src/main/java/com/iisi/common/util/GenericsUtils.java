/*    */ package com.iisi.common.util;

/*    */
/*    */ import java.lang.reflect.ParameterizedType;
/*    */ import java.lang.reflect.Type;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class GenericsUtils
/*    */ {
    /*    */ public static Class getSuperClassGenricType(Class clazz)
    /*    */ {
        /* 20 */ return getSuperClassGenricType(clazz, 0);
        /*    */ }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */ public static Class getSuperClassGenricType(Class clazz, int index)/*    */ throws IndexOutOfBoundsException
    /*    */ {
        /* 33 */ Type genType = clazz.getGenericSuperclass();
        /*    */
        /* 35 */ if (!(genType instanceof ParameterizedType)) {
            /* 36 */ return Object.class;
            /*    */ }
        /*    */
        /* 39 */ Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        /*    */
        /* 41 */ if ((index >= params.length) || (index < 0)) {
            /* 42 */ return Object.class;
            /*    */ }
        /* 44 */ if (!(params[index] instanceof Class)) {
            /* 45 */ return Object.class;
            /*    */ }
        /* 47 */ return (Class) params[index];
        /*    */ }
    /*    */ }
