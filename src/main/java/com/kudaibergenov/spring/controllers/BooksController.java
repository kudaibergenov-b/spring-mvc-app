package com.kudaibergenov.spring.controllers;

import com.kudaibergenov.spring.dao.BookDAO;
import com.kudaibergenov.spring.dao.PersonDAO;
import com.kudaibergenov.spring.models.Book;
import com.kudaibergenov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("p") Person p) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", bookDAO.getPerson(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "books/new";}

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "books/edit";}

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/free/{id}")
    public String free(@PathVariable("id") int id, Model model) {
        bookDAO.free(id);
        model.addAttribute("book", bookDAO.show(id));

        return "redirect:/books/{id}";
    }

    @PatchMapping("/appoint/{id}")
    public String appoint(@PathVariable("id") int id ,@ModelAttribute("person") Person person) {
        bookDAO.appoint(id, person.getId());

        return "redirect:/books/{id}";
    }
}
