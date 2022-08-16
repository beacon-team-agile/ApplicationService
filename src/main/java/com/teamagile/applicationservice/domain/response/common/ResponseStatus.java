package com.teamagile.applicationservice.domain.response.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatus {
    private boolean is_success;
    private String message;
}
