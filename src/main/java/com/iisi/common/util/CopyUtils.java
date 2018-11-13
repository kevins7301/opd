/*     */ package com.iisi.common.util;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ import org.springframework.stereotype.Component;

/*     */
/*     */ import com.iisi.opd.cfg.po.DataCfgApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgFileApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgFilePo;
/*     */ import com.iisi.opd.cfg.po.DataCfgMetadataApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgMetadataPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgPo;
/*     */ import com.iisi.opd.cfg.po.DataFieldCfgApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataFieldCfgPo;
/*     */ import com.iisi.opd.cfg.po.DataSetApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataSetMetadataApplyPo;
/*     */ import com.iisi.opd.cfg.po.DataSetMetadataPo;
/*     */ import com.iisi.opd.cfg.po.DataSetPo;
/*     */ import com.iisi.opd.tag.po.DataTagPo;

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
/*     */
/*     */ @Component
/*     */ public class CopyUtils
/*     */ {
    /*     */ public DataCfgApplyPo copyProperties(DataCfgPo dataCfgPo)
    /*     */ {
        /*  36 */ DataCfgApplyPo dataCfgApplyPo = new DataCfgApplyPo();
        /*  37 */ dataCfgApplyPo.setName(dataCfgPo.getName());
        /*  38 */ dataCfgApplyPo.setDescription(dataCfgPo.getDescription());
        /*  39 */ dataCfgApplyPo.setStructured(dataCfgPo.isStructured());
        /*  40 */ dataCfgApplyPo.setEnable(dataCfgPo.isEnable());
        /*  41 */ dataCfgApplyPo.setPublic(dataCfgPo.isPublic());
        /*  42 */ dataCfgApplyPo.setNoPublicReason(dataCfgPo.getNoPublicReason());
        /*  43 */ dataCfgApplyPo.setStartTime(dataCfgPo.getStartTime());
        /*  44 */ dataCfgApplyPo.setEndTime(dataCfgPo.getEndTime());
        /*  45 */ dataCfgApplyPo.setDataSetPo(dataCfgPo.getDataSetPo());
        /*  46 */ dataCfgApplyPo.setDataCfgPo(dataCfgPo);
        /*     */
        /*     */
        /*  49 */ if (dataCfgPo.getDataCfgFilePo() != null) {
            /*  50 */ DataCfgFilePo dataCfgFilePo = dataCfgPo.getDataCfgFilePo();
            /*  51 */ DataCfgFileApplyPo dataCfgFileApplyPo = new DataCfgFileApplyPo();
            /*  52 */ dataCfgFileApplyPo.setContent(dataCfgFilePo.getContent());
            /*  53 */ dataCfgFileApplyPo.setDataCfgApplyPo(dataCfgApplyPo);
            /*  54 */ dataCfgFileApplyPo.setName(dataCfgFilePo.getName());
            /*  55 */ dataCfgFileApplyPo.setSize(dataCfgFilePo.getSize());
            /*  56 */ dataCfgApplyPo.setDataCfgFileApplyPo(dataCfgFileApplyPo);
            /*     */ }
        /*     */
        /*     */
        /*  60 */ List<DataFieldCfgApplyPo> dataFieldCfgApplyPoList = new ArrayList();
        /*  61 */ List<DataFieldCfgPo> dataFieldCfgPoList = dataCfgPo.getDataFieldCfgPoList();
        /*  62 */ for (int i = 0; i < dataFieldCfgPoList.size(); i++) {
            /*  63 */ DataFieldCfgPo ddataFieldCfgPo = (DataFieldCfgPo) dataFieldCfgPoList.get(i);
            /*  64 */ DataFieldCfgApplyPo dataFieldCfgApplyPo = new DataFieldCfgApplyPo();
            /*  65 */ dataFieldCfgApplyPo.setFieldName(ddataFieldCfgPo.getFieldName());
            /*  66 */ dataFieldCfgApplyPo.setDispName(ddataFieldCfgPo.getDispName());
            /*  67 */ dataFieldCfgApplyPo.setFieldOrder(ddataFieldCfgPo.getFieldOrder());
            /*  68 */ dataFieldCfgApplyPo.setFieldType(ddataFieldCfgPo.getFieldType());
            /*  69 */ dataFieldCfgApplyPo.setCheckRule(ddataFieldCfgPo.getCheckRule());
            /*  70 */ dataFieldCfgApplyPo.setCheckMethod(ddataFieldCfgPo.getCheckMethod());
            /*  71 */ dataFieldCfgApplyPo.setPublic(ddataFieldCfgPo.isPublic());
            /*  72 */ dataFieldCfgApplyPo.setDataCfgApplyPo(dataCfgApplyPo);
            /*  73 */ dataFieldCfgApplyPoList.add(dataFieldCfgApplyPo);
            /*     */ }
        /*  75 */ dataCfgApplyPo.setDataFieldCfgApplyPoList(dataFieldCfgApplyPoList);
        /*     */
        /*     */
        /*  78 */ List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList = new ArrayList();
        /*  79 */ List<DataCfgMetadataPo> dataCfgMetadataPoList = dataCfgPo.getDataCfgMetadataPoList();
        /*  80 */ for (int i = 0; i < dataCfgMetadataPoList.size(); i++) {
            /*  81 */ DataCfgMetadataPo dataCfgMetadataPo = (DataCfgMetadataPo) dataCfgMetadataPoList.get(i);
            /*  82 */ DataCfgMetadataApplyPo DataCfgMetadataApplyPo = new DataCfgMetadataApplyPo();
            /*  83 */ DataCfgMetadataApplyPo.setMetadataKey(dataCfgMetadataPo.getMetadataKey());
            /*  84 */ DataCfgMetadataApplyPo.setMetadataValue(dataCfgMetadataPo.getMetadataValue());
            /*  85 */ DataCfgMetadataApplyPo.setCommon(dataCfgMetadataPo.isCommon());
            /*  86 */ DataCfgMetadataApplyPo.setDataCfgApplyPo(dataCfgApplyPo);
            /*  87 */ dataCfgMetadataApplyPoList.add(DataCfgMetadataApplyPo);
            /*     */ }
        /*  89 */ dataCfgApplyPo.setDataCfgMetadataApplyPoList(dataCfgMetadataApplyPoList);
        /*  90 */ return dataCfgApplyPo;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public DataSetApplyPo copyProperties(DataSetPo dataSetPo)
    /*     */ {
        /*  99 */ DataSetApplyPo dataSetApplyPo = new DataSetApplyPo();
        /* 100 */ dataSetApplyPo.setName(dataSetPo.getName());
        /* 101 */ dataSetApplyPo.setEnable(dataSetPo.isEnable());
        /* 102 */ dataSetApplyPo.setPublic(dataSetPo.isPublic());
        /* 103 */ dataSetApplyPo.setNoPublicReason(dataSetPo.getNoPublicReason());
        /* 104 */ dataSetApplyPo.setDescription(dataSetPo.getDescription());
        /* 105 */ dataSetApplyPo.setDataCategoryPo(dataSetPo.getDataCategoryPo());
        /* 106 */ dataSetApplyPo.setUnitPo(dataSetPo.getUnitPo());
        /* 107 */ dataSetApplyPo.setDataSetPo(dataSetPo);
        /*     */
        /*     */
        /* 110 */ List<DataTagPo> sourceTagList = dataSetPo.getDataTagPoList();
        /* 111 */ List<DataTagPo> applyTagList = dataSetApplyPo.getDataTagPoList();
        /* 112 */ applyTagList.clear();
        /* 113 */ for (DataTagPo dataTagPo : sourceTagList) {
            /* 114 */ applyTagList.add(dataTagPo);
            /*     */ }
        /*     */
        /*     */
        /* 118 */ List<DataSetMetadataApplyPo> dataSetMetadataApplyPoList = new ArrayList();
        /* 119 */ List<DataSetMetadataPo> sourceList = dataSetPo.getDataSetMetadataPoList();
        /* 120 */ for (int i = 0; i < sourceList.size(); i++) {
            /* 121 */ DataSetMetadataPo dataSetMetadataPo = (DataSetMetadataPo) sourceList.get(i);
            /* 122 */ DataSetMetadataApplyPo dataSetMetadataApplyPo = new DataSetMetadataApplyPo();
            /* 123 */ dataSetMetadataApplyPo.setCommon(dataSetMetadataPo.isCommon());
            /* 124 */ dataSetMetadataApplyPo.setDataSetApplyPo(dataSetApplyPo);
            /* 125 */ dataSetMetadataApplyPo.setMetadataKey(dataSetMetadataPo.getMetadataKey());
            /* 126 */ dataSetMetadataApplyPo.setMetadataValue(dataSetMetadataPo.getMetadataValue());
            /* 127 */ dataSetMetadataApplyPoList.add(dataSetMetadataApplyPo);
            /*     */ }
        /* 129 */ dataSetApplyPo.setDataSetMetadataPoList(dataSetMetadataApplyPoList);
        /* 130 */ dataSetApplyPo.setDataCfgApplyPoList(new ArrayList());
        /*     */
        /* 132 */ return dataSetApplyPo;
        /*     */ }
    /*     */ }
