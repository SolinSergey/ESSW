package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class TimeTableDto {
    private Long id;
    private int numberOfDay;
    private String dayTitle;

    private String disciplinTitle;
    private String teacherFirstName;
    private String teacherLastName;
    private String classGroupTitle;
    private int lecturesHallNumber;
    private String lecturesHallTitle;
    private int numberLesson;
    private String timeStartLesson;
    private String timeFinishLesson;
}
