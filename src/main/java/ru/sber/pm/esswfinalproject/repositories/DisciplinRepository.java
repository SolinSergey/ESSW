package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.Disciplin;

@Repository
public interface DisciplinRepository extends JpaRepository<Disciplin, Long> {
    Disciplin findDisciplinByTitle(String title);
}
