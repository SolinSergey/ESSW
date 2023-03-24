package ru.sber.pm.esswfinalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.pm.esswfinalproject.entities.LecturesHall;

@Repository
public interface LecturesHallRepository extends JpaRepository<LecturesHall, Long> {
}
