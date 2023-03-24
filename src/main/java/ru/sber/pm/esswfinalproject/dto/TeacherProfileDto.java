package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class TeacherProfileDto {
    private String firstName;
    private String lastName;
    private String classTitle;
    private String disciplinTitle;
}
