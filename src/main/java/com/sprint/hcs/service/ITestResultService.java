package com.sprint.hcs.service;

import java.util.List;



import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.TestResultNotFoundException;

public interface ITestResultService {

    public TestResult addTestResult(TestResult tr)throws DataAlreadyExists;
    public TestResult updateResult(TestResult tr) throws DataNotFoundInDataBase;
    public List<TestResult> removeTestResult(int id)throws TestResultNotFoundException;
    public List<TestResult> viewResultsByPatient(Patient patient) throws DataNotFoundInDataBase, TestResultNotFoundException;
    public List<TestResult> getAll();
    public TestResult getById(int id) throws DataNotFoundInDataBase;



}

