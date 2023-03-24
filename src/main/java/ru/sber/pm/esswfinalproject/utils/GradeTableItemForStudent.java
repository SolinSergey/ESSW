package ru.sber.pm.esswfinalproject.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeTableItemForStudent {
    private Date date;
    private String disciplin;
    private Integer grade;
    private String description;
    private String teacherFirstName;
    private String teacherLastName;
}
