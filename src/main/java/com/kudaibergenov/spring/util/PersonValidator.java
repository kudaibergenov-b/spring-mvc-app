package com.kudaibergenov.spring.util;

import com.kudaibergenov.spring.dao.PersonDAO;
import com.kudaibergenov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator
{
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    // Проверка на дубликат по имени
    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.getPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Человек с таким именем уже существует");
        }
    }
}
