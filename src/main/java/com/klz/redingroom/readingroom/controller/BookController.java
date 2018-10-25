package com.klz.redingroom.readingroom.controller;

import com.klz.redingroom.readingroom.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {

    private int counter = 4;

    private List<Map<String, String>> books = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("name", "Bible");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("name", "Developer book");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("name", "War and Peace");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return books;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getBook(id);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> book){
        book.put("id", String.valueOf(counter++));
        books.add(book);

        return book;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> book){
        Map<String, String> bookFromDB = getBook(book.get("id"));

        bookFromDB.putAll(book);
        bookFromDB.put("id", id);

        return bookFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> book = getBook(id);
        books.remove(book);
    }

    private Map<String, String> getBook(String id) {
        return books.stream()
                .filter(book -> book.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
