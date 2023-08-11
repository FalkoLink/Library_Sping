package com.example.library.Controllers;

import com.example.library.DAO.BookDAO;
import com.example.library.DAO.UserDAO;
import com.example.library.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users",userDAO.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userDAO.show(id));
        model.addAttribute("books", userDAO.books(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("user",new User());
        return "users/create";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user){
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userDAO.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        userDAO.update(user,id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userDAO.delete(id);
        return "redirect:/users";
    }

}
