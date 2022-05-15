package root.dao;

import org.springframework.stereotype.Component;
import root.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private static long COUNTER;
    private static final List<User> userList;

    static {
        userList = new ArrayList<>();
        userList.add(new User(++COUNTER,"Tom Cooper", 55, "tom@gmail.com"));
        userList.add(new User(++COUNTER, "Jay Z", 50, "jay@gmail.com"));
        userList.add(new User(++COUNTER, "Иван Иванов", 30, "ivan@mail.ru"));
        userList.add(new User(++COUNTER, "Петр Петров", 20, "petr@mail.ru"));
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        return userList
                .stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(++COUNTER);
        userList.add(user);
    }

    @Override
    public void update(User user) {
        User old = getUserById(user.getId());
        old.setName(user.getName());
        old.setAge(user.getAge());
        old.setEmail(user.getEmail());
    }

    @Override
    public void delete(Long id) {
        userList.removeIf(user -> id.equals(user.getId()));
    }
}
