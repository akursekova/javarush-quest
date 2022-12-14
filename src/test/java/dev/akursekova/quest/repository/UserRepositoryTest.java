package dev.akursekova.quest.repository;

import dev.akursekova.quest.subjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private Map<String, User> users;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        this.userRepository = new UserRepository();
        users = new HashMap<>();
        userRepository.users = (HashMap<String, User>) users;

        users.put("user1", new User());
        users.put("user2", new User());
    }

    @Test
    void add_WhenUserHasEmptyName() {
        User user3 = new User();
        user3.setName("");

        assertThrows(IllegalArgumentException.class,
                () -> userRepository.add(user3));
    }

    @Test
    void add_WhenNewUserBeAdded() {
        User user3 = new User();
        user3.setName("user3");

        userRepository.add(user3);

        assertEquals(3, users.size());
        assertEquals(user3, users.get("user3"));
    }

    @Test
    void exists_WhenUserByUserNameExists() {
        assertEquals(true, userRepository.exists("user1"));
    }

    @Test
    void exists_WhenUserByUserNameDoesntExist() {
        assertEquals(false, userRepository.exists("user3"));
    }
}
