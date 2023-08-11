package com.example.library.Controllers;

import com.example.library.DAO.BookDAO;
import com.example.library.DAO.UserDAO;
import com.example.library.Models.Book;
import com.example.library.Models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final UserDAO userDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
            model.addAttribute("users",userDAO.index());
            model.addAttribute("user", new User());
            model.addAttribute("belong",bookDAO.belong(id));
        return "books/show";
    }

    @DeleteMapping("/{id}/edit")
    public String free(@PathVariable("id") int id){
        bookDAO.free(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/edit")
    public String assign(@PathVariable("id") int id, @ModelAttribute("user") User user){
        bookDAO.assign(id, user);
        return "redirect:/books/"+id;
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("book",new Book());
        return "books/create";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/create";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(book,id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
