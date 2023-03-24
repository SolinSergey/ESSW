package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.ClassJournalDtoWithString;
import ru.sber.pm.esswfinalproject.entities.ClassJournal;
import ru.sber.pm.esswfinalproject.utils.DataUtils;

import java.text.ParseException;

@RequiredArgsConstructor

@Component
public class ClassJournalWithStringDtoConverter {
    private final DataUtils dataUtils;

    public ClassJournalDtoWithString entityToDto(ClassJournal classJournal) throws ParseException {
        ClassJournalDtoWithString classJournalDtoWithString = new ClassJournalDtoWithString();
        classJournalDtoWithString.setClassTitle(classJournal.getClassTitle().getTitle());
        classJournalDtoWithString.setStart(classJournal.getDate());
        classJournalDtoWithString.setEnd(classJournal.getDate());
        classJournalDtoWithString.setDescription(classJournal.getDescription());
        classJournalDtoWithString.setTeacherFirstName(classJournal.getTeacher().getFirstName());
        classJournalDtoWithString.setTeacherSecondName(classJournal.getTeacher().getLastName());
        classJournalDtoWithString.setGrade(classJournal.getGrade());
        classJournalDtoWithString.setDisciplinTitle(classJournal.getDisciplin().getTitle());
        classJournalDtoWithString.setStudentId(classJournal.getStudent().getId());
        classJournalDtoWithString.setDateGrade(dataUtils.parsingDate(classJournal.getDate()));
        classJournalDtoWithString.setStudentFirstName(classJournal.getStudent().getFirstName());
        classJournalDtoWithString.setStudentLastName(classJournal.getStudent().getLastName());
        return classJournalDtoWithString;
    }

}
