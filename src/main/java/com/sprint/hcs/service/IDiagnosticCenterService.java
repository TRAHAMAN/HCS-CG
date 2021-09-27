package com.sprint.hcs.service;

import java.util.List;


import com.sprint.hcs.entities.Appointment;
import com.sprint.hcs.entities.DiagnosticCenter;
import com.sprint.hcs.entities.DiagnosticTest;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.DiagnosticCenterNotFoundException;


public interface IDiagnosticCenterService {

    public List<DiagnosticCenter> getAllDiagnosticCenters();
    public DiagnosticCenter addDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws Exception;
    public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId) throws DataNotFoundInDataBase;
    public DiagnosticCenter updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DataNotFoundInDataBase;
    List<DiagnosticTest> viewTestDetails(int diagnosticCenterId);
    DiagnosticTest addTest(int diagnosticcenterId, int testid) throws DataNotFoundInDataBase;
    DiagnosticCenter getDiagnosticCenter(String centername) throws DataNotFoundInDataBase;
    DiagnosticCenter removeDiagnosticCenter(int id) throws DiagnosticCenterNotFoundException;
    List<Appointment> getListOfAppointments(String centerName);

}

