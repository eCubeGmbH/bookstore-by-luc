package com.example.demo.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AuthorRepositoryTest {

    @Test
    void addAuthorShouldAddAuthorToRepositoryListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        Author actual = sut.getAuthor(authorFromAddAuthor.getId());

        //then, Verifizieren
        assertNotNull(actual, "response should not be null");
        assertEquals(authorFromAddAuthor.getId(), actual.getId(), "ids should match");
        assertEquals( "Luc", actual.getName(), "names should match");
    }

    @Test
    void addAuthorShouldAddAuthorToRepositoryMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        Author actual = sut.getAuthor(authorFromAddAuthor.getId());

        //then, Verifizieren
        assertNotNull(actual, "response should not be null");
        assertEquals(authorFromAddAuthor.getId(), actual.getId(), "ids should match");
        assertEquals( "Luc", actual.getName(), "names should match");
    }

    @Test
    void deleteAuthorShouldRemoveFromRepositoryListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        sut.deleteAuthor(authorFromAddAuthor.getId());

        //then, Verifizieren
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            sut.deleteAuthor(authorFromAddAuthor.getId());
        });
        String expectedMessage = "The author you requested doesn't exist. Please review your parameters!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteAuthorShouldRemoveFromRepositoryMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        sut.deleteAuthor(authorFromAddAuthor.getId());

        //then, Verifizieren
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            sut.deleteAuthor(authorFromAddAuthor.getId());
        });
        String expectedMessage = "The author you requested doesn't exist. Please review your parameters!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateAuthorShouldUpdateAuthorInRepositoryListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        Author actual = sut.getAuthor(authorFromAddAuthor.getId());
        actual.setId(authorFromAddAuthor.getId());
        actual.setName("David");

        //then, Verifizieren
        assertNotNull(actual, "response should not be null");
        assertEquals(authorFromAddAuthor.getId(), actual.getId(), "ids should match");
        assertNotEquals( "Luc", actual.getName(), "names should not match");
    }

    @Test
    void updateAuthorShouldUpdateAuthorInRepositoryMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Luc");

        //when, Ausführen
        Author authorFromAddAuthor = sut.addAuthor(author1);
        Author actual = sut.getAuthor(authorFromAddAuthor.getId());
        actual.setId(authorFromAddAuthor.getId());
        actual.setName("David");

        //then, Verifizieren
        assertNotNull(actual, "response should not be null");
        assertEquals(authorFromAddAuthor.getId(), actual.getId(), "ids should match");
        assertNotEquals( "Luc", actual.getName(), "names should not match");
    }

    @Test
    void getAllAuthorsShouldGetAllAuthorsInRepositoryListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();

        Author author1 = new Author();
        author1.setName("Dam");
        Author author2 = new Author();
        author2.setName("Dax");
        Author author3 = new Author();
        author3.setName("Dom");
        Author author4 = new Author();
        author4.setName("Dax");
        Author author5 = new Author();
        author5.setName("Dax");
        Author author6 = new Author();
        author6.setName("Dom");
        Author author7 = new Author();
        author7.setName("Dax");
        Author author8 = new Author();
        author8.setName("Dam");
        Author author9 = new Author();
        author9.setName("Dam");
        Author author10 = new Author();
        author10.setName("Dam");

        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);
        sut.addAuthor(author6);
        sut.addAuthor(author7);
        sut.addAuthor(author8);
        sut.addAuthor(author9);
        sut.addAuthor(author10);

        //when, Ausführen

        List<Author> actualListOfAuthors = sut.getAll("dam", 0, 12);

        //then, Verifizieren
        assertNotNull(actualListOfAuthors, "response should not be null");
        assertEquals(4, actualListOfAuthors.size(), "list size should match");
        Assertions.assertIterableEquals(List.of(author1, author8, author9, author10), actualListOfAuthors);
    }

    @Test
    void getAllAuthorsShouldGetAllAuthorsInRepositoryMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();

         Author author1 = new Author();
        author1.setName("Dam");
        author1.setCountry("dda");
        sut.addAuthor(author1);
        Author author2 = new Author();
        author2.setName("Dax");
        sut.addAuthor(author2);
        Author author3 = new Author();
        author3.setName("Dom");
        sut.addAuthor(author3);
        Author author4 = new Author();
        author4.setName("Dax");
        sut.addAuthor(author4);
        Author author5 = new Author();
        author5.setName("Dax");
        sut.addAuthor(author5);
        Author author6 = new Author();
        author6.setName("Dom");
        sut.addAuthor(author6);
        Author author7 = new Author();
        author7.setName("Dax");
        sut.addAuthor(author7);
        Author author8 = new Author();
        author8.setName("Dam");
        author8.setCountry("fff");
        sut.addAuthor(author8);
        Author author9 = new Author();
        author9.setName("Dam");
        author9.setCountry("aaa");
        sut.addAuthor(author9);
        Author author10 = new Author();
        author10.setName("Dam");
        author10.setCountry("hhh");
        sut.addAuthor(author10);

        //when, Ausführen

        List<Author> actualListOfAuthors = sut.getAll("dam", 0, 5);

        //then, Verifizieren
        assertNotNull(actualListOfAuthors, "response should not be null");
        assertEquals(4, actualListOfAuthors.size(), "list size should match");
        Assertions.assertIterableEquals(List.of(author1, author8, author9, author10), actualListOfAuthors);
    }
}















