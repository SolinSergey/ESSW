package ru.sber.pm.esswfinalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "disciplins")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Disciplin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
}
