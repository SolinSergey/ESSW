package ru.sber.pm.esswfinalproject.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherCJFilter {
    private String classTitleId;
    private String studentId;
    private String disciplineId;
    private String teacherId;
    private String startDate;
    private String endDate;
}
