package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class AuthorRepositoryMapImpl implements AuthorRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRepositoryMapImpl.class);
    private Map<String, Author> authorMap = new HashMap<>();

    private String errorMessage = "The author you requested doesn't exist. Please review your parameters!";

    private Object errorChecking(String authorId) {
        if(authorMap.get(authorId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
    }

    public Author addAuthor(Author author) {
        String authorId = UUID.randomUUID().toString();
        author.setId(authorId);
        authorMap.put(authorId, author);
        return author;
    }

    public List<Author> getAll() {
        return new ArrayList<>(authorMap.values());
    }

    public ArrayList<Author> getAllByName(String name) {
        ArrayList<Author> filteredList = new ArrayList<>();
        for (Author currentItem : getAll()) {
            if (currentItem.getName().equalsIgnoreCase(name)) {
                filteredList.add(currentItem);
            }
        }
        return filteredList;
    }

    public List<Author> getPaginated(int from, int to){
        List<Author> authorList = new ArrayList<>(authorMap.values());
        List<Author> paginatedList = new ArrayList<>();
        if (from <= to) {
            for (int i = from; to < authorList.size(); i++) {
                Author currentItem = authorList.get(i);
                paginatedList.add(currentItem);
                if(i == to) {
                    break;
                }
            }
        }
        return paginatedList;
    }

    public List<Author> getPaginatedAndName(int from, int to, String name) {
        List<Author> dummyList;
        List<Author> filteredList = new ArrayList<>();
        dummyList = getPaginated(from, to);
        for (Author currentItem : dummyList) {
            if (currentItem.getName().equalsIgnoreCase(name)) {
                filteredList.add(currentItem);
            }
        }
        return filteredList;
    }

    public Author getAuthor(String authorId) {
        LOGGER.info("coming from map");
        errorChecking(authorId);
        Author getAuthor = authorMap.get(authorId);
        return getAuthor;
    }

    public void deleteAuthor(String authorId) {
        errorChecking(authorId);
        authorMap.remove(authorId);
    }

    public Author updateAuthor(String authorId, Author authorFromUser) {
        errorChecking(authorId);
        authorFromUser.setId(authorId);
        authorMap.replace(authorId, authorFromUser);
        return authorFromUser;
    }
}
