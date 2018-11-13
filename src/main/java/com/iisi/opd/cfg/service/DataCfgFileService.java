package com.iisi.opd.cfg.service;

import java.io.File;

public abstract interface DataCfgFileService {
    public abstract void saveArgsByDataCfgOid(String paramString1, String paramString2, long paramLong, byte[] paramArrayOfByte);

    public abstract void saveArgsByDataCfgOid(String paramString1, String paramString2, long paramLong, File paramFile);
}
