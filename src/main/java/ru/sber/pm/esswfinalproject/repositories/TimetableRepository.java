package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.*;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query("select tb from Timetable as tb where tb.classTitle.title=:title")
    List<Timetable> findTimetableByClassTitle(String title, Sort sort);

    @Query("select tb from Timetable as tb where tb.numberOfDay =:numberDay and tb.classTitle.title =:title")
    List<Timetable> findTimetableByClassTitleAndNumberOfDay(String title, int numberDay, Sort sort);

    List<Timetable> findTimetableByTeacherId(Long id, Sort sort);

    List<Timetable> findTimetableByTeacherIdAndNumberOfDay(Long id, int numberDay, Sort sort);

    List<Timetable> findTimetableByClassTitleId(Long id, Sort sort);

    List<Timetable> findTimetableByClassTitleIdAndAndNumberOfDay(Long id, int numberDay, Sort sort);

    List<Timetable> findAllByNumberOfDay(int numberDay, Sort sort);

    List<Timetable> findAll(Sort sort);

    void deleteById(Long id);

    Timetable findTimetableByNumberOfDayAndNumberLessonAndClassTitle(int numberDay, NumberLesson numberLesson, ClassTitle classTitle, Sort sort);

    Timetable findTimetableByNumberOfDayAndNumberLessonAndAndDisciplin(int numberDay, NumberLesson numberLesson, Disciplin discipline, Sort sort);

    Timetable findTimetableByNumberOfDayAndNumberLessonAndLecturesHall(int numberDay, NumberLesson numberLesson, LecturesHall lecturesHall, Sort sort);

    List<Timetable> findTimetableByTeacher(Teacher teacher, Sort sort);
}



