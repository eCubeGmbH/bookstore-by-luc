package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AuthorRepositoryListImpl implements AuthorRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRepositoryListImpl.class);

    private List<Author> authorList = new ArrayList();

    private String errorMessage = "The author you requested doesn't exist. Please review your parameters!";

    private int getAuthorIndexInAuthorList(String authorId) {
        for(int i = 0; i < authorList.size(); i++) {
            Author currentItem = authorList.get(i);
            if (currentItem.getId().equals(authorId)) {
                return i;
            }
        }
        return -1;
    }

    public Author addAuthor(Author author) {
        String authorId = UUID.randomUUID().toString();
        author.setId(authorId);
        authorList.add(author);
        return author;
    }

    public List<Author> getAll(String name, Integer from, Integer to) {
        List<Author> filteredList = new ArrayList<>();
        if (from == null) {
            from = 0;
        }
        if (to == null) {
            to = authorList.size();
        }
        for (int i = from; i <= to; i++) {
            if (name == null && from == 0 && to == authorList.size()){
                filteredList = authorList;
                break;
            } else if (name != null) {
                Author currentItem = authorList.get(i);
                if(currentItem.getName().equalsIgnoreCase(name)){
                    filteredList.add(currentItem);
                }
            }else if(name == null) {
                Author currentItem = authorList.get(i);
                filteredList.add(currentItem);
            }
        }
        return filteredList;
    }

        public Author getAuthor (String authorId){
            LOGGER.info("coming from list");
            if (getAuthorIndexInAuthorList(authorId) >= 0) {
                Author author = authorList.get(getAuthorIndexInAuthorList(authorId));
                return author;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
            }
        }

        public void deleteAuthor (String authorId){
            if (getAuthorIndexInAuthorList(authorId) >= 0) {
                authorList.remove(getAuthorIndexInAuthorList(authorId));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
            }
        }

        public Author updateAuthor (String authorId, Author authorFromUser){
            if (getAuthorIndexInAuthorList(authorId) >= 0) {
                authorFromUser.setId(authorId);
                authorList.set(getAuthorIndexInAuthorList(authorId), authorFromUser);
                return authorFromUser;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
            }
        }
    }
