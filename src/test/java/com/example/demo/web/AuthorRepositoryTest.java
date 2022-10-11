package com.example.demo.web;

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
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getAll();

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(5, list.size(), "list size should match");
    }

    @Test
    void getAllAuthorsShouldGetAllAuthorsInRepositoryMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getAll();

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(5, list.size(), "list size should match");
    }

    @Test
    void getAuthorsByNameShouldGetAllAuthorsWithThatNameListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Dux");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dum");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getAllByName("DUm");

        //then, Verifizieren
        assertEquals(2, list.size(), "list size should match");
    }

    @Test
    void getAuthorsByNameShouldGetAllAuthorsWithThatNameMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Dux");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dum");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getAllByName("DUm");

        //then, Verifizieren
        assertEquals(2, list.size(), "list size should match");
    }

    @Test
    void getAuthorsFromToShouldGetAllAuthorsFromIndexToIndexListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getPaginated(2, 4);

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(3, list.size(), "list size should match");
    }

    @Test
    void getAuthorsFromToShouldGetAllAuthorsFromIndexToIndexMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");

        //when, Ausführen
        sut.addAuthor(author1);
        sut.addAuthor(author2);
        sut.addAuthor(author3);
        sut.addAuthor(author4);
        sut.addAuthor(author5);

        List<Author> list = sut.getPaginated(2, 4);

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(3, list.size(), "list size should match");
    }

    @Test
    void getAuthorsPaginatedAndFilteredByNameListImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryListImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");
        Author author6 = new Author();
        author6.setName("Dam");
        Author author7 = new Author();
        author7.setName("Dam");
        author7.setCountry("aadf");
        Author author8 = new Author();
        author8.setName("Dam");
        author8.setCountry("cz");
        Author author9 = new Author();
        author9.setName("Dam");
        Author author10 = new Author();
        author10.setName("Dam");

        //when, Ausführen
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

        List<Author> list = sut.getPaginatedAndName(2, 6, "dam");

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(3, list.size(), "list size should match");
    }

    @Test
    void getAuthorsPaginatedAndFilteredByNameMapImpl() {
        //setup, Vorbereitung (sut) system under test
        AuthorRepository sut = new AuthorRepositoryMapImpl();
        Author author1 = new Author();
        author1.setName("Dee");
        Author author2 = new Author();
        author2.setName("Dux");
        Author author3 = new Author();
        author3.setName("Doo");
        Author author4 = new Author();
        author4.setName("Dum");
        Author author5 = new Author();
        author5.setName("Dam");
        Author author6 = new Author();
        author6.setName("Dam");
        Author author7 = new Author();
        author7.setName("Dam");
        author7.setCountry("aadf");
        Author author8 = new Author();
        author8.setName("Dam");
        author8.setCountry("cz");
        Author author9 = new Author();
        author9.setName("Dam");
        Author author10 = new Author();
        author10.setName("Dam");

        //when, Ausführen
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

        List<Author> list = sut.getPaginatedAndName(2, 6, "dam");

        //then, Verifizieren
        assertNotNull(list, "response should not be null");
        assertEquals(3, list.size(), "list size should match");
    }
}


