package com.teamagile.applicationservice.dao;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;

import java.util.List;

public interface ApplicationWorkFlowDao {
    Integer addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow);

    List<ApplicationWorkFlow> getAllApplicationWorkFlow();
    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);

    ApplicationWorkFlow updateApplicationWorkFlowById(Integer id,ApplicationWorkFlow applicationWorkFlow);

    void deleteApplicationWorkFlowById(Integer id);
}
