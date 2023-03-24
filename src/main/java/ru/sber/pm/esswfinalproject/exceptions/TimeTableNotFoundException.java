package ru.sber.pm.esswfinalproject.exceptions;

public class TimeTableNotFoundException extends RuntimeException {
    public TimeTableNotFoundException(String message) {
        super(message);
    }
}