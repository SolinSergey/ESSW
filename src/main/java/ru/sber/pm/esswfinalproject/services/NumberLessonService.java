package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.entities.NumberLesson;
import ru.sber.pm.esswfinalproject.repositories.NumberLessonRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NumberLessonService {
    private final NumberLessonRepository numberLessonRepository;

    public List<NumberLesson> listAll() {
        return numberLessonRepository.findAll();
    }

    public Optional<NumberLesson> findById(Long id) {
        return numberLessonRepository.findById(id);
    }

}
