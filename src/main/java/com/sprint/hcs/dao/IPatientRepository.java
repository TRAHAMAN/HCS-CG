package com.sprint.hcs.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.hcs.entities.Patient;
import com.sprint.hcs.entities.User;


public interface IPatientRepository extends JpaRepository<Patient, Integer>{

    List<Patient> findAllByname(String name);

    Patient findByuser(User user);
}

