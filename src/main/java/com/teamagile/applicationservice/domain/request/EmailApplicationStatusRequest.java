package com.teamagile.applicationservice.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailApplicationStatusRequest {
	String email;
	Boolean approved;
	String comment;
}
