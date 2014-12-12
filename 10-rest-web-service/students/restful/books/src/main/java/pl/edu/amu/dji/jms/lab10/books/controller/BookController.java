package pl.edu.amu.dji.jms.lab10.books.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping(value="/books",method = RequestMethod.GET,headers="Accept=application/json")
    public List<Book> getAllBooks() {
        List<Book> books = Lists.newArrayList(repository.findAll());
        return books;
    }

    @RequestMapping(value="/books/{bookId}",method = RequestMethod.GET,headers="Accept=application/json")
    public Book getBook(@PathVariable("bookId") Book book) {
        if(book == null)
            throw new BookNotFoundException();
        return book;
    }

    @RequestMapping(value="/books",method = RequestMethod.POST,headers="Accept=application/json")
    public Book createNewBook(@RequestBody Book book) {
        if(repository.exists(book.getIsbn()))
            throw new BookAlreadyExistsException();
        repository.save(book);
        return book;
    }

    @RequestMapping(value="/books/{bookId}",method = RequestMethod.PUT,headers="Accept=application/json")
    public Book editBook(@PathVariable("bookId") Book book, @RequestBody Book changedBook) {
        if(book == null)
            throw new BookNotFoundException();
        book = changedBook;
        repository.save(book);
        return book;
    }

    @RequestMapping(value="/books/{bookId}",method = RequestMethod.DELETE,headers="Accept=application/json")
    public void deleteBook(@PathVariable("bookId") Book book) {
        if(book == null)
            throw new BookNotFoundException();
        repository.delete(book);
    }

    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Book already exists")
    public class BookAlreadyExistsException extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Book not found")
    public class BookNotFoundException extends RuntimeException {}
}
