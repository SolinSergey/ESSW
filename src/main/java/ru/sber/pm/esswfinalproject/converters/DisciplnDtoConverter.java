package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.DisciplinDto;
import ru.sber.pm.esswfinalproject.entities.Disciplin;

@RequiredArgsConstructor
@Component
public class DisciplnDtoConverter {
    public DisciplinDto entityToDto(Disciplin disciplin) {
        DisciplinDto disciplinDto = new DisciplinDto();
        disciplinDto.setId(disciplin.getId());
        disciplinDto.setTitle(disciplin.getTitle());
        return disciplinDto;
    }
}
