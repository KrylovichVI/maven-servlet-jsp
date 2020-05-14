package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.Page;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class DefaultPageDao<T> {
    public List<T> listOfPage(Class<T> resultClass, Page page) {
        try(Session session = SessionUtil.openSession()){
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(resultClass);
            Root<T> root = criteria.from(resultClass);
            criteria.select(root);
            List<T> resultList = session.createQuery(criteria)
                    .setFirstResult(page.getFirstPage())
                    .setMaxResults(page.getMaxPage())
                    .getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public long countOfRow(Class<T> resultClass){
        try(Session session = SessionUtil.openSession()){
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
            Root<T> root = criteria.from(resultClass);
            criteria.select(builder.count(root));
            Long result = session.createQuery(criteria).getSingleResult();
            session.getTransaction().commit();
            return result;
        }
    }
}
