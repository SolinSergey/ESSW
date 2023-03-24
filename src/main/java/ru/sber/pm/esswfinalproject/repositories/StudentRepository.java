package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.Student;
import ru.sber.pm.esswfinalproject.entities.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUser(User user);

    Student findByUserUsername(String userName);
}
