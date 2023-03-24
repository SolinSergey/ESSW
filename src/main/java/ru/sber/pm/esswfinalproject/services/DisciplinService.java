package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.converters.DisciplnDtoConverter;
import ru.sber.pm.esswfinalproject.dto.DisciplinDto;
import ru.sber.pm.esswfinalproject.entities.Disciplin;
import ru.sber.pm.esswfinalproject.repositories.DisciplinRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DisciplinService {

    private final DisciplnDtoConverter disciplnDtoConverter;
    private final DisciplinRepository disciplinRepository;

    Sort sort = Sort.by("title").ascending();

    public List<DisciplinDto> listAll() {
        List<Disciplin> disciplinList = disciplinRepository.findAll(sort);
        List<DisciplinDto> disciplinDtoList = new ArrayList<>();
        for (Disciplin d : disciplinList) {
            disciplinDtoList.add(disciplnDtoConverter.entityToDto(d));
        }
        return disciplinDtoList;
    }

    public Optional<Disciplin> findById(Long id) {
        return disciplinRepository.findById(id);
    }

    public void addDiscipline(DisciplinDto disciplinDto) {
        Disciplin disciplin = new Disciplin();
        disciplin.setTitle(disciplinDto.getTitle());
        disciplinRepository.save(disciplin);
    }

    public Disciplin findDisciplinByTitle(String title) {
        return disciplinRepository.findDisciplinByTitle(title);
    }
}
