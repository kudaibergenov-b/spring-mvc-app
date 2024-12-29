package com.kudaibergenov.spring.dao;

import com.kudaibergenov.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int count;
    private final List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++count, "Tom"));
        people.add(new Person(++count, "Bob"));
        people.add(new Person(++count, "Mike"));
        people.add(new Person(++count, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public void save(Person person) {
        person.setId(++count);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        show(id).setName(updatedPerson.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}