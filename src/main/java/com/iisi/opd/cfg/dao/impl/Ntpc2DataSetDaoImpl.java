/*     */ package com.iisi.opd.cfg.dao.impl;
/*     */ 
/*     */ import com.iisi.common.bean.IntervalBean;
/*     */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*     */ import com.iisi.common.util.NativeSqlOrderUtils;
/*     */ import com.iisi.common.util.Pager;
/*     */ import com.iisi.opd.cfg.dao.Ntpc2DataSetDao;
/*     */ import com.iisi.opd.cfg.po.DataSetPo;
/*     */ import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.LogicalExpression;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.criterion.SimpleExpression;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ public class Ntpc2DataSetDaoImpl
/*     */   extends GenericDaoImpl<DataSetPo>
/*     */   implements Ntpc2DataSetDao
/*     */ {
/*     */   public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList, IntervalBean interval, DataSetPo.OrderByOption orderByOption)
/*     */   {
/*  31 */     DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, null, null);
/*  32 */     criteria.addOrder(generateOrder(orderByOption));
/*  33 */     return getHibernateTemplate().findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList, IntervalBean interval, String mataDataKey, List<String> mataDataValueList, DataSetPo.OrderByOption orderByOption)
/*     */   {
/*  38 */     DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, mataDataKey, mataDataValueList);
/*  39 */     return getComplexStructure(criteria, generateOrder(orderByOption));
/*     */   }
/*     */   
/*     */   public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList, IntervalBean interval, DataSetPo.OrderByOption orderByOption)
/*     */   {
/*  44 */     DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, null, null);
/*  45 */     return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
/*     */   }
/*     */   
/*     */   public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList, IntervalBean interval, String mataDataKey, List<String> mataDataValueList, DataSetPo.OrderByOption orderByOption)
/*     */   {
/*  50 */     DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, mataDataKey, mataDataValueList);
/*  51 */     return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
/*     */   }
/*     */   
/*     */   private DetachedCriteria createDetachedCriteriaForMultiCollectionSearch(List<String> unitList, List<String> categoryList, IntervalBean interval, String mataDataKey, List<String> mataDataValueList) {
/*  55 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
/*  56 */     DetachedCriteria dataCfgPoListCriteria = null;
/*  57 */     DetachedCriteria cfgMataDataPoListCriteria = null;
/*  58 */     criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
/*  59 */     criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
/*  60 */     criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
/*     */     
/*  62 */     if ((unitList != null) && (unitList.size() != 0))
/*     */     {
/*  64 */       criteria.createAlias("unitPo", "unitPo", 1);
/*  65 */       SimpleExpression likeUnitParentOidExpression = null;
/*  66 */       LogicalExpression orExpression = null;
/*  67 */       for (String str : unitList) {
/*  68 */         likeUnitParentOidExpression = Restrictions.like("unitPo.unitPathName", str, MatchMode.END);
/*  69 */         if (orExpression == null) {
/*  70 */           orExpression = Restrictions.or(Restrictions.sqlRestriction(" 1!=1 "), likeUnitParentOidExpression);
/*     */         } else {
/*  72 */           orExpression = Restrictions.or(likeUnitParentOidExpression, orExpression);
/*     */         }
/*     */       }
/*  75 */       criteria.add(orExpression);
/*     */     }
/*  77 */     if ((categoryList != null) && (categoryList.size() != 0))
/*     */     {
/*  79 */       criteria.createAlias("dataCategoryPo", "dataCategoryPo", 1);
/*  80 */       criteria.add(Restrictions.in("dataCategoryPo.oid", categoryList));
/*     */     }
/*  82 */     if ((mataDataKey != null) && (mataDataValueList != null) && (mataDataValueList.size() != 0)) {
/*  83 */       dataCfgPoListCriteria = criteria.createAlias("dataCfgPoList", "dataCfgPoList", 1);
/*  84 */       cfgMataDataPoListCriteria = dataCfgPoListCriteria.createCriteria("dataCfgPoList.dataCfgMetadataPoList", 1);
/*     */       
/*     */ 
/*     */ 
/*  88 */       cfgMataDataPoListCriteria.add(Restrictions.eq("metadataKey", mataDataKey));
/*  89 */       cfgMataDataPoListCriteria.add(Restrictions.in("metadataValue", mataDataValueList));
/*     */     }
/*  91 */     if ((interval.getName().contains("dataCfgPoList")) && (dataCfgPoListCriteria == null))
/*  92 */       dataCfgPoListCriteria = criteria.createAlias("dataCfgPoList", "dataCfgPoList", 1);
/*  93 */     criteria.add(interval.getRestriction());
/*  94 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/*     */     
/*  96 */     return criteria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Order generateOrder(DataSetPo.OrderByOption orderByOption)
/*     */   {
/* 107 */     if (orderByOption == null)
/* 108 */       return Order.desc("publicTime");
/* 109 */     if (orderByOption == DataSetPo.OrderByOption.NEWEST_DESC)
/* 110 */       return Order.desc("createTime");
/* 111 */     if (orderByOption == DataSetPo.OrderByOption.OLDEST_ASC)
/* 112 */       return Order.asc("createTime");
/* 113 */     if (orderByOption == DataSetPo.OrderByOption.HOT_DESC) {
/* 114 */       return NativeSqlOrderUtils.desc("(data_out_count + download_count + view_count + data_srv_count)");
/*     */     }
/* 116 */     if (orderByOption == DataSetPo.OrderByOption.DATA_OUT_DESC)
/* 117 */       return Order.desc("dataOutCount");
/* 118 */     if (orderByOption == DataSetPo.OrderByOption.VIEW_DESC) {
/* 119 */       return Order.desc("viewCount");
/*     */     }
/* 121 */     return Order.desc("publicTime");
/*     */   }
/*     */   
/*     */   public Pager getAllDataSetApplyStatus(Pager pager)
/*     */   {
/* 126 */     StringBuffer sqlStatment = new StringBuffer();
/* 127 */     sqlStatment.append("/****** SSMS 中 SelectTopNRows 命令的指令碼  ******/ \n");
/* 128 */     sqlStatment.append(";WITH CTE (\"oid\") AS ( \n");
/* 129 */     sqlStatment.append("\tSELECT ODS.oid \n");
/* 130 */     sqlStatment.append("\t  FROM [dbo].[od_data_set] ODS \n");
/* 131 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_apply] ODSA ON ODS.oid = ODSA.od_data_set_oid \n");
/* 132 */     sqlStatment.append("\tWHERE ODSA.oid IS NULL \n");
/* 133 */     sqlStatment.append("\tUNION ALL \n");
/* 134 */     sqlStatment.append("\tSELECT ODSA.oid \n");
/* 135 */     sqlStatment.append("\t  FROM [dbo].[od_data_set_apply] ODSA \n");
/* 136 */     sqlStatment.append(") \n");
/* 137 */     sqlStatment.append("SELECT COUNT(*) \n");
/* 138 */     sqlStatment.append("FROM CTE \n");
/*     */     
/* 140 */     int totalSize = ((Integer)getNativeSqlQueryList(sqlStatment.toString()).get(0)).intValue();
/* 141 */     pager.setTotalRows(totalSize);
/* 142 */     Pager newPager = pager.clone();
/*     */     
/* 144 */     sqlStatment = new StringBuffer();
/* 145 */     sqlStatment.append(";WITH CTE ( \n");
/* 146 */     sqlStatment.append("\t\"oid\", \"categoryName\", \"dataName\", \"CONTACT_NAME\", \"applyUserName\", \n");
/* 147 */     sqlStatment.append("\t\"applyTime\", \"dataStatus\", \"actionType\", \"REVIEW_LEVEL\", \"comment\") AS ( \n");
/* 148 */     sqlStatment.append("\tSELECT ODS.oid \n");
/* 149 */     sqlStatment.append("\t\t  ,ODC.name \n");
/* 150 */     sqlStatment.append("\t\t  ,ODS.name \n");
/* 151 */     sqlStatment.append("\t\t  ,ODSM1.metadata_value \n");
/* 152 */     sqlStatment.append("\t\t  ,ODS.last_edit_user_name \n");
/* 153 */     sqlStatment.append("\t\t  ,ODS.last_edit_time \n");
/* 154 */     sqlStatment.append("\t\t  ,'2' \n");
/* 155 */     sqlStatment.append("\t\t  ,'' \n");
/* 156 */     sqlStatment.append("\t\t  ,ODSM2.metadata_value \n");
/* 157 */     sqlStatment.append("\t\t  ,'' \n");
/* 158 */     sqlStatment.append("\t  FROM [dbo].[od_data_set] ODS \n");
/* 159 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_category] ODC ON ODS.od_data_category_oid = ODC.oid \n");
/* 160 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_metadata] ODSM1 ON  ODS.oid = ODSM1.od_data_set_oid \n");
/* 161 */     sqlStatment.append("\t\t\tAND ODSM1.metadata_key = 'contactName' \n");
/* 162 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_metadata] ODSM2 ON  ODS.oid = ODSM2.od_data_set_oid \n");
/* 163 */     sqlStatment.append("\t\t\tAND ODSM2.metadata_key = 'reviewLevel' \n");
/* 164 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_apply] ODSA ON ODS.oid = ODSA.od_data_set_oid \n");
/* 165 */     sqlStatment.append("\tWHERE ODSA.oid IS NULL \n");
/* 166 */     sqlStatment.append("\tUNION ALL \n");
/* 167 */     sqlStatment.append("\tSELECT ODSA.oid \n");
/* 168 */     sqlStatment.append("\t\t  ,ODC.name \n");
/* 169 */     sqlStatment.append("\t\t  ,ODSA.name \n");
/* 170 */     sqlStatment.append("\t\t  ,ODSMA1.metadata_value \n");
/* 171 */     sqlStatment.append("\t\t  ,ODSA.apply_user_name \n");
/* 172 */     sqlStatment.append("\t\t  ,ODSA.apply_time \n");
/* 173 */     sqlStatment.append("\t\t  ,ODSA.data_status \n");
/* 174 */     sqlStatment.append("\t\t  ,ODSA.action_type \n");
/* 175 */     sqlStatment.append("\t\t  ,ODSMA2.metadata_value \n");
/* 176 */     sqlStatment.append("\t\t  ,ODSA.comment \n");
/* 177 */     sqlStatment.append("\t  FROM [dbo].[od_data_set_apply] ODSA \n");
/* 178 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_category] ODC ON ODSA.od_data_category_oid = ODC.oid \n");
/* 179 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_metadata_apply] ODSMA1 ON ODSA.oid = ODSMA1.od_data_set_apply_oid \n");
/* 180 */     sqlStatment.append("\t\t\tAND ODSMA1.metadata_key = 'contactName' \n");
/* 181 */     sqlStatment.append("\tLEFT JOIN [dbo].[od_data_set_metadata_apply] ODSMA2 ON ODSA.oid = ODSMA2.od_data_set_apply_oid \n");
/* 182 */     sqlStatment.append("\t\t\tAND ODSMA2.metadata_key = 'reviewLevel' \n");
/* 183 */     sqlStatment.append("), CTE_ORDER AS ( \n");
/* 184 */     sqlStatment.append("\tSELECT ROW_NUMBER() OVER (ORDER BY applyTime DESC) AS rid \n");
/* 185 */     sqlStatment.append("\t\t  ,* \n");
/* 186 */     sqlStatment.append("\t  FROM CTE \n");
/* 187 */     sqlStatment.append(")  \n");
/* 188 */     sqlStatment.append("SELECT * \n");
/* 189 */     sqlStatment.append("FROM CTE_ORDER \n");
/* 190 */     sqlStatment.append("WHERE rid BETWEEN " + newPager.getCurrentStartRowIndex() + " AND " + newPager.getCurrentEndRowIndex() + " \n");
/* 191 */     newPager.setDataObj(getNativeSqlQueryList(sqlStatment.toString()));
/* 192 */     return newPager;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\Ntpc2DataSetDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */