package root.dao;

import org.springframework.validation.BindingResult;
import root.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
    boolean isEmailExisting(String email);
    void validateEmail(String email, BindingResult bindingResult);
}
