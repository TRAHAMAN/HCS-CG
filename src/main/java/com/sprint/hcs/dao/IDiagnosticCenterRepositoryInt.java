package com.sprint.hcs.dao;

import com.sprint.hcs.entities.DiagnosticCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDiagnosticCenterRepositoryInt extends IDiagnosticCenterRepository,JpaRepository<DiagnosticCenter, Integer>{


}
