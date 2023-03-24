package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.LecturesHallDtoConverter;
import ru.sber.pm.esswfinalproject.dto.LecturesHallDto;
import ru.sber.pm.esswfinalproject.entities.LecturesHall;
import ru.sber.pm.esswfinalproject.repositories.LecturesHallRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LecturesHallService {
    private final LecturesHallRepository lecturesHallRepository;
    private final LecturesHallDtoConverter lecturesHallDtoConverter;

    Sort sort = Sort.by( "title").ascending();

    public List<LecturesHallDto> listAll() {
        List<LecturesHall> lecturesHalls = lecturesHallRepository.findAll(sort);
        List<LecturesHallDto> lecturesHallDtoList = new ArrayList<>();
        for (LecturesHall l : lecturesHalls) {
            lecturesHallDtoList.add(lecturesHallDtoConverter.entityToDto(l));
        }
        return lecturesHallDtoList;
    }

    public Optional<LecturesHall> findById(Long id) {
        return lecturesHallRepository.findById(id);
    }

}
