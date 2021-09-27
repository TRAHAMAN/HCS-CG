package com.sprint.hcs.service;

import java.util.List;


import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;

public interface IDiagnosticTestService {

    public List<DiagnosticTest> getAllTest();
    DiagnosticTest addNewTest(DiagnosticTest test) throws DataAlreadyExists;
    List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId) throws Exception;
    DiagnosticTest updateTestDetail(DiagnosticTest test) throws DataNotFoundInDataBase;
    DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int test) throws Exception;
    DiagnosticTest getTestById(int diagnosticTestid) throws DataNotFoundInDataBase;

}

