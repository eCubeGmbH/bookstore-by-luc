package com.example.demo.web;

import java.time.LocalDate;
import java.util.Objects;

public class Author {
    private String id;
    private String name;
    private String country;
    private LocalDate birthDate;

    public static void main(String[] args) {
    }

    public Author() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Author)) {
            return false;
        } else {
            Author author = (Author) o;
            return Objects.equals(this.id, author.id);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name, this.country, this.birthDate});
    }

    public String toString() {
        return "Author{id=" + this.id + ", name='" + this.name + "', country='" + this.country + "', birthDate='" + this.birthDate + "'}";
    }
}
