package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import root.dao.UserDao;
import root.model.User;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String getUserPage(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "users";
    }

    @GetMapping(path = "/new")
    public String getCreationPage(Model model) {
        model.addAttribute("user", new User());
        return "users_new";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute(name = "user") User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "users_edit";
    }

    @PatchMapping(path = "/edit/{id}")
    public String editUser(@PathVariable(name = "id") Long id, @ModelAttribute(name = "user") User user) {
        userDao.update(user);
        return "redirect:/users";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}
