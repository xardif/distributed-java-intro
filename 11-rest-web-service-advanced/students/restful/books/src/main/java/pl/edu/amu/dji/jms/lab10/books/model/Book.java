package pl.edu.amu.dji.jms.lab10.books.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    public interface MinimalView{}
    public interface FullView extends MinimalView{}

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String isbn;

    private String title;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "AUTHORS", joinColumns = @JoinColumn(name = "BOOK_ID"))
    @Column(name = "AUTHOR")
    private List<String> authors;

    @Transient
    private Iterable<Review> reviews;

    public Book() {
    }

    public Book(String isbn, String title, String description, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.authors = authors;
    }

    @JsonView(MinimalView.class)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonView(MinimalView.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonView(MinimalView.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(MinimalView.class)
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @JsonView(FullView.class)
    public Iterable<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Iterable<Review> reviews) {
        this.reviews = reviews;
    }
}
