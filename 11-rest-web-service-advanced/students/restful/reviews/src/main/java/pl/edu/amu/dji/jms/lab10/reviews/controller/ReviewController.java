package pl.edu.amu.dji.jms.lab10.reviews.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.amu.dji.jms.lab10.reviews.model.Review;
import pl.edu.amu.dji.jms.lab10.reviews.repository.ReviewRepository;

import java.util.List;

@RestController
@RequestMapping(value="/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @RequestMapping(value="/{isbn}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<Review> getAllReviews(@PathVariable String isbn) {
        List<Review> reviews = repository.findByIsbn(isbn);
        return reviews;
    }

    @RequestMapping(value="/{isbn}",method = RequestMethod.POST,headers="Accept=application/json")
    public Review addReview(@PathVariable String isbn, @RequestBody Review review) {
        if(!review.getIsbn().equals(isbn))
            throw new ReviewWithInvalidIsbnException();

        repository.save(review);
        return review;
    }

    @RequestMapping(value="/{isbn}/{id}",method = RequestMethod.GET,headers="Accept=application/json")
    public Review getReview(@PathVariable String isbn, @PathVariable String id) {
        Review review = repository.findByIsbnAndId(isbn, id);
        if(review == null)
            throw new ReviewNotFoundException();

        return review;
    }

    @RequestMapping(value="/{isbn}/{id}",method = RequestMethod.PUT,headers="Accept=application/json")
    public Review updateReview(@PathVariable String isbn, @PathVariable String id, @RequestBody Review review) {
        if(!review.getIsbn().equals(isbn))
            throw new ReviewWithInvalidIsbnException();

        Review toUpdate = repository.findByIsbnAndId(isbn, id);
        if(toUpdate == null)
            throw new ReviewNotFoundException();

        review.setId(toUpdate.getId());
        repository.save(review);

        return review;
    }

    @RequestMapping(value="/{isbn}/{id}",method = RequestMethod.DELETE,headers="Accept=application/json")
    public void deleteReview(@PathVariable String isbn, @PathVariable String id) {
        Review toDelete = repository.findByIsbnAndId(isbn, id);
        if(toDelete == null)
            throw new ReviewNotFoundException();

        repository.delete(toDelete);
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Review not found")
    public class ReviewNotFoundException extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Invalid Isbn Exception")
    public class ReviewWithInvalidIsbnException extends RuntimeException {}
}
