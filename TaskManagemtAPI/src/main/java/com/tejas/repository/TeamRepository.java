package com.tejas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // Custom query methods if necessary
}
