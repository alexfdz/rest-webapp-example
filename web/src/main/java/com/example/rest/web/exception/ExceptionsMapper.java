package com.example.rest.web.exception;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.example.rest.service.exception.NotFoundException;

@Component
@Provider
public class ExceptionsMapper implements ExceptionMapper<Throwable> {

	/**
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(final Throwable throwable) {
		int httpStatus = resolveHttpStatus(throwable);
		return Response.status(httpStatus).build();
	}

	protected int resolveHttpStatus(final Throwable throwable) {
		int httpStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

		if (throwable instanceof IllegalArgumentException) {
			httpStatus = HttpServletResponse.SC_BAD_REQUEST;
		} else if (throwable instanceof NotFoundException) {
			httpStatus = HttpServletResponse.SC_NOT_FOUND;
		}

		return httpStatus;
	}
}
