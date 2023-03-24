package ru.sber.pm.esswfinalproject.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class DisciplinDto {
    private Long id;

    @Pattern(regexp = "^[а-яА-ЯёЁ-]+$", message = "Поле должно содержать только русские буквы")
    @NotBlank
    private String title;
}
