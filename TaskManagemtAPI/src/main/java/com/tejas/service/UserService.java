package com.tejas.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.entity.Roles;
import com.tejas.entity.User;
import com.tejas.enums.Role;
import com.tejas.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void assignRoleToUser(Long userId, Roles role) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Roles> roles = user.getRoles();
        if (!roles.contains(role)) {
            roles.add(role); // Add the role if it's not already assigned
        }

        userRepository.save(user);
    }

    // Other user management methods (createUser, deleteUser, etc.)
}
