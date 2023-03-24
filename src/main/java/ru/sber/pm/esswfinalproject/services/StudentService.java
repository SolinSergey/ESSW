package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.StudentDtoConverter;
import ru.sber.pm.esswfinalproject.dto.MyClassDto;
import ru.sber.pm.esswfinalproject.dto.StudentDto;
import ru.sber.pm.esswfinalproject.dto.StudentProfileDto;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.repositories.StudentRepository;

import java.util.*;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public final UserService userService;
    public final StudentDtoConverter studentDtoConverter;
    private final ClassGroupService classGroupService;

    Sort sort = Sort.by("firstName", "lastName").ascending();

    public List<StudentDto> listAll() {
        List<Student> studentList = studentRepository.findAll(sort);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student s : studentList) {
            studentDtoList.add(studentDtoConverter.entityToDto(s));
        }
        return studentDtoList;
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    public Student findByUserUsername(String userName) {
        Student student = studentRepository.findByUserUsername(userName);
        return student;
    }

    public Student findByUser(User user) {
        Student student = studentRepository.findByUser(user);
        return student;
    }

    public StudentProfileDto prepareStudentProfile(Student student) {
        List<ClassGroup> classGroups = classGroupService.listAll();
        StudentProfileDto studentProfileDto = new StudentProfileDto();
        for (ClassGroup c : classGroups) {
            if (student.getUser().getUsername().equals(c.getStudent().getUser().getUsername())) {
                studentProfileDto.setClassTitle(c.getClassTitles().getTitle());
                studentProfileDto.setTeacherSecondName(c.getClassTitles().getTeacher().getLastName());
                studentProfileDto.setTeacherFirstName(c.getClassTitles().getTeacher().getFirstName());
                studentProfileDto.setFirstName(student.getFirstName());
                studentProfileDto.setLastName(student.getLastName());
                break;
            }
        }
        return studentProfileDto;
    }

    public List<MyClassDto> findAllByStudent(Student student) {
        ClassGroup classGroup = classGroupService.findClassGroupByStudent(student);
        return classGroupService.findClassGroupByClassTitles(classGroup.getClassTitles());
    }
}

