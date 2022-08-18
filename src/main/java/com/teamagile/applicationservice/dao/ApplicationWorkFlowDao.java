package com.teamagile.applicationservice.dao;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;

import java.util.List;

public interface ApplicationWorkFlowDao {
    Integer addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow);

    List<ApplicationWorkFlow> getAllApplicationWorkFlow();
    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);

    ApplicationWorkFlow updateApplicationWorkFlowById(Integer id,ApplicationWorkFlow applicationWorkFlow);

    ApplicationWorkFlow update_ApplicationWorkFlow_lastModificationTime_ById(Integer id);

    ApplicationWorkFlow update_ApplicationWorkFlow_Status_ById(Integer id, boolean status);

    ApplicationWorkFlow update_ApplicationWorkFlow_comment_ById(Integer id, String comment);
    void deleteApplicationWorkFlowById(Integer id);
}
