package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.ClassTitleDto;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;

@RequiredArgsConstructor
@Component
public class ClassTitleDtoConverter {
    public ClassTitleDto entityToDto(ClassTitle classTitle) {
        ClassTitleDto classTitleDto = new ClassTitleDto();
        classTitleDto.setId(classTitle.getId());
        classTitleDto.setTitle(classTitle.getTitle());
        return classTitleDto;
    }
}


