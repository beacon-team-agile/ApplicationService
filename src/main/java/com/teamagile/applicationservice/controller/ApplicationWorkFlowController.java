package com.teamagile.applicationservice.controller;


import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.response.AddApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.SingleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import com.teamagile.applicationservice.service.ApplicationWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/applicationWorkFlow")
public class ApplicationWorkFlowController {
    private ApplicationWorkFlowService applicationWorkFlowService;

    @Autowired
    public void setApplicationWorkFlowService(ApplicationWorkFlowService applicationWorkFlowService) {
        this.applicationWorkFlowService = applicationWorkFlowService;
    }

    @PostMapping("add")
    public AddApplicationWorkFlowResponse addApplicationWorkFlow(@Valid @RequestBody ApplicationWorkFlow applicationWorkFlow) {
        ApplicationWorkFlow newAppWF = ApplicationWorkFlow.builder()
                .employee_id(applicationWorkFlow.getEmployee_id())
                .create_date(applicationWorkFlow.getCreate_date())
                .last_modification_date(applicationWorkFlow.getLast_modification_date())
                .status(applicationWorkFlow.isStatus())
                .comment(applicationWorkFlow.getComment())
                .build();
        Integer id = applicationWorkFlowService.addApplicationWorkFlow(newAppWF);

        return AddApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully added new Application Work Flow!")
                                .build()
                )
                .id(id)
                .applicationWorkFlow(newAppWF)
                .build();
    }

    @GetMapping("{id}")
    public SingleApplicationWorkFlowResponse getApplicationWorkFlowById(@PathVariable Integer id) {
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowService.getApplicationWorkFlowById(id);

        if (applicationWorkFlow==null) {
            return SingleApplicationWorkFlowResponse.builder()
                    .responseStatus(
                            ResponseStatus.builder()
                                    .is_success(false)
                                    .message("Didn't find ApplicationWorkFlow")
                                    .build()
                    )
                    .applicationWorkFlow(null)
                    .build();
        }

        return SingleApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Found ApplicationWorkFlow")
                                .build()
                )
                .applicationWorkFlow(applicationWorkFlow)
                .build();
    }
}