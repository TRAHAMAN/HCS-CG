package com.sprint.hcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.hcs.entities.Appointment;
import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;
import com.sprint.hcs.exception.AppointmentNotFoundException;
import com.sprint.hcs.exception.DataAlreadyExists;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.DiagnosticCenterNotFoundException;
import com.sprint.hcs.exception.InvalidAppointmentStatusException;
import com.sprint.hcs.exception.PatientNotFoundException;
import com.sprint.hcs.exception.TestResultNotFoundException;


@Service
public interface IAppointmentService {

    Appointment addAppointment(Appointment appointment, String patientID, String diagnosticCenterID,List<Integer> testsId)	throws DataAlreadyExists, DataNotFoundInDataBase;



    Appointment removeAppointment(Appointment appointment) throws AppointmentNotFoundException;


    List<Appointment> viewAppointments(int patientId) throws AppointmentNotFoundException, PatientNotFoundException;


    Appointment viewAppointment(int appointmentId) throws AppointmentNotFoundException;


    Appointment updateAppointment(Appointment appointment, List<Integer> testResultId,String patientID ,
                                  String diagnosticCenterID, List<Integer> testIds) throws AppointmentNotFoundException, PatientNotFoundException, DiagnosticCenterNotFoundException, TestResultNotFoundException, DataNotFoundInDataBase ;


    List<Appointment> getApppointmentList(int centreId, String test, String status) throws InvalidAppointmentStatusException, AppointmentNotFoundException;

    Appointment verify( int appointmentID , boolean approved) throws AppointmentNotFoundException;


    List<Appointment> verifiable();


    List<Appointment> noTestResults();


    Patient getPatient(int appID) throws PatientNotFoundException;


    TestResult setTestResult(int appointmentId, int testResId) throws AppointmentNotFoundException, TestResultNotFoundException;


    List<Appointment> getAll();




}

