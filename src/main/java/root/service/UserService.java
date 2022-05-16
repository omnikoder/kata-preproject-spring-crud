package root.service;

import root.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
}
