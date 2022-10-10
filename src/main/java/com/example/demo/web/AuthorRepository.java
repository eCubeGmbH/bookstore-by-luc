package com.example.demo.web;

import java.util.List;

public interface AuthorRepository {
     Author addAuthor(Author author);

     Author getAuthor(String authorId);

     void deleteAuthor(String authorId);

     Author updateAuthor(String authorId, Author authorFromUser);

     List<Author> getAll();

     default List<Author> getAllByName(String name){
          return List.of();
     }

     default List<Author> getPaginated(int from, int to) {
          return List.of();
     }

     default List<Author> getPaginatedAndName(int from, int to, String name) {
          return List.of();
     }
}
