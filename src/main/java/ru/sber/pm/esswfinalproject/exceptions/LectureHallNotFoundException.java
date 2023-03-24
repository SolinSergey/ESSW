package ru.sber.pm.esswfinalproject.exceptions;

public class LectureHallNotFoundException extends RuntimeException {
    public LectureHallNotFoundException(String message) {
        super(message);
    }
}