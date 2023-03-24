package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ClassJournalDtoWithString {
    private Date start;
    private Date end;
    private String startD;
    private String endD;

    @NotBlank
    private String dateGrade;
    private String disciplinTitle;
    private Long disciplinId;

    private int grade;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[а-яА-ЯёЁ\s]+$", message = "Поле может содержать русские буквы и пробел")
    private String description;

    private Long studentId;
    private String studentID;
    private String studentFirstName;
    private String studentLastName;
    private String teacherFirstName;
    private String teacherSecondName;
    private String classTitle;

    private String classId;
}
