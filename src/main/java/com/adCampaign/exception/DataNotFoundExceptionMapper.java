package com.adCampaign.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.adCampaign.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {

		ErrorMessage error = new ErrorMessage(ex.getMessage(), 405, "Data not found");
		return Response.status(Status.BAD_REQUEST)
				.entity("Jai kiran")
				.build();
	}

}
