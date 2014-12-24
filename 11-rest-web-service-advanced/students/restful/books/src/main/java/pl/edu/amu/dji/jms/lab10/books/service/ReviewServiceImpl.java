package pl.edu.amu.dji.jms.lab10.books.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.amu.dji.jms.lab10.books.model.Review;

import javax.persistence.EntityManagerFactory;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Cacheable("reviews")
    public Iterable<Review> getReviews(String isbn) {
        ParameterizedTypeReference<Iterable<Review>> responseType = new ParameterizedTypeReference<Iterable<Review>>() {};

        ResponseEntity<Iterable<Review>> responseEntity = restTemplate.exchange("http://localhost:8090/reviews/" + isbn, HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }
}