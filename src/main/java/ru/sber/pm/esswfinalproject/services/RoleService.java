package ru.sber.pm.esswfinalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.pm.esswfinalproject.entities.Role;
import ru.sber.pm.esswfinalproject.repositories.RoleRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    public final RoleRepository roleRepository;

    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    public Role findByTitle(String name) {
        return roleRepository.findByName(name);
    }
}
