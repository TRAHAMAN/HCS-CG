package com.sprint.hcs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;



import com.sprint.hcs.entities.DiagnosticCenter;
import com.sprint.hcs.entities.DiagnosticTest;

@Repository
public interface IDiagnosticCenterRepository{
    List<DiagnosticTest> viewTestDetails(int diagnosticCenterId);
    DiagnosticCenter getDiagnosticCenter(String centername);
}

