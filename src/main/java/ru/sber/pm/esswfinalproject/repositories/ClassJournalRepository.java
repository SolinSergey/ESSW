package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.*;

import java.util.Date;
import java.util.List;

@Repository
public interface ClassJournalRepository extends JpaRepository<ClassJournal, Long> {

    List<ClassJournal> findClassJournalByClassTitleAndStudentIdAndDateBetween(ClassTitle classTitle, Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findAllByStudentIdAndDateBetween(Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleAndDateBetween(ClassTitle ClassTitle, Date start, Date end, Sort sort);

    List<ClassJournal> findClassJournalByStudent_IdAndDateBetween(Long id, Date start, Date end, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndDateBetween(Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByTeacherIdAndDateBetween(Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByStudentIdAndDateBetween(Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByStudentIdAndTeacherIdAndDateBetween(Long studentId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByDisciplinIdAndDateBetween(Long id, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByDisciplinIdAndTeacherIdAndDateBetween(Long disciplineId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByDisciplinIdAndStudentIdAndDateBetween(Long disciplineId, Long studentId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByDisciplinIdAndStudentIdAndTeacherIdAndDateBetween(Long disciplineId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndTeacherIdAndDateBetween(Long classTitleId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndStudentIdAndDateBetween(Long classTitleId, Long studentId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndStudentIdAndTeacherIdAndDateBetween(Long classTitleId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndDisciplinIdAndDateBetween(Long id, Long disciplineId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndDisciplinIdAndTeacherIdAndDateBetween(Long id, Long disciplineId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndDisciplinIdAndStudentIdAndDateBetween(Long id, Long disciplineId, Long studentId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findClassJournalByClassTitleIdAndDisciplinIdAndStudentIdAndTeacherIdAndDateBetween(Long id, Long disciplineId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort);

    List<ClassJournal> findAllByDateBetween(Date date1, Date date2, Sort sort);

}
