package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CompanyException {
    private static final String DEPARTMENT_NOT_FOUND_MSG_KEY = "DepartmentNotExisted";
    private static final String DEPARTMENT_NOT_FOUND_MSG = "Department Not Found";

    private static final String EMPLOYEE_NOT_FOUND_MSG_KEY = "EmployeeNotExisted";
    private static final String EMPLOYEE_NOT_FOUND_MSG = "Employee not found";

    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseException DepartmentNotFound() {
        return notFound(DEPARTMENT_NOT_FOUND_MSG_KEY, DEPARTMENT_NOT_FOUND_MSG);
    }

    public static ResponseException EmployeeNotFound(){
        return notFound(EMPLOYEE_NOT_FOUND_MSG_KEY, EMPLOYEE_NOT_FOUND_MSG);
    }
}
