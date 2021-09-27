package com.sprint.hcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint.hcs.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByusername(String username);
}