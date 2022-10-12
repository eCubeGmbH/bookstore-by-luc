package com.example.demo.web;

import java.util.List;

public interface AuthorRepository {
     Author addAuthor(Author author);

     Author getAuthor(String authorId);

     void deleteAuthor(String authorId);

     Author updateAuthor(String authorId, Author authorFromUser);

     default List<Author> getAll(String name, Integer from, Integer to){
          return List.of();
     }
}
