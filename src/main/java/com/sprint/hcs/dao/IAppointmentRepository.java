package com.sprint.hcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint.hcs.entities.Appointment;
import com.sprint.hcs.entities.AppointmentStatus;
import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.TestResult;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer>{

    List<Appointment> findBypatient(Patient patient);

    List<Appointment> findAllByapprovalStatus(AppointmentStatus statusnotapproved);

    List<Appointment> findAllBytestResult(TestResult testResult);


}

