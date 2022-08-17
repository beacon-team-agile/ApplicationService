package com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MultipleApplicationWorkFlowResponse {
    private ResponseStatus responseStatus;
    private List<ApplicationWorkFlow> applicationWorkFlowList;
}
