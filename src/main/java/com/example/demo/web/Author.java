package com.example.demo.web;


import java.util.Objects;


public class Author {
    private  String id;
    private String name;
    private String country;
    private String birthDate;

    public Author(String name, String country, String birthDate) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String setId(String authorId) {
        return id;
    }

    public String getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Author))
            return false;
        Author author = (Author) o;
        return Objects.equals(this.id, author.id) && Objects.equals(this.name, author.name)
                && Objects.equals(this.country, author.country) && Objects.equals(this.birthDate, author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.country, this.birthDate);
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + this.id + ", name='" + this.name + '\'' + ", country='" + this.country + '\'' + ", birthdate'" + this.birthDate + '\'' + '}';
    }


}
