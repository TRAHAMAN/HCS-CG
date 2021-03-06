package com.sprint.hcs.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.hcs.dao.IAppointmentRepository;
import com.sprint.hcs.dao.IDiagnosticCenterRepositoryInt;
import com.sprint.hcs.dao.IDiagnosticTestRepository;
import com.sprint.hcs.dao.IPatientRepository;
import com.sprint.hcs.dao.ITestResultRepository;
import com.sprint.hcs.dao.ManualQueries.QueryClassPersisitContext;
import com.sprint.hcs.entities.Appointment;
import com.sprint.hcs.entities.AppointmentStatus;
import com.sprint.hcs.entities.DiagnosticCenter;
import com.sprint.hcs.entities.DiagnosticTest;
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
public class IAppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository iar;

    @Autowired
    IPatientRepository patRepo;

    @Autowired
    IDiagnosticCenterRepositoryInt centerRepo;

    @Autowired
    IDiagnosticTestRepository testRepo;

    @Autowired
    ITestResultRepository testResRepo;

    @Autowired
    QueryClassPersisitContext qcp;



    @Override
    public Appointment addAppointment(Appointment appointment,String patientid,String diagnosticCenterID,List<Integer> testsId) throws DataAlreadyExists, DataNotFoundInDataBase {

        if(iar.existsById(appointment.getAppointmentid()))throw new DataAlreadyExists("Appointment Already Exists Use Update To Change");

        DiagnosticCenter preDC = new DiagnosticCenter();
        Set<DiagnosticTest> preDTs = new HashSet<>();
        Patient prePatient = new Patient();
        try {
            if(patientid != null) {
                prePatient= patRepo.findById(Integer.parseInt(patientid))
                        .orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientid));
                appointment.setPatient(prePatient);
            }
            if(diagnosticCenterID != null) {
                preDC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
                        .orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
                appointment.setDiagnosticCenter(preDC);
            }
            if(testsId!=null) {
                for(int id : testsId) {
                    DiagnosticTest pretest = testRepo.findById(id)
                            .orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
                    preDTs.add(pretest);
                    pretest.setDiagnosticCenter(preDC);
                    testRepo.saveAndFlush(pretest);
                }
            }
        }
        catch(NumberFormatException e) {
            throw new DataNotFoundInDataBase("Please Check The ID's");
        }
        appointment.setDiagnosticTests(preDTs);

        preDC.getTests().addAll(preDTs);

        iar.saveAndFlush(appointment);

        return appointment;
    }



    @Override
    public Appointment removeAppointment(Appointment appointment) throws AppointmentNotFoundException{
        if(!iar.existsById(appointment.getAppointmentid())) throw new AppointmentNotFoundException("No Appointment found to remove");
        Appointment app = iar.findById(appointment.getAppointmentid()).get();
        iar.delete(app);
        return app;
    }




    @Override
    public List<Appointment> viewAppointments(int patientId) throws AppointmentNotFoundException, PatientNotFoundException {
        List<Appointment> apps =iar.findBypatient(patRepo.findById(patientId)
                .orElseThrow(()->new PatientNotFoundException("No Such Patient")));
        if(apps.size()==0)throw new AppointmentNotFoundException("No Appointments For You Yet");
        return apps;
    }



    @Override
    public Appointment viewAppointment(int appointmentId) throws AppointmentNotFoundException{
        if(!iar.existsById(appointmentId)) throw new AppointmentNotFoundException("No appointments Found with ID : "+appointmentId );
        return iar.findById(appointmentId).get();
    }



    @Override
    public Appointment updateAppointment(Appointment appointment,
                                         List<Integer> testResultId,
                                         String patientID ,
                                         String diagnosticCenterID,
                                         List<Integer> testIds) throws
            AppointmentNotFoundException,
            PatientNotFoundException,
            DiagnosticCenterNotFoundException, TestResultNotFoundException, DataNotFoundInDataBase {

        if(!iar.existsById(appointment.getAppointmentid())) {
            throw new AppointmentNotFoundException("Appointment Does Not Exist To Update");
        }

        if(testResultId!= null) {
            Set<TestResult> tr= appointment.getTestResult();
            for(int i : testResultId) {
                if(testResRepo.existsById(i))tr.add(testResRepo.findById(i).get());
                else throw new TestResultNotFoundException("Test Result Does Not Exist with id : "+i);
            }

        }
        try {
            if(patientID != null) {
                Patient prePatient = new Patient();
                prePatient= patRepo.findById(Integer.parseInt(patientID))
                        .orElseThrow(()-> new DataNotFoundInDataBase("Patient Not Found With ID : "+patientID));
                appointment.setPatient(prePatient);
            }
            if(diagnosticCenterID != null) {
                DiagnosticCenter preDC = new DiagnosticCenter();
                preDC = centerRepo.findById(Integer.parseInt(diagnosticCenterID))
                        .orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Center Not Found With ID : "+diagnosticCenterID));
                appointment.setDiagnosticCenter(preDC);
            }
            if(testIds!=null) {
                for(int id : testIds) {
                    DiagnosticTest pretest = testRepo.findById(id)
                            .orElseThrow(()-> new DataNotFoundInDataBase("Diagnostic Test Not Found With ID : "+id));
                    appointment.getDiagnosticTests().add(pretest);
                }
            }
        }
        catch(NumberFormatException e) {
            throw new DataNotFoundInDataBase("Please Check The ID's");
        }


        iar.saveAndFlush(appointment);
        return appointment;
    }



    @Override
    public List<Appointment> getApppointmentList(int centreId, String test, String status)
            throws InvalidAppointmentStatusException, AppointmentNotFoundException {
        AppointmentStatus stat;
        try {
            stat = AppointmentStatus.valueOf(status);
        }
        catch(Exception e) {
            throw new InvalidAppointmentStatusException("Invaild AppointMent Status"+status);
        }
        List<Appointment> apps = qcp.getAppointmentList(centreId, test,stat);
        if(apps.size() ==0) throw new AppointmentNotFoundException("No Such Appointment Exists");
        return apps;
    }



    public List<Appointment> get() {
        return iar.findAll();
    }


    @Override
    public Appointment verify(int appointmentID , boolean approved) throws AppointmentNotFoundException {
        Appointment app = iar.findById(appointmentID)
                .orElseThrow(()->new AppointmentNotFoundException("No Appointment with id "+appointmentID));
        if(approved)app.setApprovalStatus(AppointmentStatus.approved);
        else app.setApprovalStatus(AppointmentStatus.cancelled);
        return iar.saveAndFlush(app);
    }
    @Override
    public List<Appointment> verifiable(){
        List<Appointment> vapps =  iar.findAllByapprovalStatus(AppointmentStatus.statusnotapproved);
        return vapps;
    }


    @Override
    public List<Appointment> noTestResults() {
        return iar.findAllBytestResult(null);
    }


    @Override
    public Patient getPatient(int appID) throws PatientNotFoundException {
        return iar.findById(appID).orElseThrow(()->new PatientNotFoundException("No Appointment With Id "+appID)).getPatient();
    }


    @Override
    public TestResult setTestResult(int appointmentId, int testResId) throws AppointmentNotFoundException,TestResultNotFoundException {
        Appointment app = iar.findById(appointmentId)
                .orElseThrow(()->new AppointmentNotFoundException("No Appointment With Id "+appointmentId));
        TestResult tr = testResRepo.findById(testResId)
                .orElseThrow(() -> new TestResultNotFoundException("No TestResult With Id "+testResId));
        app.getTestResult().add(tr);
        tr.setAppointment(app);
        iar.saveAndFlush(app);
        testResRepo.saveAndFlush(tr);
        return tr;
    }


    @Override
    public List<Appointment> getAll() {
        return iar.findAll();
    }




}
