package ru.sber.pm.esswfinalproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "class_titles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassTitle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 3)
    @Pattern(regexp = "^[А-Я0-9]+$", message = "Поле может содержать только цифры и ОДНУ ЗАГЛАВНУЮ РУССКУЮ букву")
    @Column(name = "title")
    private String title;
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "teacher_advisor_id")
    private Teacher teacher;
}
