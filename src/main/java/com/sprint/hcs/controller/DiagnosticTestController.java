package com.sprint.hcs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.service.IDiagnosticTestService;


@RestController
@RequestMapping("/DiagnosticTest")
public class DiagnosticTestController {
    @Autowired
    IDiagnosticTestService dtestService;

    @Autowired
    LoginController logCon;


    @GetMapping("/getAllTests")
    public List<DiagnosticTest> getAllTest() throws Exception {
        return dtestService.getAllTest();
    }


    @PostMapping("/addNewTest")
    public DiagnosticTest addNewTest(@RequestBody DiagnosticTest test) throws Exception {
        return dtestService.addNewTest(test);
    }


    @GetMapping("/getTestofDiagnosticCenter/{centerId}")
    public List<DiagnosticTest> getTestsOfDiagnosticCenter(@PathVariable int centerId) throws Exception {
        try {
            dtestService.getTestsOfDiagnosticCenter(centerId);
        } catch (Exception e) {
            throw new DataNotFoundInDataBase("Diagnostic center with given id not found");
        }
        return dtestService.getTestsOfDiagnosticCenter(centerId);
    }
    @GetMapping("/getTestById/{diagnosticTestid}")
    public DiagnosticTest getTestById(@PathVariable int diagnosticTestid) throws Exception{
        try {
            dtestService.getTestById(diagnosticTestid);
        }
        catch(Exception e) {
            throw new DataNotFoundInDataBase("Diagnostic test with given id not found");

        }
        return dtestService.getTestById(diagnosticTestid);
    }


    @PutMapping("/updateTestDetail")
    public DiagnosticTest updateTestDetail(@RequestBody DiagnosticTest test)
            throws DataNotFoundInDataBase, ForBiddenException {
        return dtestService.updateTestDetail(test);
    }


    @DeleteMapping("/removeTest/{centerId}/{test}")
    public DiagnosticTest removeTestFromDiagnosticCenter(@PathVariable int centerId, @PathVariable int test)
            throws Exception {
        return dtestService.removeTestFromDiagnosticCenter(centerId, test);
    }
}

