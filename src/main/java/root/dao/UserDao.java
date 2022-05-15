package root.dao;

import root.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
}
