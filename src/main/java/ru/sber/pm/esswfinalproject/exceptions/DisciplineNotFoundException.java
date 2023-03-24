package ru.sber.pm.esswfinalproject.exceptions;

public class DisciplineNotFoundException extends RuntimeException {
    public DisciplineNotFoundException(String message) {
        super(message);
    }
}