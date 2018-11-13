/*    */ package com.iisi.common.util;

/*    */
/*    */ import java.io.File;

/*    */ import org.apache.commons.io.FileUtils;
/*    */ import org.springframework.stereotype.Component;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @Component
/*    */ public class TempFileUtils
/*    */ {
    /* 15 */ private File tmpDir = new File(FileUtils.getTempDirectory(), "opd/tmp");

    /*    */
    /*    */ public TempFileUtils() {
        /* 18 */ removeTmpRootDirectory();
        /*    */ }

    /*    */
    /*    */ public File getTempDirectory(String key) {
        /* 22 */ return new File(this.tmpDir, String.valueOf(System.currentTimeMillis()));
        /*    */ }

    /*    */
    /*    */ public void clearTmpDirectory() {
        /* 26 */ FileUtils.deleteQuietly(this.tmpDir);
        /*    */ }

    /*    */
    /*    */ private void removeTmpRootDirectory() {
        /* 30 */ File tmpRoot = new File(FileUtils.getTempDirectory(), "opd");
        /* 31 */ if (tmpRoot.exists()) {
            /* 32 */ FileUtils.deleteQuietly(tmpRoot);
            /*    */ }
        /*    */ }
    /*    */ }
