package com.teamagile.applicationservice.dao;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;

public interface ApplicationWorkFlowDao {
    Integer addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow);

    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);
}