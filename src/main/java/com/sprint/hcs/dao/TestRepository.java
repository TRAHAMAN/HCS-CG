package com.sprint.hcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint.hcs.entities.DiagnosticTest;

@Repository
public interface TestRepository extends JpaRepository<DiagnosticTest, Integer>{

}

