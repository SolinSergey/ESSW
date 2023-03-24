package ru.sber.pm.esswfinalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "numbers_lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NumberLesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numberLesson")
    private int numberLesson;
    @Column(name = "timeStartLesson")

    private String timeStartLesson;
    @Column(name = "timeFinishLesson")
    private String timeFinishLesson;
}
