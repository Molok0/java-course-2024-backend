package edu.java.bot.repositories;

import edu.java.bot.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private HashMap<Long, User> users = new HashMap<>();

    public UserRepository() {

    }

    public User findById(Long id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>();
    }

    public void addUser(Long id, User user) {
        users.put(id, user);
    }

    public boolean siteInRepository(String s) {
        return true;
    }
}
