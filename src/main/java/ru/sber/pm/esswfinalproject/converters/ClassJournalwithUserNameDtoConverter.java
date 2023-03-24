package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.ClassJournalWithUserNameDto;
import ru.sber.pm.esswfinalproject.entities.ClassJournal;

import java.text.ParseException;

@RequiredArgsConstructor
@Component
public class ClassJournalwithUserNameDtoConverter {
    public ClassJournalWithUserNameDto entityToDto(ClassJournal classJournal) throws ParseException {
        ClassJournalWithUserNameDto classJournalDto = new ClassJournalWithUserNameDto();
        classJournalDto.setStart(classJournal.getDate());
        classJournalDto.setEnd(classJournal.getDate());
        classJournalDto.setDescription(classJournal.getDescription());
        classJournalDto.setGrade(classJournal.getGrade());
        classJournalDto.setClassTitleTitle(classJournal.getClassTitle().getTitle());
        classJournalDto.setStudentId(classJournal.getStudent().getId());
        classJournalDto.setTeacherId(classJournal.getTeacher().getId());
        classJournalDto.setDisciplinId(classJournal.getDisciplin().getId());
        return classJournalDto;
    }
}

