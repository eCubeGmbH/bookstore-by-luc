package com.example.demo;

import com.example.demo.web.Author;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private Map<String, Author> authors = new HashMap<>();

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        String authorId = UUID.randomUUID().toString();
        author.setId(authorId);
        authors.put(authorId, author);
        return author;
    }

    @GetMapping("/authors/{authorId}")
    Author author(@PathVariable String authorId) {
        return authors.get(authorId);
    }

    @DeleteMapping("/authors/{authorId}")
    public boolean deleteAuthor(@PathVariable String authorId) {
        Author author = authors.remove(authorId);
        return author != null;
    }

    @PutMapping("/authors/{authorId}")
    public Author updateAuthor(@PathVariable String authorId, @RequestBody Author author) {
        author.setId(authorId);
        authors.put(authorId, author);
        return author;
    }
}