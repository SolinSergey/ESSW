package ru.sber.pm.esswfinalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "head_teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HeadTeacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_Names")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
}
