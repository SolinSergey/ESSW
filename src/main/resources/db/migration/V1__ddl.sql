CREATE TABLE users
(
    id        bigserial not null primary key,
    user_name character varying(255) unique,
    password  character varying(255)
);


insert into users (user_name, password)
values ('nata', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('serg', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('petrovna', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('gagarin', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('denis', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Koly', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Kostya', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('fedya', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('vlad', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('boris', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('alex', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('chemodan', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('surok', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('oleg', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('gerasim', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('sobchak', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('cheburashka', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('negodyay', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Alexandra', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('More', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Elka', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Ivan', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Andrey', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('zloyshilin', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Dmitriy', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Yana', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Galya', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Zina', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Kosmos', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Windows', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('Apple', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('krevetka', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('sara', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('piter', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('kail', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('john', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('miles', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('karlson', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('ventura', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('sheron', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('ura', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('germiona', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('emmi', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO'),
       ('smitt', '$2y$10$iAG23E286yLgiN4AqQBp1eTcvT4ihFzgyFE3ePLHQuSpYkiArkKhO');


CREATE TABLE roles
(
    id   bigserial NOT NULL PRIMARY KEY,
    name character varying(255) unique
);

insert into roles (id, name)
values (1, 'ROLE_STUDENT'),
       (2, 'ROLE_TEACHER'),
       (3, 'ROLE_HEADTEACHER'),
       (4, 'ROLE_ADMIN');

CREATE TABLE user_roles
(
    user_id bigint NOT NULL REFERENCES users (id),
    role_id bigint NOT NULL REFERENCES roles (id),
    primary key (user_id, role_id)
);

insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 1),
       (6, 2),
       (7, 2),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (17, 1),
       (18, 1),
       (19, 3),
       (20, 3),
       (21, 3),
       (22, 2),
       (23, 2),
       (24, 2),
       (25, 2),
       (26, 2),
       (27, 2),
       (28, 2),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1),
       (33, 1),
       (34, 1),
       (35, 1),
       (36, 1),
       (37, 1),
       (38, 1),
       (39, 1),
       (40, 1),
       (41, 1),
       (42, 1),
       (43, 1),
       (44, 1);


CREATE TABLE numbers_lesson
(
    id                 bigserial NOT NULL PRIMARY KEY,
    number_lesson      integer unique,
    time_start_lesson  character varying(255) unique,
    time_finish_lesson character varying(255) unique
);

insert into numbers_lesson (number_lesson, time_start_lesson, time_finish_lesson)
values (1, '8:00', '8:45'),
       (2, '9:00', '9:45'),
       (3, '10:00', '10:45'),
       (4, '11:00', '11:45'),
       (5, '12:00', '12:45'),
       (6, '13:00', '13:45');

CREATE TABLE lecture_hall
(
    id     bigserial NOT NULL PRIMARY KEY,
    floor  integer,
    number integer unique,
    title  character varying(255)
);

insert into lecture_hall (floor, number, title)
values (1, 101, 'Кабинет алгебры'),
       (1, 102, 'Кабинет геометрии'),
       (1, 103, 'Кабинет физики'),
       (1, 104, 'Кабинет Химии'),
       (1, 105, 'Кабинет Биологии'),
       (2, 201, 'Кабинет Истории'),
       (2, 202, 'Кабинет изучения жужжащих машинок с кнопочками'),
       (2, 203, 'Кабинет иностранных языков'),
       (2, 204, 'Кабинет литературы'),
       (2, 205, 'Спортивный зал');

CREATE TABLE disciplins
(
    id    bigserial NOT NULL PRIMARY KEY,
    title character varying(255) unique

);

insert into disciplins (title)
values ('Алгебра'),
       ('Геометрия'),
       ('Физика'),
       ('Химия'),
       ('Биология'),
       ('История'),
       ('Информатика'),
       ('Английский язык'),
       ('Литература'),
       ('Физкультура');

CREATE TABLE head_teachers
(
    id          bigserial NOT NULL PRIMARY KEY,
    first_names character varying(255),
    last_name   character varying(255),
    user_id     bigint unique REFERENCES users (id)
);

insert into head_teachers (first_names, last_name, user_id)
values ('Анна', 'Петровна', 3),
       ('Александра', 'Александрийская', 19),
       ('Марина', 'Морская', 20),
       ('Елена', 'Елкина', 21);

CREATE TABLE teacher
(
    id          bigserial NOT NULL PRIMARY KEY,
    first_names character varying(255),
    last_name   character varying(255),
    user_id     bigint unique REFERENCES users (id)
);

insert into teacher (first_names, last_name, user_id)
values ('Сергей', 'Сергеев', 2),
       ('Николай', 'Николаев', 6),
       ('Константинов', 'Константин', 7),
       ('Иван', 'Иванов', 22),
       ('Андрей', 'Андреев', 23),
       ('Сергей', 'Шилин', 24),
       ('Дмитрий', 'Дмитриев', 25),
       ('Яна', 'Ганжа', 26),
       ('Галина', 'Галеева', 27),
       ('Зинаида', 'Зидан', 28);


CREATE TABLE profiles_teachers
(
    id            bigserial NOT NULL PRIMARY KEY,
    disciplins_id bigint unique REFERENCES disciplins (id),
    teachers_id   bigint REFERENCES teacher (id)
);

insert into profiles_teachers (disciplins_id, teachers_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

CREATE TABLE students
(
    id         bigserial NOT NULL PRIMARY KEY,
    first_name character varying(255),
    last_name  character varying(255),
    user_id    bigint unique REFERENCES users (id)
);

insert into students (first_name, last_name, user_id)
values ('Наталия', 'Прекрасная', 1),
       ('Гагарин', 'Юрий', 4),
       ('Денис', 'Денисов', 5),
       ('Федор', 'Федоров', 8),
       ('Владислав', 'Всемогущий', 9),
       ('Борис', 'Борисов', 10),
       ('Алексей', 'Алексеев', 11),
       ('Роман', 'Романов', 12),
       ('Евгений', 'Сурков', 13),
       ('Олег', 'Вещий', 14),
       ('Герасим', 'Утопленников', 15),
       ('Петр', 'Романов', 16),
       ('Гена', 'Чебурашков', 17),
       ('Сергей', 'Королёв', 29),
       ('Билл', 'Гейтс', 30),
       ('Стив', 'Джобс', 31),
       ('Форест', 'Гамп', 32),
       ('Сара', 'Коннор', 33),
       ('Питер', 'Зильберман', 34),
       ('Кайл', 'Риз', 35),
       ('Джон', 'Коннор', 36),
       ('Карлсон', 'Вентиляторный', 38),
       ('Майлз', 'Дайсон', 37),
       ('Эйс', 'Вентура', 39),
       ('Шерон', 'Стоун', 40),
       ('Микки', 'Рурк', 41),
       ('Гермиона', 'Грейнджер', 42),
       ('Эмми', 'Уотсон', 43),
       ('Уилл', 'Смитт', 44),
       ('Павел', 'Негодяев', 18);


CREATE TABLE class_titles
(
    id                 bigserial NOT NULL PRIMARY KEY,
    title              character varying(255),
    teacher_advisor_id bigint unique REFERENCES teacher (id)

);

insert into class_titles (title, teacher_advisor_id)
values ('11А', 1),
       ('11Б', 2),
       ('11В', 3);

CREATE TABLE classes
(
    id         bigserial NOT NULL PRIMARY KEY,
    class_id   bigint references class_titles (id),
    student_id bigint unique REFERENCES students (id)
);

insert into classes (class_id, student_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (2, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (2, 17),
       (2, 18),
       (2, 19),
       (2, 20),
       (3, 21),
       (3, 22),
       (3, 23),
       (3, 24),
       (3, 25),
       (3, 26),
       (3, 27),
       (3, 28),
       (3, 29),
       (3, 30);

CREATE TABLE classes_journals
(
    id           bigserial NOT NULL PRIMARY KEY,
    date_grade   date,
    description  character varying(255),
    grade        integer,
    class_id     bigint REFERENCES class_titles (id),
    disciplin_id bigint REFERENCES disciplins (id),
    student_id   bigint REFERENCES students (id),
    teacher_id   bigint REFERENCES teacher (id)
);

insert into classes_journals (date_grade, description, grade, class_id, disciplin_id, student_id, teacher_id)
values ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 1, 1, 1, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 1, 1, 2, 1),
       ('2023-01-09', 'ввычисление скорости яхты от дома до дачи', 5, 1, 1, 3, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 1, 1, 4, 1),
       ('2023-01-09', 'построение треугольника', 5, 1, 2, 5, 2),
       ('2023-01-09', 'построение треугольника', 5, 1, 2, 6, 2),
       ('2023-01-09', 'построение треугольника', 5, 1, 2, 7, 2),
       ('2023-01-09', 'построение треугольника', 5, 1, 2, 1, 2),
       ('2023-01-09', 'ускорение', 5, 1, 3, 2, 3),
       ('2023-01-09', 'ускорение', 5, 1, 3, 3, 3),
       ('2023-01-09', 'ускорение', 5, 1, 3, 4, 3),
       ('2023-01-09', 'ускорение', 5, 1, 3, 5, 3),
       ('2023-01-09', 'ускорение', 5, 2, 3, 11, 3),
       ('2023-01-09', 'ускорение', 5, 2, 3, 12, 3),
       ('2023-01-09', 'ускорение', 5, 2, 3, 13, 3),
       ('2023-01-09', 'ускорение', 5, 2, 3, 14, 3),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 2, 1, 11, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 2, 1, 12, 1),
       ('2023-01-09', 'ввычисление скорости яхты от дома до дачи', 5, 2, 1, 13, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 2, 1, 14, 1),
       ('2023-01-09', 'построение треугольника', 5, 2, 2, 15, 2),
       ('2023-01-09', 'построение треугольника', 5, 2, 2, 11, 2),
       ('2023-01-09', 'построение треугольника', 5, 2, 2, 14, 2),
       ('2023-01-09', 'построение треугольника', 5, 2, 2, 18, 2),
       ('2023-01-09', 'построение треугольника', 5, 3, 2, 21, 2),
       ('2023-01-09', 'построение треугольника', 5, 3, 2, 22, 2),
       ('2023-01-09', 'построение треугольника', 5, 3, 2, 23, 2),
       ('2023-01-09', 'построение треугольника', 5, 3, 2, 25, 2),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 3, 1, 26, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 3, 1, 27, 1),
       ('2023-01-09', 'ввычисление скорости яхты от дома до дачи', 5, 3, 1, 28, 1),
       ('2023-01-09', 'вычисление скорости яхты от дома до дачи', 5, 3, 1, 29, 1),
       ('2023-01-10', 'Алкены', 5, 1, 4, 1, 4),
       ('2023-01-10', 'Алкены', 5, 1, 4, 2, 4),
       ('2023-01-10', 'Алкены', 5, 1, 4, 3, 4),
       ('2023-01-10', 'Алкены', 5, 1, 4, 4, 4),
       ('2023-01-10', 'Алкены', 5, 1, 4, 5, 4),
       ('2023-01-10', 'Алкены', 5, 1, 4, 11, 4),
       ('2023-01-10', 'Алкены', 5, 2, 4, 12, 4),
       ('2023-01-10', 'Алкены', 5, 2, 4, 13, 4),
       ('2023-01-10', 'Алкены', 5, 2, 4, 14, 4),
       ('2023-01-10', 'Алкены', 5, 2, 4, 15, 4),
       ('2023-01-10', 'Алкены', 5, 2, 4, 16, 4),
       ('2023-01-10', 'Алкены', 5, 3, 4, 21, 4),
       ('2023-01-10', 'Алкены', 5, 3, 4, 22, 4),
       ('2023-01-10', 'Алкены', 5, 3, 4, 23, 4),
       ('2023-01-10', 'Алкены', 5, 3, 4, 24, 4),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 1, 5, 1, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 1, 5, 2, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 1, 5, 3, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 1, 5, 4, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 2, 5, 11, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 2, 5, 12, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 2, 5, 14, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 2, 5, 16, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 3, 5, 26, 5),
       ('2023-01-10', 'Как вырастить замиакулькас', 5, 3, 5, 27, 5),
       ('2023-01-10', 'Первые Романовы', 5, 1, 6, 1, 5),
       ('2023-01-10', 'Первые Романовы', 5, 1, 6, 2, 5),
       ('2023-01-10', 'Первые Романовы', 5, 1, 6, 3, 5),
       ('2023-01-10', 'Первые Романовы', 5, 1, 6, 4, 5),
       ('2023-01-10', 'Первые Романовы', 5, 1, 6, 5, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 11, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 12, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 15, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 14, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 13, 5),
       ('2023-01-10', 'Первые Романовы', 5, 2, 6, 17, 5),
       ('2023-01-10', 'Первые Романовы', 5, 3, 6, 26, 5),
       ('2023-01-10', 'Первые Романовы', 5, 3, 6, 27, 5),
       ('2023-01-10', 'Первые Романовы', 5, 3, 6, 30, 5);



CREATE TABLE timetables
(
    id               bigserial NOT NULL PRIMARY KEY,
    number_of_day    integer,
    class_id         bigint REFERENCES class_titles (id),
    disciplin_id     bigint REFERENCES disciplins (id),
    lecture_hall_id  bigint REFERENCES lecture_hall (id),
    number_lesson_id bigint REFERENCES numbers_lesson (id),
    teacher_id       bigint REFERENCES teacher (id)
);

insert into timetables (number_of_day, class_id, disciplin_id, lecture_hall_id, number_lesson_id, teacher_id)
values (1, 1, 1, 1, 1, 1),
       (1, 1, 1, 1, 2, 1),
       (1, 1, 2, 2, 3, 2),
       (1, 1, 2, 2, 4, 2),
       (1, 1, 3, 3, 5, 3),
       (1, 1, 3, 3, 6, 3),
       (1, 2, 3, 3, 1, 3),
       (1, 2, 3, 3, 2, 3),
       (1, 2, 1, 1, 3, 1),
       (1, 2, 1, 1, 4, 1),
       (1, 2, 2, 2, 5, 2),
       (1, 2, 2, 2, 6, 2),
       (1, 3, 2, 2, 1, 2),
       (1, 3, 2, 2, 2, 2),
       (1, 3, 3, 3, 3, 3),
       (1, 3, 3, 3, 4, 3),
       (1, 3, 1, 1, 5, 1),
       (1, 3, 1, 1, 6, 1),
       (2, 1, 4, 4, 1, 4),
       (2, 1, 4, 4, 2, 4),
       (2, 1, 5, 5, 3, 5),
       (2, 1, 5, 5, 4, 5),
       (2, 1, 6, 6, 5, 6),
       (2, 1, 6, 6, 6, 6),
       (2, 2, 5, 5, 1, 5),
       (2, 2, 5, 5, 2, 5),
       (2, 2, 6, 6, 3, 6),
       (2, 2, 6, 6, 4, 6),
       (2, 2, 4, 4, 5, 4),
       (2, 2, 4, 4, 6, 4),
       (2, 3, 6, 6, 1, 6),
       (2, 3, 6, 6, 2, 6),
       (2, 3, 4, 4, 3, 4),
       (2, 3, 4, 4, 4, 4),
       (2, 3, 5, 5, 5, 5),
       (2, 3, 5, 5, 6, 5),
       (3, 1, 7, 7, 1, 7),
       (3, 1, 7, 7, 2, 7),
       (3, 1, 8, 8, 3, 8),
       (3, 1, 8, 8, 4, 8),
       (3, 1, 9, 9, 5, 9),
       (3, 1, 9, 9, 6, 9),
       (3, 2, 8, 8, 1, 8),
       (3, 2, 8, 8, 2, 8),
       (3, 2, 9, 9, 3, 9),
       (3, 2, 9, 9, 4, 9),
       (3, 2, 7, 7, 5, 7),
       (3, 2, 7, 7, 6, 7),
       (3, 3, 9, 9, 1, 9),
       (3, 3, 9, 9, 2, 9),
       (3, 3, 7, 7, 3, 7),
       (3, 3, 7, 7, 4, 7),
       (3, 3, 8, 8, 5, 8),
       (3, 3, 8, 8, 6, 8),
       (4, 1, 10, 10, 1, 10),
       (4, 1, 10, 10, 2, 10),
       (4, 1, 1, 1, 3, 1),
       (4, 1, 1, 1, 4, 1),
       (4, 1, 7, 7, 5, 7),
       (4, 1, 7, 7, 6, 7),
       (4, 2, 7, 7, 1, 7),
       (4, 2, 7, 7, 2, 7),
       (4, 2, 10, 10, 3, 10),
       (4, 2, 10, 10, 4, 10),
       (4, 2, 1, 1, 5, 1),
       (4, 2, 1, 1, 6, 1),
       (4, 3, 1, 1, 1, 1),
       (4, 3, 1, 1, 2, 1),
       (4, 3, 7, 7, 3, 7),
       (4, 3, 7, 7, 4, 7),
       (4, 3, 10, 10, 5, 10),
       (4, 3, 10, 10, 6, 10),
       (5, 1, 2, 2, 1, 2),
       (5, 1, 2, 2, 2, 2),
       (5, 1, 3, 3, 3, 3),
       (5, 1, 3, 3, 4, 3),
       (5, 1, 8, 8, 5, 8),
       (5, 1, 8, 8, 6, 8),
       (5, 2, 3, 3, 1, 3),
       (5, 2, 3, 3, 2, 3),
       (5, 2, 8, 8, 3, 8),
       (5, 2, 8, 8, 4, 8),
       (5, 2, 2, 2, 5, 2),
       (5, 2, 2, 2, 6, 2),
       (5, 3, 8, 8, 1, 8),
       (5, 3, 8, 8, 2, 8),
       (5, 3, 2, 2, 3, 2),
       (5, 3, 2, 2, 4, 2),
       (5, 3, 3, 3, 5, 3),
       (5, 3, 3, 3, 6, 3);