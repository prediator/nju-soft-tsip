package cn.edu.nju.tsip.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tsip.dao.IDao;

@Repository("dao")
public class DaoImpl<T> extends HibernateDaoSupport implements IDao<T> {
	
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	public void create(T baseBean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().persist(baseBean);
	}

	public Query createQuery(String hql) {
		// TODO Auto-generated method stub
		return getSession().createQuery(hql);
	}

	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(baseBean);
	}

	@SuppressWarnings("unchecked")
	public T find(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		return (T)getHibernateTemplate().get(clazz, id);
	}

	public int getTotalCount(String hql, Object... params) {
		// TODO Auto-generated method stub
		Query query = createQuery(hql);
		for(int i = 0; params != null && i < params.length; i++){
			query.setParameter(i+1, params[i]);
		}
		Object obj = createQuery(hql).uniqueResult();
		return ((Long) obj).intValue();
	}

	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params) {
		// TODO Auto-generated method stub
		Query query = createQuery(hql);
		for(int i = 0; params != null && i < params.length; i++){
			query.setParameter(i+1, params[i]);
		}
		List<T> list = createQuery(hql).setFirstResult(firstResult).setMaxResults(maxSize).list();
		return list;
	}

	public void save(T baseBean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(baseBean);
	}
	

}
