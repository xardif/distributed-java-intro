package pl.edu.amu.dji.jms.lab10.books.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;
import pl.edu.amu.dji.jms.lab10.books.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping(value="/books")
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<Book> getAllBooks() {
        List<Book> books = Lists.newArrayList(repository.findAll());
        return books;
    }

    @RequestMapping(value="/{book}",method = RequestMethod.GET,headers="Accept=application/json")
    public Book getBook(@PathVariable Book book) {
        if(book == null)
            throw new BookNotFoundException();

        book.setReviews(reviewService.getReviews(book.getIsbn()));
        return book;
    }

    @RequestMapping(method = RequestMethod.POST,headers="Accept=application/json")
    public Book createNewBook(@RequestBody Book book) {
        if(repository.exists(book.getIsbn()))
            throw new BookAlreadyExistsException();
        repository.save(book);
        return book;
    }

    @RequestMapping(value="/{book}",method = RequestMethod.PUT,headers="Accept=application/json")
    public Book editBook(@PathVariable Book book, @RequestBody Book changedBook) {
        if(book == null)
            throw new BookNotFoundException();
        book = changedBook;
        repository.save(book);
        return book;
    }

    @RequestMapping(value="/{book}",method = RequestMethod.DELETE,headers="Accept=application/json")
    public void deleteBook(@PathVariable Book book) {
        if(book == null)
            throw new BookNotFoundException();
        repository.delete(book);
    }

    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Book already exists")
    public class BookAlreadyExistsException extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Book not found")
    public class BookNotFoundException extends RuntimeException {}
}
