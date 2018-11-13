/*    */ package com.iisi.opd.data.in.service;
/*    */ 
/*    */ import com.iisi.opd.cfg.dto.DataFieldCfgDto;
/*    */ import com.iisi.opd.cfg.po.DataFieldCfgApplyPo;
/*    */ import com.iisi.opd.cfg.po.DataFieldCfgPo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ @Transactional
/*    */ @Component
/*    */ public class DataFieldCfgDtoPoTransformer
/*    */ {
/* 18 */   public static Logger logger = LoggerFactory.getLogger(DataFieldCfgDtoPoTransformer.class);
/*    */   
/*    */   public DataFieldCfgDto convertPoToDto(DataFieldCfgPo po) {
/* 21 */     DataFieldCfgDto dto = new DataFieldCfgDto();
/* 22 */     dto.setCheckMethod(po.getCheckMethod());
/* 23 */     dto.setCheckRule(po.getCheckRule());
/* 24 */     dto.setFieldName(po.getFieldName());
/* 25 */     dto.setFieldType(po.getFieldType());
/* 26 */     dto.setPublic(po.isPublic());
/* 27 */     return dto;
/*    */   }
/*    */   
/*    */   public DataFieldCfgDto convertPoToDto(DataFieldCfgApplyPo po) {
/* 31 */     DataFieldCfgDto dto = new DataFieldCfgDto();
/* 32 */     dto.setCheckMethod(po.getCheckMethod());
/* 33 */     dto.setCheckRule(po.getCheckRule());
/* 34 */     dto.setFieldName(po.getFieldName());
/* 35 */     dto.setFieldType(po.getFieldType());
/* 36 */     dto.setPublic(po.isPublic());
/* 37 */     return dto;
/*    */   }
/*    */   
/*    */   public List<DataFieldCfgDto> convertListToDto(List<?> poList) {
/* 41 */     List<DataFieldCfgDto> dtoList = new ArrayList();
/* 42 */     if ((poList == null) || (poList.size() == 0)) {
/* 43 */       return dtoList;
/*    */     }
/* 45 */     for (Object obj : poList) {
/* 46 */       if ((obj instanceof DataFieldCfgPo))
/* 47 */         dtoList.add(convertPoToDto((DataFieldCfgPo)obj));
/* 48 */       if ((obj instanceof DataFieldCfgApplyPo))
/* 49 */         dtoList.add(convertPoToDto((DataFieldCfgApplyPo)obj));
/*    */     }
/* 51 */     return dtoList;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\service\DataFieldCfgDtoPoTransformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */