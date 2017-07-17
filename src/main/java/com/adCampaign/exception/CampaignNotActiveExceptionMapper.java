package com.adCampaign.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.adCampaign.model.ErrorMessage;

public class CampaignNotActiveExceptionMapper implements ExceptionMapper<CampaignNotActiveException> {

	@Override
	public Response toResponse(CampaignNotActiveException ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 401, "No active ad campaigns exist for the specified partner");
		return Response.status(Status.NOT_FOUND)
				.entity(error)
				.build();
	}

}
