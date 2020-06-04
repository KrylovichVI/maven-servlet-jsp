package com.krylovichVI.dao.imp;

import com.krylovichVI.pojo.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class DefaultPageDao<T> {

    public List<T> listOfPage(Class<T> resultClass, SessionFactory sessionFactory, Page page) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(resultClass);
        Root<T> root = criteria.from(resultClass);
        criteria.select(root);
        List<T> resultList = session.createQuery(criteria)
                .setFirstResult(page.getFirstPage())
                .setMaxResults(page.getMaxPage())
                .getResultList();
        return resultList;
    }

    public long countOfRow(Class<T> resultClass, SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> root = criteria.from(resultClass);
        criteria.select(builder.count(root));
        Long result = session.createQuery(criteria).getSingleResult();
        return result;
    }
}
