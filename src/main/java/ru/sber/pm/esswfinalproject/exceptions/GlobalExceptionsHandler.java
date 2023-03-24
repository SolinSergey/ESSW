package ru.sber.pm.esswfinalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(new AppError("USER_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> IllegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(new AppError("ILLEGAL_DATA_STATE", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleClassNotFoundException(LearningClassNotFoundException e) {
        return new ResponseEntity<>(new AppError("CLASS_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleClassJournalNotFoundException(ClassJournalNotFoundException e) {
        return new ResponseEntity<>(new AppError("CLASS_JOURNAL_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleDisciplineNotFoundException(DisciplineNotFoundException e) {
        return new ResponseEntity<>(new AppError("DISCIPLINE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleHeadTeacherNotFoundException(HeadTeacherNotFoundException e) {
        return new ResponseEntity<>(new AppError("HEAD_TEACHER_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleLectureHallNotFoundException(LectureHallNotFoundException e) {
        return new ResponseEntity<>(new AppError("LECTURE_HALL_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleProfileNotFoundException(ProfileNotFoundException e) {
        return new ResponseEntity<>(new AppError("PROFILE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleStudentNotFoundException(StudentNotFoundException e) {
        return new ResponseEntity<>(new AppError("STUDENT_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleTeacherNotFoundException(TeacherNotFoundException e) {
        return new ResponseEntity<>(new AppError("TEACHER_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleTimeTableNotFoundException(TimeTableNotFoundException e) {
        return new ResponseEntity<>(new AppError("TIME_TABLE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
