package com.teamagile.applicationservice.controller;


import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.AddApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.MultipleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.SingleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import com.teamagile.applicationservice.service.ApplicationWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applicationWorkFlow")
public class ApplicationWorkFlowController {
    private ApplicationWorkFlowService applicationWorkFlowService;

    @Autowired
    public void setApplicationWorkFlowService(ApplicationWorkFlowService applicationWorkFlowService) {
        this.applicationWorkFlowService = applicationWorkFlowService;
    }

    @GetMapping("/all")
    public MultipleApplicationWorkFlowResponse getAllApplicationWorkFlow() {
        List<ApplicationWorkFlow> applicationWorkFlowList = applicationWorkFlowService.getAllApplicationWorkFlow();
        return MultipleApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully got all the Application Work Flows")
                                .build()
                )
                .applicationWorkFlowList(applicationWorkFlowList)
                .build();
    }

    @PostMapping("/add")
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

    @GetMapping("/{id}")
    public SingleApplicationWorkFlowResponse getApplicationWorkFlowById(@PathVariable Integer id) {
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowService.getApplicationWorkFlowById(id);

//        if (applicationWorkFlow==null) {
//            return SingleApplicationWorkFlowResponse.builder()
//                    .responseStatus(
//                            ResponseStatus.builder()
//                                    .is_success(false)
//                                    .message("Didn't find ApplicationWorkFlow")
//                                    .build()
//                    )
//                    .applicationWorkFlow(null)
//                    .build();
//        }

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

    @DeleteMapping("/delete/{id}")
    public SingleApplicationWorkFlowResponse deleteApplicationWorkFlowById(@PathVariable Integer id) {
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowService.getApplicationWorkFlowById(id);

        applicationWorkFlowService.deleteApplicationWorkFlowById(id);

        return SingleApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Deleted ApplicationWorkFlow")
                                .build()
                )
                .applicationWorkFlow(applicationWorkFlow)
                .build();
    }

    @PatchMapping("/update/{id}")
    public SingleApplicationWorkFlowResponse deleteApplicationWorkFlowById(@PathVariable Integer id,
                                                                           @RequestBody ApplicationWorkFlow applicationWorkFlow) {

        ApplicationWorkFlow updated_applicationWorkFlow = applicationWorkFlowService.updateApplicationWorkFlowById(id,applicationWorkFlow);

        return SingleApplicationWorkFlowResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Updated ApplicationWorkFlow")
                                .build()
                )
                .applicationWorkFlow(updated_applicationWorkFlow)
                .build();

    }

}
