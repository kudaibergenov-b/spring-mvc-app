package com.kudaibergenov.spring.dao;

import com.kudaibergenov.spring.models.Book;
import com.kudaibergenov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                        .stream().findAny().orElse(null);
    }

    // Поиск человека по имени
    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    // Список книг человека
    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE personid=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, dateOfBirth) VALUES (?, ?)",
                person.getName(), person.getDateOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, dateOfBirth=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getDateOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}