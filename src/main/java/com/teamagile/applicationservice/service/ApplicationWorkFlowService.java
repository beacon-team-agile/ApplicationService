package com.teamagile.applicationservice.service;

import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationWorkFlowService {
    public ApplicationWorkFlowDao applicationWorkFlowDao;

    @Autowired
    public void setApplicationWorkFlowDao(ApplicationWorkFlowDao applicationWorkFlowDao) {
        this.applicationWorkFlowDao = applicationWorkFlowDao;
    }

    public List<ApplicationWorkFlow> getAllApplicationWorkFlow() {
        return applicationWorkFlowDao.getAllApplicationWorkFlow();
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

    public ApplicationWorkFlow update_ApplicationWorkFlow_lastModificationTime_ById(Integer id){
        this.getApplicationWorkFlowById(id);
        return applicationWorkFlowDao.update_ApplicationWorkFlow_lastModificationTime_ById(id);
    }
    public ApplicationWorkFlow update_ApplicationWorkFlow_comment_ById(Integer id, String comment){
        this.getApplicationWorkFlowById(id);
        return applicationWorkFlowDao.update_ApplicationWorkFlow_comment_ById(id, comment);
    }

    public ApplicationWorkFlow update_ApplicationWorkFlow_Status_ById(Integer id, boolean status){
        this.getApplicationWorkFlowById(id);
        return applicationWorkFlowDao.update_ApplicationWorkFlow_Status_ById(id,status);
    }

    public List<ApplicationWorkFlow> getAllInactiveApplicationWorkFlow() {
        return applicationWorkFlowDao.getAllInactiveApplicationWorkFlow();

    }
}
