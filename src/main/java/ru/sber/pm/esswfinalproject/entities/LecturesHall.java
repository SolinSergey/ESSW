package ru.sber.pm.esswfinalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lecture_hall")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LecturesHall {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private int number;
    @Column(name = "floor")
    private int floor;
    @Column(name = "title")
    private String title;

}
