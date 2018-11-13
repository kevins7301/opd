/*     */ package com.iisi.common.util;

/*     */
/*     */ import org.springframework.stereotype.Component;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ @Component
/*     */ public class Pager
/*     */ {
    /*     */ private Object dataObj;
    /*  17 */ private int pageSize = 10;
    /*  18 */ private int currentPageIndex = 1;
    /*     */ private int currentStartRowIndex;
    /*     */ private int currentEndRowIndex;
    /*     */ private int totalRows;
    /*  22 */ private int totalPages = 1;

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Pager() {
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Pager(int pageSize)
    /*     */ {
        /*  34 */ setPageSize(pageSize);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Pager(int pageSize, int currentPageIndex)
    /*     */ {
        /*  42 */ setPageSize(pageSize);
        /*  43 */ setCurrentPageIndex(currentPageIndex);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Pager(int pageSize, int currentPageIndex, int totalRows)
    /*     */ {
        /*  52 */ setPageSize(pageSize);
        /*  53 */ setCurrentPageIndex(currentPageIndex);
        /*  54 */ setTotalRows(totalRows);
        /*     */ }

    /*     */
    /*     */ private void reflash() {
        /*  58 */ int maxPage = getTotalPages();
        /*  59 */ if (this.totalRows != 0) {
            /*  60 */ if ((this.totalRows <= maxPage * this.pageSize) && (this.currentPageIndex <= maxPage)
                    && (this.currentPageIndex > 0)) {
                /*  61 */ this.currentStartRowIndex = ((this.currentPageIndex - 1) * this.pageSize + 1);
                /*  62 */ if ((this.currentEndRowIndex = this.currentStartRowIndex + (this.pageSize - 1)) > this.totalRows)
                    /*  63 */ this.currentEndRowIndex = this.totalRows;
                /*     */ } else {
                /*  65 */ this.currentPageIndex = 1;
                /*  66 */ this.currentStartRowIndex = 1;
                /*  67 */ this.currentEndRowIndex = (this.pageSize > this.totalRows ? this.totalRows : this.pageSize);
                /*     */ }
            /*     */ } else {
            /*  70 */ this.currentPageIndex = 1;
            /*  71 */ this.currentStartRowIndex = 0;
            /*  72 */ this.currentEndRowIndex = 0;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Object getDataObj()
    /*     */ {
        /*  81 */ return this.dataObj;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public void setDataObj(Object dataObj)
    /*     */ {
        /*  88 */ this.dataObj = dataObj;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getPageSize()
    /*     */ {
        /*  95 */ return this.pageSize;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public void setPageSize(int pageSize)
    /*     */ {
        /* 102 */ if (pageSize > 0) {
            /* 103 */ this.pageSize = pageSize;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getCurrentPageIndex()
    /*     */ {
        /* 111 */ return this.currentPageIndex;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void setCurrentPageIndex(int currentPageIndex)
    /*     */ {
        /* 121 */ this.currentPageIndex = currentPageIndex;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getCurrentStartRowIndex()
    /*     */ {
        /* 128 */ return this.currentStartRowIndex;
        /*     */ }

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
    /*     */ public int getCurrentEndRowIndex()
    /*     */ {
        /* 142 */ return this.currentEndRowIndex;
        /*     */ }

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
    /*     */ public int getTotalRows()
    /*     */ {
        /* 157 */ return this.totalRows;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public void setTotalRows(int totalRows)
    /*     */ {
        /* 164 */ this.totalRows = totalRows;
        /* 165 */ reflash();
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getTotalPages()
    /*     */ {
        /* 172 */ int lose = this.totalRows % this.pageSize;
        /* 173 */ if (lose == 0)
        /*     */ {
            /* 175 */ if (this.totalRows == 0) {
                /* 176 */ this.totalPages = 1;
                /*     */ }
            /*     */ else {
                /* 179 */ this.totalPages = (this.totalRows / this.pageSize);
                /*     */ }
            /*     */ }
        /*     */ else {
            /* 183 */ this.totalPages = (this.totalRows / this.pageSize + 1);
            /*     */ }
        /* 185 */ return this.totalPages;
        /*     */ }

    /*     */
    /* 188 */ @Override
    public Pager clone() {
        Pager newPager = new Pager(this.pageSize, this.currentPageIndex, this.totalRows);
        /* 189 */ return newPager;
        /*     */ }
    /*     */ }
