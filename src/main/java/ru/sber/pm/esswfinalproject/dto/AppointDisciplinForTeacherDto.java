package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class AppointDisciplinForTeacherDto {
    private long teacherId;
    private long disciplinId;

}
