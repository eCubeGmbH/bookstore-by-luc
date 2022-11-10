package com.example.demo.web;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value = {"/api/authors"})
@CrossOrigin
class AuthorController {

    AuthorRepository repository;

    AuthorController() {
        this.repository = new AuthorRepositoryListImpl();
    }

    @PostMapping(consumes = {"application/json"},
                produces = {"application/json"})
    Author addAuthor(@RequestBody Author author) {
        return repository.addAuthor(author);
    }

    @ResponseBody
    @GetMapping(produces = {"application/json"})
    List<Author> getAllAuthors(@RequestParam(required = false) String name, Integer from, Integer to){
        return repository.getAll(name, from, to);
    }

    @ResponseBody
    @GetMapping(value = {"/{authorId}"},
                produces = {"application/json"})
    Author getAuthor(@PathVariable String authorId){
        return repository.getAuthor(authorId);
    }

    @ResponseBody
    @DeleteMapping(value = {"/{authorId}"},
                   consumes = {"application/json"})
    void removeAuthor(@PathVariable String authorId){
        repository.deleteAuthor(authorId);
    }

    @ResponseBody
    @PutMapping(value = {"/{authorId}"},
                consumes = {"application/json"},
                produces = {"application/json"})
    Author updateAuthor(@PathVariable String authorId, @RequestBody Author authorFromUser){
        return repository.updateAuthor(authorId, authorFromUser);
    }
}
