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

import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    IPatientService patientService;

    @Autowired
    LoginController logCon;


    @PostMapping("/registerpatient/{userID}")
    public Patient registerPatient(@RequestBody Patient patient,@PathVariable int userID) throws DataAlreadyExists, DataNotFoundInDataBase {
        return patientService.registerPatient(patient,userID);
    }


    @PutMapping("/updatepatient")
    public Patient updatePatientDetails(@RequestBody Patient patient)
            throws DataNotFoundInDataBase, ForBiddenException {
        return patientService.updatePatientDetails(patient);
    }


    @GetMapping("/viewpatient/{userid}")
    Patient viewPatient(@PathVariable String userid) throws ForBiddenException, DataNotFoundInDataBase {
        return patientService.viewPatient(userid);
    }


    @GetMapping("/viewtestresult/{testResultId}")
    TestResult viewTestResult(@PathVariable int testResultId) throws Exception {
        return patientService.viewTestResult(testResultId);
    }

    @DeleteMapping("/deletePatient")
    Patient deletePatient(@RequestBody Patient patient) throws DataNotFoundInDataBase, ForBiddenException {
        return patientService.deletePatient(patient);
    }

    @GetMapping("/getAll")
    public List<Patient> getAll(){
        return patientService.getAll();
    }
}

