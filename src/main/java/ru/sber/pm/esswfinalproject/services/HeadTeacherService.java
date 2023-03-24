package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.exceptions.DisciplineNotFoundException;
import ru.sber.pm.esswfinalproject.exceptions.TeacherNotFoundException;
import ru.sber.pm.esswfinalproject.exceptions.UsernameNotFoundException;
import ru.sber.pm.esswfinalproject.repositories.HeadTeacherRepository;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HeadTeacherService {
    private final HeadTeacherRepository headTeacherRepository;
    private final UserService userService;
    private final TeacherService teacherService;
    private final RoleService roleService;
    private final ClassTitleService classTitleService;
    private final ProfileTeacherService profileTeacherService;
    private final DisciplinService disciplinService;
    private final LecturesHallService lecturesHallService;
    private final NumberLessonService numberLessonService;
    private final TimeTableService timeTableService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<HeadTeacher> listAll() {
        return headTeacherRepository.findAll();
    }

    public void createToTeacher(RegistrationDataDto registrationDataDto) {
        User user = new User();
        user.setUsername(registrationDataDto.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDataDto.getPassword()));
        Role role = roleService.findByTitle(registrationDataDto.getRole());
        user.setRole(role);
        userService.create(user);
        user = userService.findByUsername(registrationDataDto.getUserName()).orElseThrow(() -> new UsernameNotFoundException("Пользователь с username:" + registrationDataDto.getUserName() + " не найден"));
        Teacher newTeacher = new Teacher();
        newTeacher.setUser(user);
        newTeacher.setFirstName(registrationDataDto.getFirstName());
        newTeacher.setLastName(registrationDataDto.getLastName());
        teacherService.saveTeacher(newTeacher);
    }

    public String createToTimeTable(CreateTimeTableDto createTimeTableDto) {
        Timetable timetable;
        int numberDay = createTimeTableDto.getNumberOfDay();
        NumberLesson numberLesson = numberLessonService.findById(createTimeTableDto.getNumberLessonId()).orElseThrow(() -> new TeacherNotFoundException("Номер урока с id:" + createTimeTableDto.getNumberLessonId() + " не найден"));
        ClassTitle classTitle = classTitleService.findClassTitleById(createTimeTableDto.getClassTitleId());
        timetable = timeTableService.findTimetableByNumberOfDayAndNumberLessonAndClassTitle(numberDay, numberLesson, classTitle);
        if (timetable == null) {
            Disciplin disciplin = disciplinService.findById(createTimeTableDto.getDisciplinId()).orElseThrow(() -> new DisciplineNotFoundException("Дисциплина с id:" + createTimeTableDto.getDisciplinId() + " не найденa"));
            timetable = timeTableService.findTimetableByNumberOfDayAndNumberLessonAndAndDisciplin(numberDay, numberLesson, disciplin);
            if (timetable == null) {
                LecturesHall lecturesHall = lecturesHallService.findById(createTimeTableDto.getLecturesHallId()).orElseThrow(() -> new DisciplineNotFoundException("Кабинет с id:" + createTimeTableDto.getLecturesHallId() + " не найденa"));
                timetable = timeTableService.findTimetableByNumberOfDayAndNumberLessonAndLecturesHall(numberDay, numberLesson, lecturesHall);
                if (timetable == null) {
                    timetable = new Timetable();
                    ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByDisciplin(disciplin);
                    Teacher teacher = profileTeacher.getTeacher();
                    timetable.setNumberOfDay(createTimeTableDto.getNumberOfDay());
                    timetable.setDisciplin(disciplin);
                    timetable.setTeacher(teacher);
                    timetable.setClassTitle(classTitle);
                    timetable.setLecturesHall(lecturesHall);
                    timetable.setNumberLesson(numberLesson);
                    timeTableService.saveTimetable(timetable);
                } else {
                    return "busyLecturesHall";
                }
            } else {
                return "busyTeacher";
            }
        } else {
            return "busyLesson";
        }
        return "ok";
    }

    public ClassTitle appointTeacherToAdvisorForClassGroup(Long teacher_id, Long class_id) {
        Teacher teacher = teacherService.findById(teacher_id).orElseThrow(() -> new TeacherNotFoundException("Прреподаватель с id:" + teacher_id + " не найден"));
        ClassTitle classTitle = classTitleService.findClassTitleById(class_id);
        classTitle.setTeacher(teacher);
        classTitleService.saveTeacher(classTitle);
        return classTitle;
    }

    public void appointDisciplinToTeacher(Long disciplin_id, Long teacher_id) {
        Disciplin disciplin = disciplinService.findById(disciplin_id).orElseThrow(() -> new DisciplineNotFoundException("Дисциплина с id:" + disciplin_id + " не найденa"));
        Teacher teacher = teacherService.findById(teacher_id).orElseThrow(() -> new TeacherNotFoundException("Преподаватель с id:" + teacher_id + " не найден"));
        ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(teacher);
        if (profileTeacher == null) {
            profileTeacher = new ProfileTeacher();
            profileTeacher.setTeacher(teacher);
            profileTeacherService.saveProfileTeacher(profileTeacher);
        }
        ProfileTeacher profileTeacher1 = profileTeacherService.findProfileTeacherByDisciplin(disciplin);
        Teacher teacherOld = null;
        List<Timetable> timetableListOldTeacher = null;
        if (profileTeacher1 != null) {
            teacherOld = profileTeacher1.getTeacher();
            if (teacherOld != null) {
                timetableListOldTeacher = timeTableService.findTimetableByTeacher(teacherOld);
            }
            profileTeacher1.setDisciplin(null);
            profileTeacherService.saveProfileTeacher(profileTeacher1);
        }
        if (timetableListOldTeacher != null) {
            if (timetableListOldTeacher.size() != 0) {
                for (Timetable t : timetableListOldTeacher) {
                    t.setTeacher(teacher);
                }
            }
        }
        profileTeacher.setDisciplin(disciplin);
        profileTeacherService.saveProfileTeacher(profileTeacher);
    }

    public List<TeacherDto> listAllTeacher() {
        List<TeacherDto> teachers = teacherService.listAll();
        return teachers;
    }
}
