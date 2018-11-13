package com.iisi.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;

@Component
public class ZipUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private static final int BUFFER_1KB = 1024;

    private static final int BUFFER_4KB = 4096;

    private byte[] compression(byte[] byteArr, String fileName) {
        if ((byteArr == null) || (byteArr.length == 0)) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }

        ByteArrayOutputStream btz = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(btz);

        try {
            zos.putNextEntry(new ZipEntry(fileName));
            zos.write(byteArr);
            zos.closeEntry();

            zos.close();
        } catch (Exception e) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
        }

        return btz.toByteArray();
    }

    private static byte[] compression(File srcFile, String fileName) {
        if ((srcFile == null) || (!srcFile.exists())) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }

        ByteArrayOutputStream btz = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(btz);

        byte[] buffer = new byte['Ѐ'];
        try {
            FileInputStream fis = new FileInputStream(srcFile);

            zos.putNextEntry(new ZipEntry(fileName));

            int length = 0;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer);
            }
            zos.closeEntry();

            fis.close();

            zos.close();
        } catch (Exception e) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
        }

        return btz.toByteArray();
    }

    private static void compression(File srcFile, File outputZipFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipOutputStream zos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(srcFile), 4096);
            bos = new BufferedOutputStream(new FileOutputStream(outputZipFile), 4096);
            zos = new ZipOutputStream(bos);

            zos.putNextEntry(new ZipEntry(srcFile.getName()));
            IOUtils.copyLarge(bis, zos);

            zos.closeEntry();

            zos.close();
            bos.close();

            bis.close();
        } catch (IOException e) {
            e =

                    e;
            logger.error("zip file error", e);
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        } catch (Exception e) {
            e = e;
            logger.error("zip file error", e);
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        } finally {
        }
    }

    private String md5(byte[] byteArr) {
        if ((byteArr == null) || (byteArr.length == 0)) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(byteArr);
        } catch (NoSuchAlgorithmException e) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
        }
        byte[] temp = digest.digest();
        return new String(Hex.encodeHex(temp));
    }

    private String md5(File outputZipFile) {
        BufferedInputStream bis = null;

        if ((outputZipFile == null) || (!outputZipFile.exists())) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            bis = new BufferedInputStream(new FileInputStream(outputZipFile), 4096);
            byte[] buffer = new byte['က'];

            while (bis.read(buffer) > 0) {
                digest.update(buffer);
            }
            byte[] temp = digest.digest();
            bis.close();
            String str = new String(Hex.encodeHex(temp));
            return str;
        } catch (NoSuchAlgorithmException e) {
            e = e;
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
        } catch (IOException e) {
            e = e;
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
        } finally {
        }
    }

    public Map<String, Object> zipProcess(byte[] byteArr, String fileName) {
        Map<String, Object> map = new HashMap();

        byte[] zipFile = compression(byteArr, fileName);
        if ((zipFile != null) && (zipFile.length != 0)) {
            map.put("file", zipFile);
        }
        String md5 = md5(zipFile);
        if ((md5 != null) && (md5.length() != 0)) {
            map.put("md5", md5);
        }
        return map;
    }

    @Deprecated
    public Map<String, Object> zipProcess(File srcFile, String fileName) {
        Map<String, Object> map = new HashMap();

        byte[] zipFile = compression(srcFile, fileName);
        if ((zipFile != null) && (zipFile.length != 0)) {
            map.put("file", zipFile);
        }
        String md5 = md5(zipFile);
        if ((md5 != null) && (md5.length() != 0)) {
            map.put("md5", md5);
        }
        return map;
    }

    public Map<String, Object> zipProcess(File inputSrcFile, File outputZipFile) {
        Map<String, Object> map = new HashMap();

        compression(inputSrcFile, outputZipFile);
        if ((outputZipFile != null) && (outputZipFile.exists())) {
            map.put("file", outputZipFile);
        }
        String md5 = md5(outputZipFile);
        if ((md5 != null) && (md5.length() != 0)) {
            map.put("md5", md5);
        }
        return map;
    }
}
