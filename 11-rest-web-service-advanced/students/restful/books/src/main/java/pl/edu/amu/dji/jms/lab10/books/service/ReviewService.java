package pl.edu.amu.dji.jms.lab10.books.service;

import pl.edu.amu.dji.jms.lab10.books.model.Review;

public interface ReviewService {
    Iterable<Review> getReviews(String isbn);
}