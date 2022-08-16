package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.dao.AbstractHibernateDAO;
import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        return findById(id);
    }

    @Override
    public ApplicationWorkFlow updateApplicationWorkFlowById(Integer id, ApplicationWorkFlow applicationWorkFlow) {
        Session session = getCurrentSession();
        ApplicationWorkFlow old_applicationWorkFlow = findById(id);

        old_applicationWorkFlow.setEmployee_id(applicationWorkFlow.getEmployee_id());
        old_applicationWorkFlow.setCreate_date(applicationWorkFlow.getCreate_date());
        old_applicationWorkFlow.setLast_modification_date(applicationWorkFlow.getLast_modification_date());
        old_applicationWorkFlow.setStatus(applicationWorkFlow.isStatus());
        old_applicationWorkFlow.setComment(applicationWorkFlow.getComment());

        session.update(old_applicationWorkFlow);

        return old_applicationWorkFlow;
    }

    @Override
    public void deleteApplicationWorkFlowById(Integer id) {
        delete(id);
    }
}
