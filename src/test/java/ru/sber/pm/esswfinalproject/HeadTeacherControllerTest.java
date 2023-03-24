package ru.sber.pm.esswfinalproject;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.repositories.*;
import ru.sber.pm.esswfinalproject.services.HeadTeacherService;
import ru.sber.pm.esswfinalproject.utils.Day;
import ru.sber.pm.esswfinalproject.utils.HeadCJFilter;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("application.yaml")
public class HeadTeacherControllerTest {
    @Autowired
    private
    WebApplicationContext context;

    @MockBean
    private DisciplinRepository disciplinRepository;

    @MockBean
    private ClassTitleRepository classTitleRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private ProfileTeacherRepository profileTeacherRepository;

    @MockBean
    private HeadTeacherService headTeacherService;

    @MockBean
    TimetableRepository timetableRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("petrovna")
    public void getAllDiscipline() throws Exception {
        mockMvc.perform(get("/headteacher/listalldiscipline"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewAllDiscipline"))
                .andExpect(content().string(containsString("Список дисциплин")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addDiscipline() throws Exception {
        DisciplinDto disciplinDto = new DisciplinDto();
        disciplinDto.setTitle("Котовасия");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/adddiscipline", disciplinDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/listalldiscipline"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addDiscipline2() throws Exception {
        DisciplinDto disciplinDto = new DisciplinDto();
        disciplinDto.setTitle("История");
        Disciplin disciplin = new Disciplin();
        disciplin.setId(6L);
        disciplin.setTitle("История");
        Mockito.doReturn(disciplin)
                .when(disciplinRepository)
                .findDisciplinByTitle(disciplin.getTitle());
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/adddiscipline", disciplinDto))
                .andExpect(status().isOk())
                .andExpect(view().name("headteacher/viewAllDisciplineWithErrorDisciplineIsPresent"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addDisciplineValidateCheck() throws Exception {
        DisciplinDto disciplinDto = new DisciplinDto();
        disciplinDto.setTitle("114442141");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/adddiscipline", disciplinDto))
                .andExpect(status().isOk())
                .andExpect(view().name("headteacher/viewAllDiscipline"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void getAllTeachers() throws Exception {
        mockMvc.perform(get("/headteacher/listallteachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewAllTeachers"))
                .andExpect(content().string(containsString("Список преподавателей школы")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void getAllClassTitle() throws Exception {
        mockMvc.perform(get("/headteacher/listallclasstitle"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewAllClassTitle"))
                .andExpect(content().string(containsString("Список классов")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addClassTitleGet() throws Exception {
        mockMvc.perform(get("/headteacher/addclasstitle"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddClassTitle"))
                .andExpect(content().string(containsString("Добавление нового класса")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addClassTitlePostValidateCheck() throws Exception {
        ClassTitle classTitle = new ClassTitle();
        classTitle.setTitle("114442141");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/addclasstitle", classTitle))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddClassTitle"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addClassTitlePostValidateIncorrectSymbol() throws Exception {
        ClassTitle classTitle = new ClassTitle();
        classTitle.setTitle("1А1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/addclasstitle", classTitle))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddClassTitleWithErrorIncorrectSymbol"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addClassTitlePostValidateClassTitleIsPresent() throws Exception {
        ClassTitle classTitle = new ClassTitle();
        classTitle.setId(2L);
        classTitle.setTitle("11Б");
        Mockito.doReturn(classTitle)
                .when(classTitleRepository)
                .findClassTitleByTitle(classTitle.getTitle());
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/addclasstitle", classTitle))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddClassTitleWithErrorClassTitleIsPresent"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addClassTitlePostValidateClass() throws Exception {
        ClassTitle classTitle = new ClassTitle();
        classTitle.setId(2L);
        classTitle.setTitle("11Б");
        ClassTitle classTitle1 = new ClassTitle();
        classTitle1.setTitle("11В");
        Mockito.doReturn(classTitle)
                .when(classTitleRepository)
                .findClassTitleByTitle(classTitle.getTitle());
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/addclasstitle", classTitle1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/listallclasstitle"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void showFormAddTeacher() throws Exception {
        mockMvc.perform(get("/headteacher/addteacher"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddTeacher"))
                .andExpect(content().string(containsString("Добавление нового преподавателя")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addTeacherValidateCheck() throws Exception {
        RegistrationDataDto registrationDataDto = new RegistrationDataDto();
        registrationDataDto.setLastName("Иванов");
        registrationDataDto.setFirstName("Герасим");
        registrationDataDto.setUserName("gerych");
        registrationDataDto.setPassword("123");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/addteacher", registrationDataDto))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAddTeacher"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void showFormSetTeacherDiscipline() throws Exception {
        mockMvc.perform(get("/headteacher/setteacherdiscipline"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAppointTeacherToDiscipline"))
                .andExpect(content().string(containsString("Назначить дисциплину преподавателю")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void appointTeacherDiscipline() throws Exception {
        AppointDisciplinForTeacherDto appointDisciplinForTeacherDto = new AppointDisciplinForTeacherDto();
        appointDisciplinForTeacherDto.setDisciplinId(1L);
        appointDisciplinForTeacherDto.setTeacherId(1L);
        Disciplin disciplin = new Disciplin();
        disciplin.setId(1L);
        disciplin.setTitle("Алгебра");
        Optional<Disciplin> disciplin1 = Optional.of(disciplin);
        Mockito.doReturn(disciplin1)
                .when(disciplinRepository)
                .findById(disciplin.getId());
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Сергей");
        teacher.setLastName("Сергеев");
        Optional<Teacher> teacher1 = Optional.of(teacher);
        Mockito.doReturn(teacher1)
                .when(teacherRepository)
                .findById(teacher.getId());
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/setteacherdiscipline", appointDisciplinForTeacherDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/listallteachers"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void appointTeacherClass() throws Exception {
        AppointDisciplinForTeacherDto appointDisciplinForTeacherDto = new AppointDisciplinForTeacherDto();
        appointDisciplinForTeacherDto.setDisciplinId(1L);
        appointDisciplinForTeacherDto.setTeacherId(1L);
        Disciplin disciplin = new Disciplin();
        disciplin.setId(1L);
        disciplin.setTitle("Алгебра");
        Optional<Disciplin> disciplin1 = Optional.of(disciplin);
        Mockito.doReturn(disciplin1)
                .when(disciplinRepository)
                .findById(disciplin.getId());
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Сергей");
        teacher.setLastName("Сергеев");
        Optional<Teacher> teacher1 = Optional.of(teacher);
        Mockito.doReturn(teacher1)
                .when(teacherRepository)
                .findById(teacher.getId());
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/setteacherdiscipline", appointDisciplinForTeacherDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/listallteachers"));
    }

    @GetMapping("/setteacherclassgroup")
    public String showFormSetTeacherClassGroup(Model model) throws Exception {
        mockMvc.perform(get("/headteacher/setteacherdiscipline"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAppointTeacherToDiscipline"))
                .andExpect(content().string(containsString("Назначить преподавателю класс")));
        return "headteacher/formAppointTeacherToClass";
    }

    @Test
    @WithUserDetails("petrovna")
    public void appointTeacherClassGroup() throws Exception {
        AppointTeacherToClassDto appointTeacherToClassDto = new AppointTeacherToClassDto();
        appointTeacherToClassDto.setClassTitleId(1L);
        appointTeacherToClassDto.setTeacherId(1L);
        ClassTitle classTitle = new ClassTitle();
        classTitle.setTitle("11А");
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Сергей");
        teacher.setLastName("Сергеев");
        Mockito.doReturn(Optional.of(teacher))
                .when(teacherRepository)
                .findById(teacher.getId());
        Mockito.doReturn(classTitle)
                .when(classTitleRepository)
                .findClassTitleByTeacher(teacher);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/setteacherclassgroup", appointTeacherToClassDto))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAppointTeacherToClassWithErrorClassAlreadyAppoint"));
        ProfileTeacher profileTeacher = new ProfileTeacher();
        Mockito.doReturn(null)
                .when(classTitleRepository)
                .findClassTitleByTeacher(teacher);
        Mockito.doReturn(null)
                .when(profileTeacherRepository)
                .findProfileTeacherByTeacher(teacher);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/setteacherclassgroup", appointTeacherToClassDto))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/formAppointTeacherToClassWithErrorNoDisciplineAppointByTeacher"));
        Mockito.doReturn(profileTeacher)
                .when(profileTeacherRepository)
                .findProfileTeacherByTeacher(teacher);
        Mockito.doReturn(classTitle)
                .when(classTitleRepository)
                .findClassTitleById(1L);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/setteacherclassgroup", appointTeacherToClassDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/listallteachers"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void findTimeTable() throws Exception {
        mockMvc.perform(get("/headteacher/timetable/0/0"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewTimeTableForHeadTeacher"))
                .andExpect(content().string(containsString("Расписание уроков")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void removeItemFromTimeTable() throws Exception {
        mockMvc.perform(get("/headteacher/removefromtimetable?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/timetable/0/0"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addItemToTimeTable() throws Exception {
        CreateTimeTableDto createTimeTableDto = new CreateTimeTableDto();
        createTimeTableDto.setClassTitleId(1);
        createTimeTableDto.setNumberOfDay(1);
        createTimeTableDto.setLecturesHallId(1);
        createTimeTableDto.setNumberLessonId(1);
        createTimeTableDto.setDisciplinId(1);
        String result = "ok";
        Mockito.doReturn(result)
                .when(headTeacherService)
                .createToTimeTable(createTimeTableDto);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/additemtotimetable", createTimeTableDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/timetable/0/0"));
        result = "busyTeacher";
        Mockito.doReturn(result)
                .when(headTeacherService)
                .createToTimeTable(createTimeTableDto);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/additemtotimetable", createTimeTableDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/additemtotimetableWithError?error=" + result));
    }

    @Test
    @WithUserDetails("petrovna")
    public void addItemToTimeTableWithError() throws Exception {
        mockMvc.perform(get("/headteacher/additemtotimetableWithError?error=busyLesson"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewTimeTableForHeadTeacherWithErrorBusyLesson"));
        mockMvc.perform(get("/headteacher/additemtotimetableWithError?error=busyTeacher"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewTimeTableForHeadTeacherWithErrorBusyTeacher"));
        mockMvc.perform(get("/headteacher/additemtotimetableWithError?error=busyLecturesHall"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewTimeTableForHeadTeacherWithErrorBusyLecturesHall"));
        mockMvc.perform(get("/headteacher/additemtotimetableWithError?error=ok"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/timetable/0"));
    }

    @Test
    @WithUserDetails("petrovna")
    public void selectDayAndClassToTimeTable() throws Exception {
        Day day = new Day();
        day.setNumberDay("1");
        day.setNumberClass("1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/selectdayandclass", day))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/timetable/" + day.getNumberDay() + "/" + day.getNumberClass()));
    }

    @Test
    @WithUserDetails("petrovna")
    public void findClassJournalGet() throws Exception {
        mockMvc.perform(get("/headteacher/classjournal?classTitleId=0&disciplineId=0&studentId=0&teacherId=0&startDate=2023-01-01&endDate=2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(view().name("/headteacher/viewAllGrade"))
                .andExpect(content().string(containsString("Просмотр успеваемости")));
    }

    @Test
    @WithUserDetails("petrovna")
    public void selectItemFiltersInClassJournal() throws Exception {
        HeadCJFilter headCJFilter = HeadCJFilter.builder()
                .classTitleId("1")
                .disciplineId("1")
                .studentId("1")
                .teacherId("1")
                .startDate("2023-01-01")
                .endDate("2023-02-02")
                .build();
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/headteacher/classjournal", headCJFilter))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/headteacher/classjournal" + "?classTitleId=" + headCJFilter.getClassTitleId() +
                        "&disciplineId=" + headCJFilter.getDisciplineId() +
                        "&studentId=" + headCJFilter.getStudentId() +
                        "&teacherId=" + headCJFilter.getTeacherId() +
                        "&startDate=" + headCJFilter.getStartDate() +
                        "&endDate=" + headCJFilter.getEndDate()));
    }
}


