package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class CreateTimeTableDto {

    private int numberOfDay;
    private long disciplinId;

    private long classTitleId;
    private long lecturesHallId;
    private long numberLessonId;
}
