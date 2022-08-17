package com.teamagile.applicationservice.domain.response.DigitalDocumentResponse;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddDigitalDocumentResponse {
    private ResponseStatus responseStatus;
    private Integer id;
    private DigitalDocument digitalDocument;


}
