package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.ClassTitleDtoConverter;
import ru.sber.pm.esswfinalproject.dto.ClassTitleDto;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Teacher;
import ru.sber.pm.esswfinalproject.repositories.ClassTitleRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassTitleService {
    private final ClassTitleRepository classTitleRepository;
    private final ClassTitleDtoConverter classTitleDtoConverter;

    Sort sort = Sort.by("title").ascending();

    public ClassTitle findClassTitleById(Long id) {
        return classTitleRepository.findClassTitleById(id);
    }

    public boolean isClassTitlePresent(String classTitleTitle) {
        ClassTitle classTitle = classTitleRepository.findClassTitleByTitle(classTitleTitle);
        if (classTitle != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCheckNewTitle(String classTitle) {
        boolean checkResult = false;
        if (classTitle.charAt(classTitle.length() - 1) >= '\u0410' && classTitle.charAt(classTitle.length() - 1) <= '\u042F')
            checkResult = true;
        if (classTitle.length() == 2) {
            if (classTitle.charAt(0) >= '\u0030' && classTitle.charAt(0) <= '\u0039') {
                checkResult &= true;
            } else {
                checkResult &= false;
            }
        } else {
            if (classTitle.length() == 3) {
                if (classTitle.charAt(0) >= '\u0030' && classTitle.charAt(0) <= '\u0039' && classTitle.charAt(1) >= '\u0030' && classTitle.charAt(1) <= '\u0039') {
                    checkResult &= true;
                } else {
                    checkResult &= false;
                }
            }
        }
        return checkResult;
    }

    public ClassTitle saveTeacher(ClassTitle classTitle) {
        classTitleRepository.save(classTitle);
        return classTitle;
    }

    public ClassTitle findClassTitleByTeacher(Teacher teacher) {
        return classTitleRepository.findClassTitleByTeacher(teacher);
    }

    public void addClassTitle(ClassTitle classTitle) {
        classTitleRepository.save(classTitle);
    }

    public List<ClassTitleDto> listAll() {
        List<ClassTitle> classTitleList = classTitleRepository.findAll(sort);
        List<ClassTitleDto> classTitleDtoList = new ArrayList<>();
        for (ClassTitle ct : classTitleList) {
            classTitleDtoList.add(classTitleDtoConverter.entityToDto(ct));
        }
        return classTitleDtoList;
    }
}

