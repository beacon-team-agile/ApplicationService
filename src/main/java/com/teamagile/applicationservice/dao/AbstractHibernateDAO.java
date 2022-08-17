package com.teamagile.applicationservice.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDAO<T extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findById(final Integer id) {
        return getCurrentSession().get(clazz, id);
    }

    public Integer add(T t) {
        return (Integer) getCurrentSession().save(t);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<T> getAll() {
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        criteriaQuery.from(clazz);

        return session.createQuery(criteriaQuery).getResultList();
    }

    public void delete(Integer id) {
        T t = findById(id);
        getCurrentSession().delete(t);
    }
}
