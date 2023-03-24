package ru.sber.pm.esswfinalproject;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.services.*;
import ru.sber.pm.esswfinalproject.utils.ClassCjFilter;
import ru.sber.pm.esswfinalproject.utils.ClassUtil;
import ru.sber.pm.esswfinalproject.utils.Day;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("application.yaml")
public class TeacherControllerTest {
    @Autowired
    private
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithUserDetails("serg")
    public void myClass() throws Exception {
        mockMvc.perform(get("/teacher/myclass"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/viewAllStudents"))
                .andExpect(content().string(containsString("Мой класс")));
    }

    @Test
    @WithUserDetails("serg")
    public void addStudentValidateCheck() throws Exception {
        RegistrationDataDto registrationDataDto = new RegistrationDataDto();
        registrationDataDto.setLastName("Иванов");
        registrationDataDto.setFirstName("Герасим");
        registrationDataDto.setUserName("gerych");
        registrationDataDto.setPassword("123");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/addstudent", registrationDataDto))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/formAddStudentsInClass"));
    }

    @Test
    @WithUserDetails("serg")
    public void addStudentValidateStudentUserIsPresent() throws Exception {
        RegistrationDataDto registrationDataDto = new RegistrationDataDto();
        registrationDataDto.setLastName("Иванов");
        registrationDataDto.setFirstName("Герасим");
        registrationDataDto.setUserName("serg");
        registrationDataDto.setPassword("As123");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/addstudent", registrationDataDto))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/formAddStudentWithErrorUsernameIsPresent"));
    }

    @Test
    @WithUserDetails("serg")
    public void showFormAddStudent() throws Exception {
        mockMvc.perform(get("/teacher/addstudent"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("teacher/formAddStudentsInClass"))
                .andExpect(content().string(containsString("Добавление нового ученика в класс")));
    }

    @Test
    @WithUserDetails("serg")//
    public void myProfile1() throws Exception {
        ProfileTeacherService profileTeacherService1;
        mockMvc.perform(get("/teacher/myprofile"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/teacher/myProfile"));
    }


    @Test
    @WithUserDetails("serg")
    public void findTimetablebyTeachert() throws Exception {
        mockMvc.perform(get("/teacher/mytimetable/0"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/teacher/viewTimeTableForTeacher"));
    }

    @Test
    @WithUserDetails("serg")
    public void selectDay() throws Exception {
        Day day = new Day();
        day.setNumberDay("0");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/selectday", day))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/teacher/mytimetable/" + day.getNumberDay()));
    }

    @Test
    @WithUserDetails("serg")
    public void findTimeTableMyClass() throws Exception {
        mockMvc.perform(get("/teacher/myclasstimetable/0"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/teacher/viewTimeTableMyClass"));
    }

    @Test
    @WithUserDetails("serg")
    public void selectDate() throws Exception {
        Day day = new Day();
        day.setNumberDay("0");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/selectdate", day))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/teacher/myclasstimetable/" + day.getNumberDay()));
    }

    @Test
    @WithUserDetails("serg")
    public void selectClassForm() throws Exception {
        mockMvc.perform(get("/teacher/setgradetoclassjournal"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/teacher/formSelectClass"));
    }

    @Test
    @WithUserDetails("serg")
    public void callAddGradeForm() throws Exception {
        ClassUtil classUtil = new ClassUtil();
        classUtil.setNumberClass("1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/selectClassForFormGrade", classUtil))
                .andExpect(status().isOk())
                .andExpect(view().name("/teacher/formAddGrade"));
    }

    @Test
    @WithUserDetails("serg")
    public void addItemToTimeTableValidateData() throws Exception {
        ClassJournalDtoWithString classJournalDtoWithString = new ClassJournalDtoWithString();
        classJournalDtoWithString.setStudentLastName("Прекрасная");
        classJournalDtoWithString.setClassTitle("11А");
        classJournalDtoWithString.setStudentFirstName("Наталья");
        classJournalDtoWithString.setDescription("dfhgdfghdfgd");
        classJournalDtoWithString.setGrade(2);
        classJournalDtoWithString.setDateGrade("2023-02-28");
        classJournalDtoWithString.setStudentId(1L);
        classJournalDtoWithString.setClassId("1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/setgradetoclassjournal", classJournalDtoWithString))
                .andExpect(status().isOk())
                .andExpect(view().name("/teacher/formAddGrade"));
    }

    @Test
    @WithUserDetails("serg")
    public void findGradeMyClass() throws Exception {
        mockMvc.perform(get("/teacher/classjournal?studentId=0&classId=0&startDate=2023-01-01&endDate=2023-12-01"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/teacher/viewAllGrade"));
    }

    @Test
    @WithUserDetails("serg")
    public void selectDay1() throws Exception {
        ClassCjFilter classCjFilter = ClassCjFilter.builder()
                .classId("0")
                .teacherId("0")
                .studentId(0L)
                .className("11А")
                .classTitleId("0")
                .disciplineId("0")
                .startDate("2023-01-01")
                .endDate("2023-12-31")
                .build();
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/classjournal", classCjFilter))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/teacher/classjournal" + "?studentId=" + classCjFilter.getStudentId() + "&classId=" + classCjFilter.getClassId() + "&startDate=" + classCjFilter.getStartDate() + "&endDate=" + classCjFilter.getEndDate()));
    }
}