package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.MyClassDto;
import ru.sber.pm.esswfinalproject.entities.ClassGroup;
import ru.sber.pm.esswfinalproject.entities.Student;

@RequiredArgsConstructor
@Component
public class MyClassDtoConverter {

    public MyClassDto entityToDto(Student student, String classTitle) {
        MyClassDto myClassDto = new MyClassDto();
        myClassDto.setFirstName(student.getFirstName());
        myClassDto.setSecondName(student.getLastName());
        myClassDto.setTitle(classTitle);
        return myClassDto;
    }

    public MyClassDto entityToDto(ClassGroup classGroup) {
        MyClassDto myClassDto = new MyClassDto();
        myClassDto.setTitle(classGroup.getClassTitles().getTitle());
        myClassDto.setFirstName(classGroup.getStudent().getFirstName());
        myClassDto.setSecondName(classGroup.getStudent().getLastName());
        myClassDto.setStudentId(classGroup.getStudent().getId());
        return myClassDto;
    }


}
