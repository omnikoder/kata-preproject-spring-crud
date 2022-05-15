package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    public String saveUser(@ModelAttribute(name = "user") User user) {
        userDao.save(user);
        return "redirect:/users";
    }
}
