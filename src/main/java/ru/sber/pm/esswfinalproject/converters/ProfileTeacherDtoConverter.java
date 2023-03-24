package ru.sber.pm.esswfinalproject.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.pm.esswfinalproject.dto.ProfileTeacherDto;
import ru.sber.pm.esswfinalproject.entities.ProfileTeacher;

@RequiredArgsConstructor
@Component
public class ProfileTeacherDtoConverter {
    public ProfileTeacherDto entityToDto(ProfileTeacher profileTeacher) {
        ProfileTeacherDto profileTeacherDto = new ProfileTeacherDto();
        profileTeacherDto.setId(profileTeacher.getId());
        profileTeacherDto.setDisciplinsId(profileTeacher.getDisciplin().getId());
        return profileTeacherDto;
    }
}
