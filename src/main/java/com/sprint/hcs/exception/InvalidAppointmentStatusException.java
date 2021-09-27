package com.sprint.hcs.exception;

public class InvalidAppointmentStatusException extends Exception {


    private static final long serialVersionUID = 1L;

    public InvalidAppointmentStatusException(String string) {
        super(string);
    }

}
