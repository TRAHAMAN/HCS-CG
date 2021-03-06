package com.sprint.hcs.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.hcs.dao.ITestResultRepository;
import com.sprint.hcs.dao.ManualQueries.QueryClassPersisitContext;
import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.TestResultNotFoundException;

@Service
public class ITestResultServiceImpl implements ITestResultService {

    @Autowired
    ITestResultRepository resultrepo;

    @Autowired
    QueryClassPersisitContext qcp;


    @Override
    public TestResult addTestResult(TestResult tr)  throws DataAlreadyExists{

        if(resultrepo.existsById(tr.getTestResultid())) throw new DataAlreadyExists("Test Result Already exists with id :"+tr.getTestResultid());
        return resultrepo.saveAndFlush(tr);
    }



    @Override
    public TestResult updateResult(TestResult tr) throws DataNotFoundInDataBase {
        if(!resultrepo.existsById(tr.getTestResultid()))throw new DataNotFoundInDataBase("TestResult Not Found in DataBase to update");
        return resultrepo.saveAndFlush(tr);
    }



    @Override
    public List<TestResult> removeTestResult(int id) throws TestResultNotFoundException {
        // TODO Auto-generated method stub
        if(!resultrepo.existsById(id)) throw new TestResultNotFoundException("Test Result Does Not Exist  "+ id);
        TestResult tr = resultrepo.findById(id).get();
        resultrepo.deleteById(id);
        return resultrepo.findAll();

    }





    @Override
    public List<TestResult> viewResultsByPatient(Patient patient) throws DataNotFoundInDataBase, TestResultNotFoundException {
        List<TestResult> testRes =  qcp.viewResultsByPatient(patient);
        if(testRes.size() ==0 )throw new DataNotFoundInDataBase("User/Tests Doesn't Exits");
        return testRes;
    }


    @Override
    public List<TestResult> getAll() {
        return resultrepo.findAll();
    }

    @Override
    public TestResult getById(int id) throws DataNotFoundInDataBase {
        // TODO Auto-generated method stub
        return resultrepo.findById(id).orElseThrow(()-> new DataNotFoundInDataBase("No Patient With ID "+id));
    }







}
