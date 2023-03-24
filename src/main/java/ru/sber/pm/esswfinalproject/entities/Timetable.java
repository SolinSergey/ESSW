package ru.sber.pm.esswfinalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "timetables")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Timetable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numberOfDay")
    private int numberOfDay;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplin_id",
            foreignKey = @ForeignKey(name = "FK_TIMETABLE_DISCIPLIN")
    )
    private Disciplin disciplin;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id",
            foreignKey = @ForeignKey(name = "FK_TIMETABLE_TEACHER")
    )
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id",
            foreignKey = @ForeignKey(name = "FK_TIMETABLE_CLASS"))
    private ClassTitle classTitle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lecture_hall_id",
            foreignKey = @ForeignKey(name = "FK_TIMETABLE_LECTURESHAL"))
    private LecturesHall lecturesHall;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "number_lesson_id",
            foreignKey = @ForeignKey(name = "FK_TIMETABLE_NUMBERLESSON"))
    private NumberLesson numberLesson;
}

