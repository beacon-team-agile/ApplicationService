package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.dao.AbstractHibernateDAO;
import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationWorkFlowDaoImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDao {

    public ApplicationWorkFlowDaoImpl() {
        setClazz(ApplicationWorkFlow.class);
    }

    @Override
    public Integer addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow) {
        return add(applicationWorkFlow);
    }

    @Override
    public List<ApplicationWorkFlow> getAllApplicationWorkFlow() {
        return getAll();
    }

    @Override
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        return findById(id);
    }


    @Override
    public ApplicationWorkFlow updateApplicationWorkFlowById(Integer id, ApplicationWorkFlow applicationWorkFlow) {
        Session session = getCurrentSession();
        ApplicationWorkFlow old_applicationWorkFlow = findById(id);

        if (old_applicationWorkFlow==null) {
            return null;
        }

        old_applicationWorkFlow.setEmployee_id(applicationWorkFlow.getEmployee_id());
        old_applicationWorkFlow.setCreate_date(applicationWorkFlow.getCreate_date());
        old_applicationWorkFlow.setLast_modification_date(applicationWorkFlow.getLast_modification_date());
        old_applicationWorkFlow.setStatus(applicationWorkFlow.isStatus());
        old_applicationWorkFlow.setComment(applicationWorkFlow.getComment());

        session.update(old_applicationWorkFlow);

        return old_applicationWorkFlow;
    }

    @Override
    public ApplicationWorkFlow update_ApplicationWorkFlow_lastModificationTime_ById(Integer id) {
        Session session = getCurrentSession();
        ApplicationWorkFlow old_applicationWorkFlow = findById(id);

        if (old_applicationWorkFlow==null) {
            return null;
        }

        Date date = new java.util.Date();

        old_applicationWorkFlow.setLast_modification_date(String.valueOf(date));

        session.update(old_applicationWorkFlow);

        return old_applicationWorkFlow;
    }

    @Override
    public ApplicationWorkFlow update_ApplicationWorkFlow_Status_ById(Integer id, boolean status) {
        Session session = getCurrentSession();
        ApplicationWorkFlow old_applicationWorkFlow = findById(id);

        if (old_applicationWorkFlow==null) {
            return null;
        }

        old_applicationWorkFlow.setStatus(status);

        session.update(old_applicationWorkFlow);

        return old_applicationWorkFlow;
    }

    @Override
    public ApplicationWorkFlow update_ApplicationWorkFlow_comment_ById(Integer id, String comment) {
        Session session = getCurrentSession();
        ApplicationWorkFlow old_applicationWorkFlow = findById(id);

        if (old_applicationWorkFlow==null) {
            return null;
        }

        old_applicationWorkFlow.setComment(comment);

        session.update(old_applicationWorkFlow);

        return old_applicationWorkFlow;
    }

    @Override
    public List<ApplicationWorkFlow> getAllInactiveApplicationWorkFlow() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from ApplicationWorkFlow where status = false");
        List<ApplicationWorkFlow> applicationWorkFlowList = query.getResultList();

        return applicationWorkFlowList;
    }


    @Override
    public void deleteApplicationWorkFlowById(Integer id) {
        delete(id);
    }
}
