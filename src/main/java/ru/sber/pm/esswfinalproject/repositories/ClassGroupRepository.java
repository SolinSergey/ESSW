package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.ClassGroup;
import ru.sber.pm.esswfinalproject.entities.ClassTitle;
import ru.sber.pm.esswfinalproject.entities.Student;

import java.util.List;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {

    List<ClassGroup> findByClassTitles_Id(Long id,Sort sort);

    List<ClassGroup> findClassGroupByClassTitles(ClassTitle classTitle, Sort sort);

    ClassGroup findClassGroupByStudent(Student student);

}
