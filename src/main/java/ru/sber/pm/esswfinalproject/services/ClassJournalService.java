package ru.sber.pm.esswfinalproject.services;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.ClassJournalDtoConverter;
import ru.sber.pm.esswfinalproject.converters.ClassJournalWithStringDtoConverter;
import ru.sber.pm.esswfinalproject.dto.ClassJournalDtoWithString;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.repositories.ClassGroupRepository;
import ru.sber.pm.esswfinalproject.repositories.ClassJournalRepository;
import ru.sber.pm.esswfinalproject.repositories.ClassTitleRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Builder
@RequiredArgsConstructor
@Service
public class ClassJournalService {
    private final ClassJournalRepository classJournalRepository;
    private final ClassJournalDtoConverter classJournalDtoConverter;
    private final ClassJournalWithStringDtoConverter classJournalWithStringDtoConverter;
    private final ClassTitleService classTitleService;
    private final ClassGroupRepository classGroupRepository;
    private final ClassTitleRepository classTitleRepository;
    private final ClassGroupService classGroupService;

    public void create(ClassJournal classJournal) {
        classJournalRepository.save(classJournal);
    }

    public List<ClassJournalDtoWithString> findClassJournalByStudentUserName(Student student, String start, String end) throws ParseException {
        Sort sort = Sort.by("date", "disciplin.title");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date first = formatter.parse(start);
        Date second = formatter.parse(end);
        List<ClassJournal> classJournals =
                findClassJournalByStudent_IdAndDateBetween(student.getId(), first, second, sort);
        List<ClassJournalDtoWithString> classJournalDtoList = new ArrayList<>();
        for (ClassJournal cj : classJournals) {
            classJournalDtoList.add(classJournalWithStringDtoConverter.entityToDto(cj));
        }
        return classJournalDtoList;
    }

