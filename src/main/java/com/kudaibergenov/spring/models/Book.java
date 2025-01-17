package com.kudaibergenov.spring.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    private Person person;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, max = 40, message = "Author name should be between 2 and 40 characters")
    private String author;

    @Max(value = 2025, message = "Date should not be greater than 2025")
    private int year;

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
