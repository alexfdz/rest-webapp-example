package com.example.rest.service;

import com.example.rest.model.Person;

public interface StoreSaver {

	public void savePerson(Person person) throws IllegalArgumentException;
}
