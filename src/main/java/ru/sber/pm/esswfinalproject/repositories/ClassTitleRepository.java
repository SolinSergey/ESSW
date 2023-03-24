package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Teacher;

public interface ClassTitleRepository extends JpaRepository<ClassTitle, Long> {

    ClassTitle findClassTitleById(Long id);

    ClassTitle findClassTitleByTitle(String title);

    ClassTitle findClassTitleByTeacher(Teacher teacher);
}
