package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.ProfileTeacherDtoConverter;
import ru.sber.pm.esswfinalproject.dto.ProfileTeacherDto;
import ru.sber.pm.esswfinalproject.dto.TeacherProfileDto;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Disciplin;
import ru.sber.pm.esswfinalproject.entities.ProfileTeacher;
import ru.sber.pm.esswfinalproject.entities.Teacher;
import ru.sber.pm.esswfinalproject.repositories.ProfileTeacherRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileTeacherService {
    private final ProfileTeacherRepository profileTeacherRepository;
    private final ProfileTeacherDtoConverter profileTeacherDtoConverter;
    private final ClassTitleService classTitleService;

    public List<ProfileTeacherDto> listAll() {
        List<ProfileTeacher> profileTeachers = profileTeacherRepository.findAll();
        List<ProfileTeacherDto> profileTeacherList = new ArrayList<>();
        for (ProfileTeacher profileTeacher : profileTeachers) {
            profileTeacherList.add(profileTeacherDtoConverter.entityToDto(profileTeacher));
        }
        return profileTeacherList;
    }

    public ProfileTeacher findProfileTeacherByTeacher(Teacher teacher) {
        return profileTeacherRepository.findProfileTeacherByTeacher(teacher);
    }


    public ProfileTeacher saveProfileTeacher(ProfileTeacher profileTeacher) {
        profileTeacherRepository.save(profileTeacher);
        return profileTeacher;
    }

    public ProfileTeacher findProfileTeacherByDisciplin(Disciplin disciplin) {
        return profileTeacherRepository.findProfileTeacherByDisciplin(disciplin);
    }

    public TeacherProfileDto prepareTeacherProfile(Teacher teacher) {
        ProfileTeacher profileTeachers = profileTeacherRepository.findProfileTeacherByTeacher(teacher);
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        TeacherProfileDto teacherProfileDto = new TeacherProfileDto();
        if (profileTeachers == null && classTitle == null) {
            teacherProfileDto.setFirstName(teacher.getFirstName());
            teacherProfileDto.setLastName(teacher.getLastName());
            teacherProfileDto.setDisciplinTitle("дисциплина не назначена");
            teacherProfileDto.setClassTitle("Класс не назначен");
            return teacherProfileDto;
        }
        if (profileTeachers.getDisciplin() == null && classTitle == null) {
            teacherProfileDto.setFirstName(teacher.getFirstName());
            teacherProfileDto.setLastName(teacher.getLastName());
            teacherProfileDto.setDisciplinTitle("дисциплина не назначена");
            teacherProfileDto.setClassTitle("Класс не назначен");
            return teacherProfileDto;
        }
        if (classTitle == null) {
            teacherProfileDto.setDisciplinTitle(profileTeachers.getDisciplin().getTitle());
            teacherProfileDto.setFirstName(profileTeachers.getTeacher().getFirstName());
            teacherProfileDto.setLastName(profileTeachers.getTeacher().getLastName());
            teacherProfileDto.setClassTitle("Класс не назначен");
            return teacherProfileDto;

        }
        if (profileTeachers == null) {
            teacherProfileDto.setDisciplinTitle("дисциплина не назначена");
            teacherProfileDto.setFirstName(teacher.getFirstName());
            teacherProfileDto.setLastName(teacher.getLastName());
            teacherProfileDto.setClassTitle(classTitle.getTitle());
            return teacherProfileDto;
        }
        if (profileTeachers.getDisciplin() == null) {
            teacherProfileDto.setDisciplinTitle("дисциплина не назначена");
            teacherProfileDto.setFirstName(teacher.getFirstName());
            teacherProfileDto.setLastName(teacher.getLastName());
            teacherProfileDto.setClassTitle(classTitle.getTitle());
            return teacherProfileDto;
        }
        teacherProfileDto.setDisciplinTitle(profileTeachers.getDisciplin().getTitle());
        teacherProfileDto.setFirstName(profileTeachers.getTeacher().getFirstName());
        teacherProfileDto.setLastName(profileTeachers.getTeacher().getLastName());
        teacherProfileDto.setClassTitle(classTitle.getTitle());
        return teacherProfileDto;
    }
}


