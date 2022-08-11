package com.example.demo;

import com.example.demo.web.Author;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping(value = "/api/author", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private List<Author> authorList = new ArrayList<>();

    @PostMapping("/iu")
    public Author addAuthor(@RequestBody Author author) {
        //add an unique ID to author before saving it to the authorlist | UUID!
        String authorId = UUID.randomUUID().toString();
        author.setId(authorId);
        authorList.add(author);
        return author;

    }

    @GetMapping("/iu/{uuid}")
    Author author(@PathVariable String uuid) {
        for (int i = 0; i < authorList.size(); i++) {
            Author currentItem = authorList.get(i);
            if (currentItem.getId().equals(uuid)) {
                return currentItem;
            }

        }
        return null;
    }

}