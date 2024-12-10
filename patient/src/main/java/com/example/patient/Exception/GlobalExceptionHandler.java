package com.example.patient.Exception;

import com.example.patient.Bean.ErrorBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = PatientNotFoundException.class)
    public ResponseEntity<ErrorBean> noPatientFound(PatientNotFoundException e) {
        ErrorBean err = new ErrorBean();
        err.setErrorCode(404);
        err.setErrorMsg("Invalid ID");

        return ResponseEntity.ok(err);
    }
}
