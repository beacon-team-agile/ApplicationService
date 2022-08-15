package com.teamagile.applicationservice.service;

import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationWorkFlowService {
    public ApplicationWorkFlowDao applicationWorkFlowDao;

    @Autowired
    public void setApplicationWorkFlowDao(ApplicationWorkFlowDao applicationWorkFlowDao) {
        this.applicationWorkFlowDao = applicationWorkFlowDao;
    }

    public Integer addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow) {
        return applicationWorkFlowDao.addApplicationWorkFlow(applicationWorkFlow);
    }

    public ApplicationWorkFlow getApplicationWorkFlowById(int id) {
        return applicationWorkFlowDao.getApplicationWorkFlowById(id);
    }

}
