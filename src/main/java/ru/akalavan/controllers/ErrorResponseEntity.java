package ru.akalavan.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseEntity {

    private String massage;
    private String error;
    private int status;

    public ErrorResponseEntity(String massage, String error, int status) {
        this.massage = massage;
        this.error = error;
        this.status = status;
    }
}
