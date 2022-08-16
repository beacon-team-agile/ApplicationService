package com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleApplicationWorkFlowResponse {
    private ResponseStatus responseStatus;

    private ApplicationWorkFlow applicationWorkFlow;
}
