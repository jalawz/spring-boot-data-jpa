package com.apress.demo;

import com.apress.demo.domain.User;
import com.apress.demo.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsers() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void findUserById() {
        Optional<User> user = userRepository.findById(1);
        assertNotNull(user.get());
    }

    /*@Test
    public void createUser() {
        User user = new User("Paul", "paul@gmail.com", false);
        user.setId(100);
        User savedUser = userRepository.save(user);
        User newUser = userRepository.findById(savedUser.getId()).get();
        assertEquals("Paul", newUser.getName());
        assertEquals("paul@gmail.com", newUser.getEmail());
    }*/

    @Test
    public void sortAllUsers() {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        List<User> users = userRepository.findAll(sort);
        assertNotNull(users);
    }

    @Test
    public void sortAllUsersMultiple() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(order1, order2);
        List<User> users = userRepository.findAll(sort);
        assertNotNull(users);
    }

    @Test
    public void findUsersPaged() {
        int size = 25;
        int page = 0; // zero-based page index.
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAll(pageable);
        assertNotNull(usersPage);
    }
}
