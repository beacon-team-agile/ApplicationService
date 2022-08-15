package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.dao.AbstractHibernateDAO;
import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import org.springframework.stereotype.Repository;

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
}
