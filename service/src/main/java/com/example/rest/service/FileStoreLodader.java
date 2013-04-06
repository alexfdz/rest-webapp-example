package com.example.rest.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.rest.model.Person;
import com.example.rest.service.exception.NotFoundException;

@Component
public class FileStoreLodader implements StoreLoader {

	@Value("${store.path}")
	URL storePath;
	@Value("${file.prefix}")
	String personFilePrefix;
	@Value("${file.extension}")
	String personFileExtension;

	@Override
	public Person getPerson(String id) throws IllegalArgumentException,
			NotFoundException {
		String fileName = resolvePersonFileNameById(id);
		File personFile = resolvePersonFile(fileName);
		return resolvePersonEntity(personFile);
	}

	protected String resolvePersonFileNameById(String id) {
		if (StringUtils.isNotEmpty(id)) {
			return personFilePrefix + id.toString() + "." + personFileExtension;
		} else {
			throw new IllegalArgumentException();
		}
	}

	protected File resolvePersonFile(String fileName) throws NotFoundException {
		if (StringUtils.isNotEmpty(fileName)) {
			File storeFolder;
			try {
				storeFolder = new File(storePath.toURI());
			} catch (URISyntaxException e) {
				throw new NotFoundException();
			}
			String filePath = storeFolder.getAbsolutePath() + File.separator
					+ fileName;
			File personFile = new File(filePath);
			if (personFile != null && personFile.exists()) {
				return personFile;
			} else {
				throw new NotFoundException();
			}
		}
		throw new IllegalArgumentException();
	}

	protected Person resolvePersonEntity(File personFile)
			throws NotFoundException {
		if (personFile != null && personFile.exists()) {
			Properties properties = new Properties();
			try {
				properties.load(FileUtils.openInputStream(personFile));
			} catch (IOException e) {
				throw new NotFoundException(e);
			}
			return personFromProperties(properties);
		}
		return null;
	}

	protected Person personFromProperties(Properties props) {
		BeanWrapper wrapper = new BeanWrapperImpl(Person.class);
		for (Entry<Object, Object> entry : props.entrySet()) {
			String propertyName = entry.getKey().toString();
			if (wrapper.isWritableProperty(propertyName)) {
				wrapper.setPropertyValue(propertyName, entry.getValue());
			}
		}
		return (Person) wrapper.getWrappedInstance();
	}
}
