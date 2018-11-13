package com.iisi.common.dao;

import com.iisi.common.util.Pager;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public abstract interface GenericDao<T>
{
  public abstract T save(Object paramObject);
  
  public abstract void saveAll(List<T> paramList);
  
  public abstract void update(Object paramObject);
  
  public abstract void updateAll(List<T> paramList);
  
  public abstract void saveOrUpdate(Object paramObject);
  
  public abstract void saveOrUpdateAll(List<T> paramList);
  
  public abstract void delete(Object paramObject);
  
  public abstract void deleteById(Serializable paramSerializable);
  
  public abstract void deleteAll(List<T> paramList);
  
  public abstract T findById(Serializable paramSerializable);
  
  public abstract List<T> findAll(Class<T> paramClass);
  
  public abstract List<T> findAll(Class<T> paramClass, String paramString, boolean paramBoolean);
  
  public abstract void flush();
  
  public abstract List<T> findByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract List<T> findByPage(int paramInt1, int paramInt2);
  
  public abstract Pager getPager(Pager paramPager);
  
  public abstract Pager getPager(Pager paramPager, String paramString);
  
  public abstract Pager getPager(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager getPager(Pager paramPager, DetachedCriteria paramDetachedCriteria, String paramString);
  
  public abstract int getRowCount(DetachedCriteria paramDetachedCriteria);
  
  public abstract List<?> getProjectionsQueryList(DetachedCriteria paramDetachedCriteria);
  
  public abstract List<?> getNativeSqlQueryList(String paramString);
  
  public abstract List<?> getNativeSqlQueryList(String paramString, Object[] paramArrayOfObject);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\common\dao\GenericDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */