package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import root.model.User;
import root.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(path = "/new")
    public String getCreationPage(Model model) {
        model.addAttribute("user", new User());
        return "users_new";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute(name = "user") @Valid User user,
                           BindingResult bindingResult) {
        userService.validateEmail(user.getEmail(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "users_new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users_edit";
    }

    @PatchMapping(path = "/edit/{id}")
    public String editUser(@ModelAttribute(name = "user") @Valid User user,
                           BindingResult bindingResult) {
        userService.validateEmail(user.getEmail(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "users_edit";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
