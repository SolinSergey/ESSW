package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.ClassJournalDto;
import ru.sber.pm.esswfinalproject.entities.ClassJournal;

@RequiredArgsConstructor
@Component
public class ClassJournalDtoConverter {
    public ClassJournalDto entityToDto(ClassJournal classJournal) {
        ClassJournalDto classJournalDto = new ClassJournalDto();
        classJournalDto.setStart(classJournal.getDate());
        classJournalDto.setEnd(classJournal.getDate());
        classJournalDto.setDescription(classJournal.getDescription());
        classJournalDto.setGrade(classJournal.getGrade());
        classJournalDto.setClassTitleId(classJournal.getClassTitle().getId());
        classJournalDto.setStudentId(classJournal.getStudent().getId());
        classJournalDto.setTeacherId(classJournal.getTeacher().getId());
        classJournalDto.setDisciplinId(classJournal.getDisciplin().getId());
        return classJournalDto;
    }
}
