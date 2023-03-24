package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.StudentDto;
import ru.sber.pm.esswfinalproject.entities.Student;

@RequiredArgsConstructor
@Component
public class StudentDtoConverter {
    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setId(student.getId());
        return studentDto;
    }
}
