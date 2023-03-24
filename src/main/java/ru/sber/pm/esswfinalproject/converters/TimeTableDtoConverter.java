package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.TimeTableDto;
import ru.sber.pm.esswfinalproject.entities.Timetable;
import ru.sber.pm.esswfinalproject.utils.DayConverter;

@RequiredArgsConstructor
@Component
public class TimeTableDtoConverter {
    private final DayConverter dayConverter;

    public TimeTableDto entityToDto(Timetable timetable) {
        TimeTableDto timeTableDto = new TimeTableDto();
        timeTableDto.setLecturesHallTitle(timetable.getLecturesHall().getTitle());
        timeTableDto.setLecturesHallNumber(timetable.getLecturesHall().getNumber());
        timeTableDto.setNumberOfDay(timetable.getNumberOfDay());
        timeTableDto.setDayTitle(dayConverter.getDayName(timetable.getNumberOfDay()));
        timeTableDto.setDisciplinTitle(timetable.getDisciplin().getTitle());
        timeTableDto.setTeacherFirstName(timetable.getTeacher().getFirstName());
        timeTableDto.setTeacherLastName(timetable.getTeacher().getLastName());
        timeTableDto.setClassGroupTitle(timetable.getClassTitle().getTitle());
        timeTableDto.setNumberLesson(timetable.getNumberLesson().getNumberLesson());
        timeTableDto.setTimeStartLesson(timetable.getNumberLesson().getTimeStartLesson());
        timeTableDto.setTimeFinishLesson(timetable.getNumberLesson().getTimeFinishLesson());
        timeTableDto.setId(timetable.getId());
        return timeTableDto;
    }
}

