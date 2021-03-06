package com.sprint.hcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.hcs.dao.IDiagnosticTestRepository;
import com.sprint.hcs.dao.ManualQueries.QueryClassPersisitContext;
import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;

@Service
public class IDiagnosticTestServiceImpl implements IDiagnosticTestService {

    @Autowired
    IDiagnosticTestRepository testRepo;
    @Autowired
    QueryClassPersisitContext qcp;


    @Override
    public List<DiagnosticTest> getAllTest() {
        return testRepo.findAll();
    }



    @Override
    public DiagnosticTest addNewTest(DiagnosticTest test) throws DataAlreadyExists {
        if(testRepo.existsById(test.getDiagonasticTestid())) throw new DataAlreadyExists("Test Already Exists Use Update To Change");
        return testRepo.saveAndFlush(test);
    }



    @Override
    public List<DiagnosticTest> getTestsOfDiagnosticCenter(int centerId) throws DataNotFoundInDataBase {
        List<DiagnosticTest> tests = qcp.getTestsOfDiagnosticCenter(centerId);
        if(tests.size()==0)throw new DataNotFoundInDataBase("No Diagnostic Tests Exist");
        return tests;
    }
    @Override
    public DiagnosticTest getTestById(int diagnosticTestid) throws DataNotFoundInDataBase {
        //DiagnosticTest test = qcp.getTestById(diagnosticTestid);
        return testRepo.findById(diagnosticTestid).get();
    }



    @Override
    public DiagnosticTest updateTestDetail(DiagnosticTest test) throws DataNotFoundInDataBase{

        if(!testRepo.existsById(test.getDiagonasticTestid())) throw new DataNotFoundInDataBase("No test Exist with id : "+test.getDiagonasticTestid()+" To Update");

        return testRepo.saveAndFlush(test);
    }



    @Override
    public DiagnosticTest removeTestFromDiagnosticCenter(int centerId, int test) throws Exception {
        return qcp.removeTestFromDiagnosticCenter(centerId, test);
    }




}

