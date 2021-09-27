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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.ConflictException;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.service.ITestService;
@RestController
@RequestMapping("/Test")
public class TestController {
    @Autowired
    ITestService testService;

    @Autowired
    LoginController logCon;


    @PostMapping("/addtest")
    public DiagnosticTest addTest(@RequestBody DiagnosticTest test) throws ForBiddenException, DataNotFoundInDataBase {
        return testService.addTest(test);
    }


    @PutMapping("/updatetest")
    public DiagnosticTest updateTest(@RequestBody DiagnosticTest test)
            throws DataNotFoundInDataBase, ForBiddenException {
        return testService.updateTest(test);
    }


    @DeleteMapping("/removetest/{diagnosticTestid}")
    public DiagnosticTest removeTest(@PathVariable int diagnosticTestid) throws DataNotFoundInDataBase, ConflictException, ForBiddenException {
        return testService.removeTest(diagnosticTestid);
    }


    @GetMapping("/viewalltest")
    public List<DiagnosticTest> viewAllTest() throws ForBiddenException {
        return testService.viewAllTest();
    }


    @PostMapping("/addtestincenter")
    public DiagnosticTest addTestInCenter(@RequestParam int testID, @RequestParam int centerId)
            throws ForBiddenException, DataNotFoundInDataBase {
        return testService.addTestInCenter(testID, centerId);
    }
}
