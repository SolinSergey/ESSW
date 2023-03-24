package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.TimeTableDtoConverter;
import ru.sber.pm.esswfinalproject.dto.TimeTableDto;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.repositories.TeacherRepository;
import ru.sber.pm.esswfinalproject.repositories.TimetableRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimeTableService {
    private final TimetableRepository timetableRepository;
    private final ClassGroupService classGroupService;
    private final TimeTableDtoConverter timeTableDtoConverter;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassTitleService classTitleService;

    Sort sort = Sort.by("numberOfDay", "classTitle", "numberLesson").ascending();//.and(Sort.by("classTitle").ascending().and(Sort.by("numberLesson").ascending()));
    private final TeacherRepository teacherRepository;

    public List<TimeTableDto> findTimetablebyStudentUsername(String username, int numberDay) {
        Student student = studentService.findByUserUsername(username);
        List<TimeTableDto> timeTableDtos = findTimetableByStudentId(student.getId(), numberDay);
        return timeTableDtos;
    }

    public List<TimeTableDto> findTimeTableByTeacherUserName(String username, int numberDay) {
        Teacher teacher = teacherService.findTeacherByUsername(username);
        List<TimeTableDto> timeTableDtos = findTimetableByTeacherId(teacher.getId(), numberDay);
        return timeTableDtos;
    }

    public List<TimeTableDto> findTimeTableMyClassByTeacherUserName(String username, int numberDay) {
        Teacher teacher = teacherService.findTeacherByUsername(username);
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        if (classTitle != null) {
            List<TimeTableDto> timeTableDtos = findTimetableByClassTitleId(classTitle.getId(), numberDay);
            return timeTableDtos;
        }
        return null;
    }

    public List<TimeTableDto> findTimetableByClassTitleId(Long classTitle_id, int numberDay) {
        List<Timetable> timeTable;
        if (numberDay <= 0 || numberDay > 7) {
            timeTable = timetableRepository.findTimetableByClassTitleId(classTitle_id, sort);
        } else {
            timeTable = (timetableRepository.findTimetableByClassTitleIdAndAndNumberOfDay(classTitle_id, numberDay, sort));
        }
        if (classTitle_id == 0 && numberDay == 0) {
            timeTable = timetableRepository.findAll(sort);
        }
        if (classTitle_id == 0 && numberDay != 0) {
            timeTable = (timetableRepository.findAllByNumberOfDay(numberDay, sort));
        }
        if (classTitle_id != 0 && numberDay == 0) {
            timeTable = timetableRepository.findTimetableByClassTitleId(classTitle_id, sort);
        }
        List<TimeTableDto> timeTableDtos = new ArrayList<>();
        for (Timetable t : timeTable) {
            timeTableDtos.add(timeTableDtoConverter.entityToDto(t));
        }
        return timeTableDtos;
    }


    public void delete(Long id) {
        timetableRepository.deleteById(id);
    }

    public List<TimeTableDto> findTimetableByStudentId(Long student_id, int numberDay) {
        List<ClassGroup> classGroups = classGroupService.listAll();
        String classTitle = null;
        for (ClassGroup c : classGroups) {
            Student student = c.getStudent();
            boolean flag = false;
            if (student.getId() == student_id) {
                classTitle = c.getClassTitles().getTitle();
                flag = true;
                break;
            }
            if (flag) break;
        }
        List<Timetable> timeTable;
        if (numberDay <= 0 || numberDay > 7) {
            timeTable = timetableRepository.findTimetableByClassTitle(classTitle, sort);
        } else {
            timeTable = timetableRepository.findTimetableByClassTitleAndNumberOfDay(classTitle, numberDay, sort);
        }
        List<TimeTableDto> timeTableDtos = new ArrayList<>();
        for (Timetable t : timeTable) {
            timeTableDtos.add(timeTableDtoConverter.entityToDto(t));
        }
        return timeTableDtos;
    }

    public List<TimeTableDto> findTimetableByTeacherId(Long teacher_id, int numberDay) {


        List<Timetable> timeTable;
        if (numberDay <= 0 || numberDay > 7) {
            timeTable = (timetableRepository.findTimetableByTeacherId(teacher_id, sort));
        } else {
            timeTable = (timetableRepository.findTimetableByTeacherIdAndNumberOfDay(teacher_id, numberDay, sort));
        }
        List<TimeTableDto> timeTableDtos = new ArrayList<>();
        for (Timetable t : timeTable) {
            timeTableDtos.add(timeTableDtoConverter.entityToDto(t));
        }
        return timeTableDtos;

    }

    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    public Timetable findTimetableByNumberOfDayAndNumberLessonAndClassTitle(int numberDay, NumberLesson numberLesson, ClassTitle classTitle) {
        return timetableRepository.findTimetableByNumberOfDayAndNumberLessonAndClassTitle(numberDay, numberLesson, classTitle, sort);
    }

    public Timetable findTimetableByNumberOfDayAndNumberLessonAndAndDisciplin(int numberDay, NumberLesson numberLesson, Disciplin disciplin) {
        return timetableRepository.findTimetableByNumberOfDayAndNumberLessonAndAndDisciplin(numberDay, numberLesson, disciplin, sort);
    }

    public Timetable findTimetableByNumberOfDayAndNumberLessonAndLecturesHall(int numberDay, NumberLesson numberLesson, LecturesHall lecturesHall) {
        return timetableRepository.findTimetableByNumberOfDayAndNumberLessonAndLecturesHall(numberDay, numberLesson, lecturesHall, sort);
    }

    public List<Timetable> findTimetableByTeacher(Teacher teacher) {
        return timetableRepository.findTimetableByTeacher(teacher, this.sort);
    }
}

