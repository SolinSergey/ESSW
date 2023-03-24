package ru.sber.pm.esswfinalproject.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassCjFilter {
    private String classTitleId;
    private Long studentId;
    private String disciplineId;
    private String teacherId;
    private String className;
    private String classId;
    private String startDate;
    private String endDate;
}

