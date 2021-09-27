package com.sprint.hcs.service;

import java.util.List;



import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;

public interface IPatientService {

    Patient registerPatient(Patient patient, int userID) throws DataAlreadyExists, DataNotFoundInDataBase;
    Patient updatePatientDetails(Patient patient) throws DataNotFoundInDataBase;
    Patient viewPatient(String patientUserName) throws DataNotFoundInDataBase;
    List<TestResult> getAllTestResult(String patientUserName) throws DataNotFoundInDataBase;
    TestResult viewTestResult(int testResultId) throws DataNotFoundInDataBase;
    Patient deletePatient(Patient patient) throws DataNotFoundInDataBase;
    List<Patient> getAll();

}

