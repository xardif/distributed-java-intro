package com.uam.exercise2;

import com.uam.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreExchangeIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void shouldReturnListOfBooksAsJsonString() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity response = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
	}

	@Test
	public void shouldReturnListOfBooksAsJsonObject() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);

        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> response = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, responseType);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
	}

	@Test
	public void shouldReturnListOfBooksAsXmlString() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/xml");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity response = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
    }

	@Test
	public void shouldReturnListOfBooksAsXmlObject() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/xml");
        HttpEntity entity = new HttpEntity(headers);

        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> response = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, responseType);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
	}
}

