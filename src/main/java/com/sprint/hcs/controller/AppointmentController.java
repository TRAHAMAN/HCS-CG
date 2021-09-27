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

import com.sprint.hcs.entities.Appointment;
import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.AppointmentNotFoundException;
import com.sprint.hcs.exception.PatientNotFoundException;
import com.sprint.hcs.exception.TestResultNotFoundException;
import com.sprint.hcs.service.IAppointmentService;


@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    @Autowired
    IAppointmentService appserv;


    @PostMapping(value = "/addappointment")
    public Appointment addAppointment(@RequestBody Appointment appointment,
                                      @RequestParam(required = true) String patientID, @RequestParam(required = true) String diagnosticCenterID,
                                      @RequestParam(required = true) List<Integer> testIds) throws Exception {
        return appserv.addAppointment(appointment, patientID, diagnosticCenterID, testIds);
    }


    @DeleteMapping("/removeappointment")
    public Appointment removeAppointment(@RequestBody Appointment appointment) throws Exception {
        return appserv.removeAppointment(appointment);
    }


    @GetMapping("/viewappointments/{patientId}")
    public List<Appointment> viewAppointments(@PathVariable int patientId) throws Exception {
        return appserv.viewAppointments(patientId);
    }


    @GetMapping("/viewappointment/{appointmentId}")
    public Appointment viewAppointment(@PathVariable int appointmentId) throws Exception {
        return appserv.viewAppointment(appointmentId);
    }


    @PutMapping("/updateappointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment,
                                         @RequestParam(required = false) List<Integer> testResultId,
                                         @RequestParam(required = false) String patientID, @RequestParam(required = false) String diagnosticCenterID,
                                         @RequestParam(required = false) List<Integer> testIds) throws Exception {
        return appserv.updateAppointment(appointment, testResultId, patientID, diagnosticCenterID, testIds);
    }


    @GetMapping("/getappointmentlist")
    public List<Appointment> getApppointmentList(@RequestParam String diagnosticCenterid, @RequestParam String testName,
                                                 @RequestParam String appointmentStatus) throws Exception {
        return appserv.getApppointmentList(Integer.parseInt(diagnosticCenterid), testName, appointmentStatus);
    }

    @PutMapping("/verify")
    public Appointment verify(@RequestBody Appointment appointment) throws AppointmentNotFoundException {

        return appserv.verify(appointment.getAppointmentid(), true);
    }

    @PutMapping("/reject")
    public Appointment reject(@RequestBody Appointment appointment) throws AppointmentNotFoundException {
        return appserv.verify(appointment.getAppointmentid() , false);
    }

    @GetMapping("/getVerifiable")
    public List<Appointment> verifiable() {
        return appserv.verifiable();

    }

    @GetMapping("/getWithNoTestResults")
    public List<Appointment> noTestResults(){
        return appserv.noTestResults();
    }

    @GetMapping("/getPatient/{AppointmentId}")
    public Patient getPatient(@PathVariable int appID) throws PatientNotFoundException {
        return appserv.getPatient(appID);
    }

    @PutMapping("/addTestRes/{AppointmentId}/{testResId}")
    public TestResult addTestRes(@PathVariable int AppointmentId,@PathVariable int testResId) throws AppointmentNotFoundException, TestResultNotFoundException {
        return appserv.setTestResult(AppointmentId,testResId);
    }

    @GetMapping("/getAll")
    public List<Appointment> getAll(){
        return appserv.getAll();
    }


}
