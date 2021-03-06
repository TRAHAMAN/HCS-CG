package com.sprint.hcs.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;








@Entity

public class Appointment{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentid;

    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus approvalStatus;


    @ManyToMany
    private Set<DiagnosticTest> diagnosticTests;


    @ManyToOne
    private Patient patient;


    @OneToOne
    private DiagnosticCenter diagnosticCenter;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.REMOVE)
    private Set<TestResult> testResult;



    public Appointment() {

        super();
    }




    public Appointment(LocalDate appointmentDate, AppointmentStatus approvalStatus, Set<DiagnosticTest> diagnosticTests,
                       Patient patient, DiagnosticCenter diagnosticCenter, Set<TestResult> testResult) {

        super();
        this.appointmentDate = appointmentDate;
        this.approvalStatus = approvalStatus;
        this.diagnosticTests = diagnosticTests;
        this.patient = patient;
        this.diagnosticCenter = diagnosticCenter;
        this.testResult = testResult;
    }





    public int getAppointmentid() {

        return appointmentid;
    }


    public void setAppointmentid(int appointmentid) {

        this.appointmentid = appointmentid;
    }


    public LocalDate getAppointmentDate() {

        return appointmentDate;
    }


    public void setAppointmentDate(LocalDate appointmentDate) {

        this.appointmentDate = appointmentDate;
    }


    public AppointmentStatus getApprovalStatus() {

        return approvalStatus;
    }


    public void setApprovalStatus(AppointmentStatus approvalStatus) {

        this.approvalStatus = approvalStatus;
    }


    public Set<DiagnosticTest> getDiagnosticTests() {

        return diagnosticTests;
    }


    public void setDiagnosticTests(Set<DiagnosticTest> diagnosticTests) {

        this.diagnosticTests = diagnosticTests;
    }


    public Patient getPatient() {

        return patient;
    }


    public void setPatient(Patient patient) {

        this.patient = patient;
    }


    public DiagnosticCenter getDiagnosticCenter() {

        return diagnosticCenter;
    }


    public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {

        this.diagnosticCenter = diagnosticCenter;
    }


    public Set<TestResult> getTestResult() {

        return testResult;
    }


    public void setTestResult(Set<TestResult> testResult) {

        this.testResult = testResult;
    }


}

