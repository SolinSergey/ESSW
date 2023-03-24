package ru.sber.pm.esswfinalproject.converters;

import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.LecturesHallDto;
import ru.sber.pm.esswfinalproject.entities.LecturesHall;

@Component
public class LecturesHallDtoConverter {
    public LecturesHallDto entityToDto(LecturesHall lecturesHall) {
        LecturesHallDto lecturesHallDto = new LecturesHallDto();
        lecturesHallDto.setId(lecturesHall.getId());
        lecturesHallDto.setNumber(lecturesHall.getNumber());
        lecturesHallDto.setFloor(lecturesHall.getFloor());
        lecturesHallDto.setTitle(lecturesHall.getTitle());
        return lecturesHallDto;
    }
}
