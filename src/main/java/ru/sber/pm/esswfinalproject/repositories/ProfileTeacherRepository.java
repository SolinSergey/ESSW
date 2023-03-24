package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.Disciplin;
import ru.sber.pm.esswfinalproject.entities.ProfileTeacher;
import ru.sber.pm.esswfinalproject.entities.Teacher;

@Repository
public interface ProfileTeacherRepository extends JpaRepository<ProfileTeacher, Long> {

    ProfileTeacher findProfileTeacherByDisciplin(Disciplin disciplin);

    ProfileTeacher findProfileTeacherByTeacher(Teacher teacher);
}
