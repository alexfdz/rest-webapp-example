package com.example.rest.service;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.rest.model.Person;
import com.example.rest.service.exception.NotFoundException;

@ContextConfiguration(locations = { "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileStoreLodaderTest {

	@Autowired
	FileStoreLodader loader;

	@Test(expected = IllegalArgumentException.class)
	public void null_id_should_throw_an_exception()
			throws IllegalArgumentException, NotFoundException {
		loader.getPerson(null);
	}

	@Test(expected = NotFoundException.class)
	public void invalid_id_should_throw_an_exception()
			throws IllegalArgumentException, NotFoundException {
		loader.getPerson("999");
	}

	@Test
	public void valid_id_should_return_the_valid_entity()
			throws IllegalArgumentException, NotFoundException {
		Person person = loader.getPerson("1");

		assertThat(person, is(notNullValue()));
		assertThat(person.getId(), is("1"));
		assertThat(person.getGivenName(), is("Wayne"));
		assertThat(person.getFamilyName(), is("Rooney"));
		assertThat(person.getMiddleNames(), is("Mark"));
		assertThat(person.getDateOfDeath(), is(""));
		assertThat(person.getDateOfBirth(), is("1985-10-24"));
		assertThat(person.getPlaceOfBirth(), is("Liverpool"));
		assertThat(person.getHeight(), is(Float.valueOf(1.76f)));
		assertThat(person.getTwitterId(), is("@WayneRooney"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void null_id_should_should_throw_an_exception() {
		loader.resolvePersonFileNameById(null);
	}

	@Test
	public void valid_id_should_return_null_file_name() {
		assertThat(loader.resolvePersonFileNameById("2"), is("person-2.txt"));
		assertThat(loader.resolvePersonFileNameById("4"), is("person-4.txt"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void null_file_name_should_return_null_file()
			throws NotFoundException {
		loader.resolvePersonFile(null);
	}

	@Test(expected = NotFoundException.class)
	public void invalid_file_name_should_return_null_file()
			throws NotFoundException {
		loader.resolvePersonFile("person-1234.txt");
	}

	@Test
	public void valid_file_name_should_return_a_valid_file()
			throws IOException, NotFoundException {
		File personFile = loader.resolvePersonFile("person-1.txt");

		assertThat(personFile, is(notNullValue()));
		assertThat(personFile.exists(), is(true));
	}
}