    public List<ClassJournalDtoWithString> findClassJournalByClassTitleIdAndStudentIdAndDateBetween
            (String studentId, String classId, String start, String end) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date1 = formatter.parse(start + " 00:00:00.0");
        Date date2 = formatter.parse(end + " 00:00:00.0");
        List<ClassJournal> classJournals = null;
        Sort sort = Sort.by("date", "classTitle.title", "student.lastName");
        ClassTitle classTitle = classTitleService.findClassTitleById(Long.parseLong(classId));
        if (classId.equals("0") && studentId.equals("0")) {
            classJournals = classJournalRepository.findAllByDateBetween(date1, date2, sort);
        }
        if (classId.equals("0") && !studentId.equals("0")) {
            classJournals = classJournalRepository.findAllByStudentIdAndDateBetween(Long.valueOf(studentId), date1, date2, sort);
        }
        if (!classId.equals("0") && studentId.equals("0")) {
            classJournals = classJournalRepository.findClassJournalByClassTitleAndDateBetween(classTitle, date1, date2, sort);
        }
        if (!classId.equals("0") && !studentId.equals("0")) {
            classJournals = classJournalRepository.findClassJournalByClassTitleAndStudentIdAndDateBetween(classTitle, Long.valueOf(studentId), date1, date2, sort);
        }
        List<ClassJournalDtoWithString> classJournalDtoWithStrings = new ArrayList<>();
        for (ClassJournal cj : classJournals) {
            classJournalDtoWithStrings.add(classJournalWithStringDtoConverter.entityToDto(cj));
        }
        return classJournalDtoWithStrings;
    }

    public List<ClassJournal> findClassJournalByStudent_IdAndDateBetween(Long id, Date start, Date end, Sort sort) {
        return classJournalRepository.findClassJournalByStudent_IdAndDateBetween(id, start, end, sort);
    }

    private List<ClassJournalDtoWithString> classJournalListConvert(List<ClassJournal> classJournalList) throws ParseException {
        List<ClassJournalDtoWithString> classJournalDtoWithStringList = new ArrayList<>();
        for (ClassJournal cj : classJournalList) {
            classJournalDtoWithStringList.add(classJournalWithStringDtoConverter.entityToDto(cj));
        }
        return classJournalDtoWithStringList;
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleId(Long classTitle, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndDateBetween(classTitle, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByTeacherId(Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByTeacherIdAndDateBetween(teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByStudentId(Long studentId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByStudentIdAndDateBetween(studentId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByStudentIdAndTeacherId(Long studentId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByStudentIdAndTeacherIdAndDateBetween(studentId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByDisciplineId(Long disciplineId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByDisciplinIdAndDateBetween(disciplineId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByDisciplineIdAndTeacherId(Long disciplineId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByDisciplinIdAndTeacherIdAndDateBetween(disciplineId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByDisciplineIdAndStudentId(Long disciplineId, Long studentId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByDisciplinIdAndStudentIdAndDateBetween(disciplineId, studentId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByDisciplineIdAndStudentIdAndTeacherId(Long disciplineId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByDisciplinIdAndStudentIdAndTeacherIdAndDateBetween(disciplineId, studentId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndTeacherId(Long classTitleId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndTeacherIdAndDateBetween(classTitleId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndStudentId(Long classTitleId, Long studentId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndStudentIdAndDateBetween(classTitleId, studentId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndStudentIdAndTeacherId(Long classTitleId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndStudentIdAndTeacherIdAndDateBetween(classTitleId, studentId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndDisciplineId(Long classTitleId, Long disciplineId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndDisciplinIdAndDateBetween(classTitleId, disciplineId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndDisciplineIdAndTeacherId(Long classTitleId, Long disciplineId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndDisciplinIdAndTeacherIdAndDateBetween(classTitleId, disciplineId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndDisciplineIdAndStudentId(Long classTitleId, Long disciplineId, Long studentId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndDisciplinIdAndStudentIdAndDateBetween(classTitleId, disciplineId, studentId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllByClassTitleIdAndDisciplineIdAndStudentIdAndTeacherId(Long classTitleId, Long disciplineId, Long studentId, Long teacherId, Date date1, Date date2, Sort sort) throws ParseException {
        return classJournalListConvert(classJournalRepository.findClassJournalByClassTitleIdAndDisciplinIdAndStudentIdAndTeacherIdAndDateBetween(classTitleId, disciplineId, studentId, teacherId, date1, date2, sort));
    }

    public List<ClassJournalDtoWithString> listAllWithFilterSelect(Long classTitleId, Long disciplineId, Long studentId, Long teacherId, String startDate, String endDate) throws ParseException {
        List<ClassJournalDtoWithString> classJournalDtoWithStringList = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date1 = formatter.parse(startDate + " 00:00:00.0");
        Date date2 = formatter.parse(endDate + " 00:00:00.0");
        Sort sort = Sort.by("date").ascending().and(Sort.by("classTitle.title").ascending().and(Sort.by("student.lastName").ascending()));
        if (classTitleId == 0 && disciplineId == 0 && studentId == 0 && teacherId == 0) {
            List<ClassJournal> classJournalList = classJournalRepository.findAllByDateBetween(date1, date2, sort);
            classJournalDtoWithStringList = classJournalListConvert(classJournalList);
        } else if (classTitleId == 0 && disciplineId == 0 && studentId == 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByTeacherId(teacherId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId == 0 && studentId != 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByStudentId(studentId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId == 0 && studentId != 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByStudentIdAndTeacherId(studentId, teacherId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId != 0 && studentId == 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByDisciplineId(disciplineId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId != 0 && studentId == 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByDisciplineIdAndTeacherId(disciplineId, teacherId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId != 0 && studentId != 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByDisciplineIdAndStudentId(disciplineId, studentId, date1, date2, sort);
        } else if (classTitleId == 0 && disciplineId != 0 && studentId != 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByDisciplineIdAndStudentIdAndTeacherId(disciplineId, studentId, teacherId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId == 0 && studentId == 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByClassTitleId(classTitleId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId == 0 && studentId == 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndTeacherId(classTitleId, teacherId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId == 0 && studentId != 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndStudentId(classTitleId, studentId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId == 0 && studentId != 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndStudentIdAndTeacherId(classTitleId, studentId, teacherId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId != 0 && studentId == 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndDisciplineId(classTitleId, disciplineId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId != 0 && studentId == 0 && teacherId != 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndDisciplineIdAndTeacherId(classTitleId, disciplineId, teacherId, date1, date2, sort);
        } else if (classTitleId != 0 && disciplineId != 0 && studentId != 0 && teacherId == 0) {
            classJournalDtoWithStringList = listAllByClassTitleIdAndDisciplineIdAndStudentId(classTitleId, disciplineId, studentId, date1, date2, sort);
        } else {
            classJournalDtoWithStringList = listAllByClassTitleIdAndDisciplineIdAndStudentIdAndTeacherId(classTitleId, disciplineId, studentId, teacherId, date1, date2, sort);
        }
        return classJournalDtoWithStringList;
    }
}