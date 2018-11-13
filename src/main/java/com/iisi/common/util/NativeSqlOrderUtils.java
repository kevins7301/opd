/*    */ package com.iisi.common.util;

/*    */
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.criterion.CriteriaQuery;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.util.StringHelper;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class NativeSqlOrderUtils/*    */ extends Order
/*    */ {
    /*    */ private static final long serialVersionUID = -7661733772888919852L;
    /*    */ private String sql;
    /* 18 */ private boolean asc = false;

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */ protected NativeSqlOrderUtils(String propertyName, boolean ascending)
    /*    */ {
        /* 26 */ super(propertyName, ascending);
        /* 27 */ this.sql = propertyName;
        /* 28 */ this.asc = ascending;
        /*    */ }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */ public static NativeSqlOrderUtils asc(String propertyName)
    /*    */ {
        /* 37 */ return new NativeSqlOrderUtils(propertyName, true);
        /*    */ }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */ public static NativeSqlOrderUtils desc(String propertyName)
    /*    */ {
        /* 46 */ return new NativeSqlOrderUtils(propertyName, false);
        /*    */ }

    /*    */
    /*    */
    /*    */
    /*    */ @Override
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)/*    */ throws HibernateException
    /*    */ {
        /* 54 */ StringBuilder fragment = new StringBuilder();
        /* 55 */ fragment.append("(" + this.sql + ")");
        /* 56 */ fragment.append(this.asc ? " asc" : " desc");
        /* 57 */ return StringHelper.replace(fragment.toString(), "{alias}", criteriaQuery.getSQLAlias(criteria));
        /*    */ }
    /*    */ }
