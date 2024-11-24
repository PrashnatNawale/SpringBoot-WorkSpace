package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Models.UserLogin;
import com.example.demo.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.id FROM User u WHERE u.email = :email AND u.password = :password")
    Long findIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

