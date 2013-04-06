package com.example.rest.service;

import com.example.rest.model.Person;
import com.example.rest.service.exception.NotFoundException;

public interface StoreLoader {

	public Person getPerson(String id) throws IllegalArgumentException,
			NotFoundException;
}
