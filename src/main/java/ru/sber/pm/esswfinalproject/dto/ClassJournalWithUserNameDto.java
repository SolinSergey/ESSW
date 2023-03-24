package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ClassJournalWithUserNameDto {
    private Date start;
    private Date end;
    private Long disciplinId;
    private int grade;
    private String description;
    private Long studentId;
    private long teacherId;
    private String classTitleTitle;
}
