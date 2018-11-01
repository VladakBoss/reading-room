package com.klz.redingroom.readingroom.controller;

import com.klz.redingroom.readingroom.domain.Book;
import com.klz.redingroom.readingroom.repositories.BookRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/book"})
public class BookController {

    private final BookRepo bookRepo;

    @Autowired
    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public List<Book> list() {
        return bookRepo.findAll();
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable("id") Book book) {
        return book;
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        return bookRepo.save(book);
    }

    @PutMapping("{id}")
    public Book update(
            @PathVariable("id") Book bookFromDb,
            @RequestBody Book book
    ){
        BeanUtils.copyProperties(book, bookFromDb, "id");

        return bookRepo.save(book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Book book){
        bookRepo.delete(book);
    }
}
