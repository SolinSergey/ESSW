package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String disciplinTitle;
    private String classTitle;
}
