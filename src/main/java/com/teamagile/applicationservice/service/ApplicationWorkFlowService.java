package com.teamagile.applicationservice.service;

import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        return Optional.ofNullable(applicationWorkFlowDao.getApplicationWorkFlowById(id))
                .orElseThrow(() -> new DataNotFoundException("Application Work Flow not found"));
    }

    public void deleteApplicationWorkFlowById(int id) {
        this.getApplicationWorkFlowById(id);
        applicationWorkFlowDao.deleteApplicationWorkFlowById(id);
    }

    public ApplicationWorkFlow updateApplicationWorkFlowById(int id, ApplicationWorkFlow applicationWorkFlow) {
        this.getApplicationWorkFlowById(id);
        return applicationWorkFlowDao.updateApplicationWorkFlowById(id,applicationWorkFlow);
    }
}
