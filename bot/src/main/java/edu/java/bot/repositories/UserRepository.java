package edu.java.bot.repositories;

import edu.java.bot.model.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

@Repository
public class UserRepository {
    private HashMap<Long, User> users = new HashMap<>();;

    public UserRepository(){

    }
}
