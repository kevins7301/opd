/*    */ package com.iisi.common.util;

/*    */
/*    */ import java.util.List;

/*    */ import org.springframework.stereotype.Component;

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
/*    */
/*    */ @Component
/*    */ public class PagerUtils
/*    */ {
    /*    */ public Pager transationFromListToPager(List<?> list, Pager pager)
    /*    */ {
        /* 22 */ if ((list == null) || (list.size() == 0)) {
            /* 23 */ pager.setTotalRows(0);
            /* 24 */ return pager.clone();
            /*    */ }
        /*    */
        /* 27 */ pager.setTotalRows(list.size());
        /* 28 */ Pager newPager = pager.clone();
        /* 29 */ int startIdx = newPager.getCurrentStartRowIndex() - 1;
        /* 30 */ int endIdx = newPager.getCurrentEndRowIndex();
        /*    */
        /*    */
        /* 33 */ newPager.setDataObj(list.subList(startIdx, endIdx));
        /* 34 */ return newPager;
        /*    */ }
    /*    */ }