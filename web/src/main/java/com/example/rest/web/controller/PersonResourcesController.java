package com.example.rest.web.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rest.model.Person;
import com.example.rest.service.StoreLoader;
import com.example.rest.service.StoreSaver;

@Component
@Path("/person")
public class PersonResourcesController {

	@Autowired
	StoreLoader storeLoader;

	@Autowired
	StoreSaver storeSaver;

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam(value = "id") final String id)
			throws Throwable {
		Person person = storeLoader.getPerson(id);
		return Response.ok(person).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	public Response save(final Person person) throws Throwable {
		storeSaver.savePerson(person);
		return Response.ok().build();
	}
}
