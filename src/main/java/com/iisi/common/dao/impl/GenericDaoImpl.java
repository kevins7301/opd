package com.iisi.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.GenericsUtils;
import com.iisi.common.util.Pager;

@Repository
public class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T> {
    private static final int batchSize = 32;
    private Class<T> entityClass;

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    public GenericDaoImpl() {
        this.entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
    }

    protected Class<T> getEntityClass() {
        return this.entityClass;
    }

    protected void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> findAll(Class<T> entityClass, String orderBy, boolean isAsc) {
        Assert.hasText(orderBy);
        if (isAsc) {
            return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        }

        return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    @Override
    public T findById(Serializable id) {
        return (T) getHibernateTemplate().get(this.entityClass, id);
    }

    @Override
    public void deleteById(Serializable id) {
        delete(findById(id));
    }

    @Override
    public void delete(Object o) {
        getHibernateTemplate().delete(o);
    }

    protected Serializable create(Object o) {
        return getHibernateTemplate().save(o);
    }

    @Override
    public T save(Object o) {
        return (T) getHibernateTemplate().merge(o);
    }

    @Override
    public void saveOrUpdate(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    @Override
    public void update(Object o) {
        getHibernateTemplate().update(o, LockMode.NONE);
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    @Override
    public void saveAll(final List<T> entities) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                for (int i = 0; i < entities.size(); i++) {
                    GenericDaoImpl.this.save(entities.get(i));
                    if ((i & 0x20) == 32) {
                        GenericDaoImpl.this.flush();
                    }
                }
                return entities;
            }
        });
    }

    @Override
    public void updateAll(final List<T> entities) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                for (int i = 0; i < entities.size(); i++) {
                    GenericDaoImpl.this.update(entities.get(i));
                    if ((i & 0x20) == 32) {
                        GenericDaoImpl.this.flush();
                    }
                }
                return entities;
            }
        });
    }

    @Override
    public void saveOrUpdateAll(final List<T> entities) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                for (int i = 0; i < entities.size(); i++) {
                    GenericDaoImpl.this.saveOrUpdate(entities.get(i));
                    if ((i & 0x20) == 32) {
                        GenericDaoImpl.this.flush();
                    }
                }
                return entities;
            }
        });
    }

    @Override
    public void deleteAll(final List<T> entities) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                for (int i = 0; i < entities.size(); i++) {
                    GenericDaoImpl.this.delete(entities.get(i));
                    if ((i & 0x20) == 32) {
                        GenericDaoImpl.this.flush();
                    }
                }
                return entities;
            }
        });
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<T> findByPage(int firstResult, int maxResults) {
        DetachedCriteria criteria = DetachedCriteria.forClass(this.entityClass);
        return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

    @Override
    public Pager getPager(Pager pager) {
        DetachedCriteria criteria = DetachedCriteria.forClass(this.entityClass);
        return getPager(pager, criteria, "");
    }

    @Override
    public Pager getPager(Pager pager, String orderBy) {
        DetachedCriteria criteria = DetachedCriteria.forClass(this.entityClass);
        return getPager(pager, criteria, orderBy);
    }

    @Override
    public Pager getPager(Pager pager, DetachedCriteria criteria) {
        return getPager(pager, criteria, "");
    }

    @Override
    public Pager getPager(Pager pager, DetachedCriteria criteria, String orderBy) {
        pager.setTotalRows(getRowCount(criteria));

        if ((orderBy != null) && (orderBy.trim().length() != 0)) {
            criteria.addOrder(Order.desc(orderBy));
        }
        List<?> listT = getHibernateTemplate().findByCriteria(criteria, pager.getCurrentStartRowIndex() - 1, pager.getPageSize());
        Pager newPager = pager.clone();
        newPager.setDataObj(listT);
        return newPager;
    }

    protected Pager getPager(Pager pager, DetachedCriteria criteria, Order orderBy) {
        pager.setTotalRows(getRowCount(criteria));

        if (orderBy != null) {
            criteria.addOrder(orderBy);
        }
        List<?> listT = getHibernateTemplate().findByCriteria(criteria, pager.getCurrentStartRowIndex() - 1, pager.getPageSize());
        Pager newPager = pager.clone();
        newPager.setDataObj(listT);
        return newPager;
    }

    protected Pager getPagerWithComplexStructure(Pager pager, DetachedCriteria detachedCriteria) {
        return getPagerWithComplexStructure(pager, detachedCriteria, null);
    }

    protected Pager getPagerWithComplexStructure(Pager pager, DetachedCriteria detachedCriteria, Order order) {
        List<?> listT = new ArrayList();

        detachedCriteria.setProjection(Projections.countDistinct("oid"));

        int totalSize = ((Long) getProjectionsQueryList(detachedCriteria).get(0)).intValue();
        pager.setTotalRows(totalSize);

        detachedCriteria.setProjection(Projections.property("oid"));

        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());

        criteria.add(Subqueries.propertyIn("oid", detachedCriteria));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (order != null) {
            criteria.addOrder(order);
        }
        listT = getHibernateTemplate().findByCriteria(criteria, pager.getCurrentStartRowIndex() - 1, pager.getPageSize());
        Pager newPager = pager.clone();
        newPager.setDataObj(listT);
        return newPager;
    }

    protected List<T> getComplexStructure(DetachedCriteria detachedCriteria, Order order) {
        List<T> listT = new ArrayList();

        detachedCriteria.setProjection(Projections.groupProperty("oid"));

        List<Object> oidList = (List<Object>) getProjectionsQueryList(detachedCriteria);
        detachedCriteria.setProjection(null);

        if (oidList.size() != 0) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);

            detachedCriteria.add(Restrictions.in("oid", oidList));
            if (order != null)
                detachedCriteria.addOrder(order);
            detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            listT = getHibernateTemplate().findByCriteria(detachedCriteria);
        }
        return listT;
    }

    protected List<T> getComplexStructure(DetachedCriteria detachedCriteria, String createAlies, Order order) {
        List<T> listT = new ArrayList();

        detachedCriteria.setProjection(Projections.groupProperty("oid"));

        List<Object> oidList = (List<Object>) getProjectionsQueryList(detachedCriteria);
        detachedCriteria.setProjection(null);

        if (oidList.size() != 0) {
            detachedCriteria = DetachedCriteria.forClass(this.entityClass);

            detachedCriteria.add(Restrictions.in("oid", oidList));
            if ((createAlies != null) && (createAlies.length() != 0)) {
                detachedCriteria.createAlias(createAlies, createAlies, 1);
                detachedCriteria.setFetchMode(createAlies, FetchMode.JOIN);
            }
            if (order != null)
                detachedCriteria.addOrder(order);
            detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            listT = getHibernateTemplate().findByCriteria(detachedCriteria);
        }
        return listT;
    }

    @Override
    public int getRowCount(DetachedCriteria detachedCriteria) {
        int cnt = 0;
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        CriteriaImpl impl = (CriteriaImpl) criteria;

        Projection projection = impl.getProjection();

        List result = getHibernateTemplate().findByCriteria(detachedCriteria.setProjection(Projections.countDistinct("oid")));

        cnt = result.isEmpty() ? 0 : ((Long) result.get(0)).intValue();

        detachedCriteria.setProjection(projection);
        if (projection == null) {
            detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        }
        return cnt;
    }

    @Override
    public List<?> getProjectionsQueryList(DetachedCriteria detachedCriteria) {
        return getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public List<?> getNativeSqlQueryList(final String sqlQuery) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                return session.createSQLQuery(sqlQuery).list();
            }
        });
    }

    @Override
    public List<?> getNativeSqlQueryList(final String sqlQuery, final Object[] params) {
        return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                SQLQuery query = session.createSQLQuery(sqlQuery);
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
                return query.list();
            }
        });
    }

    protected List<?> getListByHql(String queryString, String[] paramNames, Object[] values) {
        if ((paramNames != null) && (values != null) && (paramNames.length != 0) && (values.length != 0)) {
            return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
        }
        return getHibernateTemplate().find(queryString);
    }

    protected List<?> getListByHql(String queryString, Map<String, Object> conditions) {
        String[] paramNames = conditions.keySet().toArray(new String[0]);
        Object[] values = new Object[paramNames.length];
        for (int i = 0; i < paramNames.length; i++) {
            values[i] = conditions.get(paramNames[i]);
        }
        return getListByHql(queryString, paramNames, values);
    }

    protected List<?> getListBySql(String queryString, String[] paramNames, Object[] values) {
        Session session = getOpenSession();
        SQLQuery q = session.createSQLQuery(queryString);
        if ((paramNames != null) && (values != null)) {
            int length = paramNames.length;
            for (int i = 0; i < length; i++) {
                q.setParameter(paramNames[i], values[i]);
            }
        }
        List<?> list = q.list();
        closeSession(session);
        return list;
    }

    protected List<?> getListBySql(String queryString, Map<String, Object> conditions) {
        Session session = getOpenSession();
        SQLQuery q = session.createSQLQuery(queryString);
        if (conditions != null) {
            for (Map.Entry<String, Object> condition : conditions.entrySet()) {
                q.setParameter(condition.getKey(), condition.getValue());
            }
        }
        List<?> list = q.list();
        closeSession(session);
        return list;
    }

    protected static void closeSession(Session session) {
        session.flush();
        session.close();
    }

    protected Session getOpenSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected Object getSigleObject(List<?> list) {
        if ((list == null) || (list.isEmpty())) {
            return null;
        }
        return list.get(0);
    }
}