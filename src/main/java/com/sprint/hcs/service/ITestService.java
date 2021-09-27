package com.sprint.hcs.service;


import java.util.List;
import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.ConflictException;
import com.sprint.hcs.exception.DataNotFoundInDataBase;

public interface ITestService {

    public DiagnosticTest addTest(DiagnosticTest test) throws DataNotFoundInDataBase;
    public DiagnosticTest updateTest(DiagnosticTest test) throws DataNotFoundInDataBase;
    public DiagnosticTest removeTest(int diagnosticTestid) throws DataNotFoundInDataBase, ConflictException;
    public List<DiagnosticTest> viewAllTest();

    DiagnosticTest addTestInCenter(int testID, int centerId) throws DataNotFoundInDataBase;

}

