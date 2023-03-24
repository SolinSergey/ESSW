package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.TeacherDto;
import ru.sber.pm.esswfinalproject.entities.Teacher;

@RequiredArgsConstructor
@Component
public class TeacherDtoConverter {

    public TeacherDto entityToDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setId(teacher.getId());
        return teacherDto;
    }
}
