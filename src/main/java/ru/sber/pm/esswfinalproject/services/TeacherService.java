package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.*;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.exceptions.TeacherNotFoundException;
import ru.sber.pm.esswfinalproject.repositories.ClassTitleRepository;
import ru.sber.pm.esswfinalproject.repositories.TeacherRepository;
import ru.sber.pm.esswfinalproject.utils.UserUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentService studentService;
    private final RoleService roleService;
    private final UserService userService;
    private final DisciplinService disciplinService;
    private final ClassJournalService classJournalService;
    private final ClassTitleService classTitleService;
    private final ClassGroupService classGroupService;
    private final TeacherDtoConverter teacherDtoConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserUtils userUtils;
    private final ProfileTeacherService profileTeacherService;
    private final ClassTitleRepository classTitleRepository;


    Sort sort = Sort.by("lastName", "firstName").ascending();

    public List<TeacherDto> listAll() {
        List<TeacherDto> teacherDtos = new ArrayList<>();
        List<Teacher> teacher = teacherRepository.findAll(sort);
        for (Teacher t : teacher) {
            ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(t);
            ClassTitle classTitle = classTitleService.findClassTitleByTeacher(t);
            TeacherDto teacherDto = teacherDtoConverter.entityToDto(t);
            if (profileTeacher == null || profileTeacher.getDisciplin() == null) {
                teacherDto.setDisciplinTitle("Не назначена");
            } else {
                teacherDto.setDisciplinTitle(profileTeacher.getDisciplin().getTitle());
            }
            if (classTitle == null) {
                teacherDto.setClassTitle("Не назначен");
            } else {
                teacherDto.setClassTitle(classTitle.getTitle());
            }
            teacherDtos.add(teacherDto);
        }
        return teacherDtos;
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public String createToStudent(RegistrationDataDto registrationDataDto) {
        Teacher teacher = teacherRepository.findTeacherByUser_Username(userUtils.getUsernameForLoginUser());
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        if (classTitle != null) {
            User user = new User();
            user.setUsername(registrationDataDto.getUserName());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDataDto.getPassword()));
            Role role = roleService.findByTitle(registrationDataDto.getRole());
            user.setRole(role);
            userService.create(user);
            user = userService.findByUsername(registrationDataDto.getUserName()).orElseThrow(() -> new TeacherNotFoundException("Пользователь с username:" + registrationDataDto.getUserName() + " не найден"));
            Student student = new Student();
            student.setUser(user);
            student.setFirstName(registrationDataDto.getFirstName());
            student.setLastName(registrationDataDto.getLastName());
            student.setId(0L);
            studentService.saveStudent(student);
            student = studentService.findByUser(user);
            ClassGroup classGroup = new ClassGroup();
            classGroup.setClassTitles(classTitleService.findClassTitleByTeacher(teacher));
            classGroup.setStudent(student);
            classGroupService.create(classGroup);
        } else {
            return "teacherWithoutClass";
        }
        return "ok";
    }

    public ClassJournal addGradeToStudent(ClassJournalDtoWithString сlassJournalDtoWithString) throws
            ParseException {
        if (сlassJournalDtoWithString.getGrade() >= 1 && сlassJournalDtoWithString.getGrade() <= 5) {
            ClassJournal classJournal = new ClassJournal();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date date1 = formatter.parse(сlassJournalDtoWithString.getDateGrade() + " 00:00:00.0");
            classJournal.setDate(date1);
            classJournal.setGrade(сlassJournalDtoWithString.getGrade());
            classJournal.setDescription(сlassJournalDtoWithString.getDescription());
            String username = userUtils.getUsernameForLoginUser();
            Teacher teacher = teacherRepository.findTeacherByUser_Username(username);
            Student student = studentService.findById(сlassJournalDtoWithString.getStudentId()).get();
            classJournal.setStudent(student);
            ClassTitle classTitle = classTitleService.findClassTitleById(Long.valueOf(сlassJournalDtoWithString.getClassId()));
            classJournal.setClassTitle(classTitle);
            Disciplin disciplin = disciplinService.findDisciplinByTitle(сlassJournalDtoWithString.getDisciplinTitle());
            classJournal.setDisciplin(disciplin);
            classJournal.setTeacher(teacher);
            classJournalService.create(classJournal);
            return classJournal;
        } else throw new RuntimeException();
    }

    public Teacher saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public Teacher findTeacherByUsername(String username) {
        return teacherRepository.findTeacherByUser_Username(username);
    }
}
