package com.sprint.hcs.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.hcs.dao.IPatientRepository;
import com.sprint.hcs.dao.ITestResultRepository;
import com.sprint.hcs.dao.UserRepository;
import com.sprint.hcs.dao.ManualQueries.QueryClassPersisitContext;
import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
@Service
public class IPatientServiceImpl implements IPatientService {

    @Autowired
    IPatientRepository patRepo;

    @Autowired
    QueryClassPersisitContext qcp;

    @Autowired
    ITestResultRepository testRepo;

    @Autowired
    UserRepository userRepo;




    @Override
    public Patient registerPatient(Patient patient , int userID) throws DataAlreadyExists, DataNotFoundInDataBase {
        if(patRepo.existsById(patient.getPatientId()))throw new DataAlreadyExists("Patient Already exists with id "+ patient.getPatientId()+" use update to change");
        patient.setUser(userRepo.findById(userID).orElseThrow(()->new DataNotFoundInDataBase("No Such User Exists with Id :"+userID)));
        return patRepo.saveAndFlush(patient);
    }



    @Override
    public Patient updatePatientDetails(Patient patient) throws DataNotFoundInDataBase {
        Patient p = patRepo.findById(patient.getPatientId()).orElseThrow(()->new DataNotFoundInDataBase("Patient Details Not Found in DataBase"));
        patient.setUser(p.getUser());
        return patRepo.saveAndFlush(patient);
    }



    @Override
    public Patient viewPatient(String userid) throws DataNotFoundInDataBase {
        return patRepo.findByuser(
                userRepo.findById(Integer.parseInt(userid))
                        .orElseThrow(()->new DataNotFoundInDataBase("No Such User Exists with Id :"+userid)));
    }



    @Override
    public List<TestResult> getAllTestResult(String patientUserName) throws DataNotFoundInDataBase {
        List<TestResult> res = qcp.getAllTestResult(patientUserName);
        if(res == null) throw new DataNotFoundInDataBase("Patient UserName Might Not Exist");
        return res;
    }



    @Override
    public TestResult viewTestResult(int testResultId) throws DataNotFoundInDataBase {
        if(!testRepo.existsById(testResultId))throw new DataNotFoundInDataBase("TestResult Does not Exist!!");
        return testRepo.findById(testResultId).get();
    }


    @Override
    public Patient deletePatient(Patient patient) throws DataNotFoundInDataBase {
        if(!patRepo.existsById(patient.getPatientId()))throw new DataNotFoundInDataBase("Patient Details Not Found in DataBase");
        Patient pat = patRepo.findById(patient.getPatientId()).get();
        patRepo.deleteById(patient.getPatientId());
        return pat;
    }


    @Override
    public List<Patient> getAll() {
        // TODO Auto-generated method stub
        return patRepo.findAll();
    }

}
