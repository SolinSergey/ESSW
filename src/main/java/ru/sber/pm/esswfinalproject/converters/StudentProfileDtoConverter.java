package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.StudentProfileDto;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Student;
import ru.sber.pm.esswfinalproject.entities.Teacher;

@RequiredArgsConstructor
@Component
public class StudentProfileDtoConverter {
    public StudentProfileDto entityToDto(Student student, ClassTitle classTitle, Teacher teacher) {
        StudentProfileDto studentProfileDto = new StudentProfileDto();
        studentProfileDto.setFirstName(student.getFirstName());
        studentProfileDto.setLastName(student.getLastName());
        studentProfileDto.setTeacherFirstName(teacher.getFirstName());
        studentProfileDto.setTeacherSecondName(teacher.getLastName());
        studentProfileDto.setClassTitle(classTitle.getTitle());
        return studentProfileDto;
    }
}
