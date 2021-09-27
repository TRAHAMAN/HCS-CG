package com.sprint.hcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint.hcs.entities.TestResult;

@Repository
public interface ITestResultRepository extends JpaRepository<TestResult, Integer> {

}
