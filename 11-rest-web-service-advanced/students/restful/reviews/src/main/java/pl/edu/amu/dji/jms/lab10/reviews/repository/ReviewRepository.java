package pl.edu.amu.dji.jms.lab10.reviews.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.amu.dji.jms.lab10.reviews.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository  extends CrudRepository<Review, String> {

    public List<Review> findByIsbn(String isbn);

    public Review findByIsbnAndId(String isbn, String id);
}
