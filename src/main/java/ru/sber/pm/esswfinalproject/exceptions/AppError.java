package ru.sber.pm.esswfinalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private String code;
    private String message;
}
