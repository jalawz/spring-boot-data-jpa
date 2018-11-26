package com.apress.demo.repositories;

import com.apress.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    List<User> findByNameLike(String name);

    @Query("select u from User u where u.name like %?1%")
    List<User> searchByName(String name);
}
