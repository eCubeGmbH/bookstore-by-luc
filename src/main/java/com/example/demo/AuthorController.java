package com.example.demo;

import com.example.demo.web.Author;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping(value = "/api/author", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private HashMap<String, Author> authorList = new HashMap<>();

    @PostMapping("/iu")
    public Author addAuthor(@RequestBody Author author, String uuid) {
        //add an unique ID to author before saving it to the authorlist | UUID!
        String authorId = UUID.randomUUID().toString();
        author.setId(authorId);
        authorList.put("uuid", author);
        return author;

    }

    @GetMapping("/iu/{uuid}")
    Author author(@PathVariable String uuid) {
        Author author = authorList.get(uuid);
        if (author.getId().equals(uuid)) { //author.id oder author.getId und warum ?
            return author;
        } else {
            return null;
        }

    }

}