package com.sprint.hcs.exception;

public class AppointmentNotFoundException extends Exception {


    private static final long serialVersionUID = 1L;

    public AppointmentNotFoundException(String msg) {
        super(msg);
    }

}
