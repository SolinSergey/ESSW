package ru.sber.pm.esswfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sber.pm.esswfinalproject.entities.LecturesHall;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class LecturesHallDto extends LecturesHall {
    private int number;
    private String title;
}
