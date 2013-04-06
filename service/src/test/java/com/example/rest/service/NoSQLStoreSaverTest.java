package com.example.rest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.rest.model.Person;
import com.example.rest.service.exception.NotFoundException;

@ContextConfiguration(locations = { "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class NoSQLStoreSaverTest {

	@Autowired
	NoSQLStoreSaver saver;

	@Test(expected = IllegalArgumentException.class)
	public void null_entity_should_throw_an_exception()
			throws IllegalArgumentException, NotFoundException {
		saver.savePerson(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void null_id_should_throw_an_exception()
			throws IllegalArgumentException, NotFoundException {
		saver.savePerson(new Person());
	}

	@Test
	public void valid_entity_should_return_void()
			throws IllegalArgumentException, NotFoundException {
		Person person = new Person();
		person.setId("1");
		saver.savePerson(person);
	}
}
