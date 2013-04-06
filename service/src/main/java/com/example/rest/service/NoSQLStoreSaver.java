package com.example.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.example.rest.model.Person;

@Component
public class NoSQLStoreSaver implements StoreSaver {

	@Autowired
	MongoOperations mongoOps;

	@Override
	public void savePerson(Person person) throws IllegalArgumentException {
		if (person != null && person.getId() != null) {
			mongoOps.save(person);
		} else {
			throw new IllegalArgumentException();
		}
	}

}
