package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.MyClassDtoConverter;
import ru.sber.pm.esswfinalproject.dto.MyClassDto;
import ru.sber.pm.esswfinalproject.entities.ClassGroup;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Student;
import ru.sber.pm.esswfinalproject.repositories.ClassGroupRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;
    private final MyClassDtoConverter myClassDtoConverter;

    Sort sort = Sort.by("student.lastName").ascending();

    public List<ClassGroup> listAll() {
        return classGroupRepository.findAll(sort);
    }

    public void create(ClassGroup classGroup) {
        classGroupRepository.save(classGroup);
    }

    public List<MyClassDto> findByClassTitlesId(Long id) {
        List<ClassGroup> classGroup = classGroupRepository.findByClassTitles_Id(id, sort);
        List<MyClassDto> myClassDtoList = new ArrayList<>();
        for (ClassGroup c : classGroup) {
            myClassDtoList.add(myClassDtoConverter.entityToDto(c));
        }
        return myClassDtoList;
    }

    public List<MyClassDto> findClassGroupByClassTitles(ClassTitle classTitle) {
        List<ClassGroup> classGroup = classGroupRepository.findClassGroupByClassTitles(classTitle, sort);
        List<Student> studentList = new ArrayList<>();
        for (ClassGroup cg : classGroup) {
            studentList.add(cg.getStudent());
        }
        List<MyClassDto> myClassDtoList = new ArrayList<>();
        for (Student s : studentList) {
            myClassDtoList.add(myClassDtoConverter.entityToDto(s, classTitle.getTitle()));
        }
        return myClassDtoList;
    }

    public ClassGroup findClassGroupByStudent(Student student) {
        return classGroupRepository.findClassGroupByStudent(student);
    }
}
