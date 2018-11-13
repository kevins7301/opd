/*     */ package com.iisi.common.util;

/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;

/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;

/*     */
/*     */ import info.monitorenter.cpdetector.io.ASCIIDetector;
/*     */ import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
/*     */ import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
/*     */ import info.monitorenter.cpdetector.io.JChardetFacade;
/*     */ import info.monitorenter.cpdetector.io.ParsingDetector;
/*     */ import info.monitorenter.cpdetector.io.UnicodeDetector;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ @Component
/*     */ public class GuessEncodeUtils
/*     */ {
    /*  24 */ private static final Logger logger = LoggerFactory.getLogger(GuessEncodeUtils.class);
    /*     */ private CodePageDetector codePageDetector;

    /*     */
    /*     */ public GuessEncodeUtils()
    /*     */ {
        /*  29 */ this.codePageDetector = new CodePageDetector();
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public Charset guessEncodeForStream(InputStream in, int length)
    /*     */ {
        /*     */ try
        /*     */ {
            /*  39 */ return this.codePageDetector.getCodepageDetectorProxy().detectCodepage(in, length);
            /*     */ } catch (Exception e) {
            /*  41 */ logger.error("GuessEncodeUtils.guessEncodeForStream error{} ", e.getCause());
        }
        /*  42 */ return null;
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
    /*     */ class CodePageDetector
    /*     */ {
        /*     */ private CodepageDetectorProxy detector;

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
        /*     */ public CodePageDetector()
        /*     */ {
            /*  69 */ this.detector = CodepageDetectorProxy.getInstance();
            /*     */
            /*     */
            /*  72 */ this.detector.add(new ByteOrderMarkDetector());
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*  78 */ this.detector.add(new ParsingDetector(false));
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*  88 */ this.detector.add(JChardetFacade.getInstance());
            /*     */
            /*  90 */ this.detector.add(ASCIIDetector.getInstance());
            /*     */
            /*  92 */ this.detector.add(UnicodeDetector.getInstance());
            /*     */ }

        /*     */
        /*     */
        /*     */
        /*     */
        /*     */ public CodepageDetectorProxy getCodepageDetectorProxy()
        /*     */ {
            /* 100 */ return this.detector;
            /*     */ }
        /*     */ }
    /*     */ }
