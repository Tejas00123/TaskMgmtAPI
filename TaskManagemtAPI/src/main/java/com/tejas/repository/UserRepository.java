package com.tejas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods if necessary
}
