package ru.sber.pm.esswfinalproject;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.sber.pm.esswfinalproject.utils.Day;
import ru.sber.pm.esswfinalproject.utils.StudentCJFilter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("application.yaml")
public class StudentControllerTest {

    @Autowired
    private
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("nata")
    public void getAllStudents() throws Exception {
        mockMvc.perform(get("/student/getall"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("/students/viewAllStudents"))
                .andExpect(content().string(containsString("Список одноклассников")));
    }

    @Test
    @WithUserDetails("nata")
    public void findAllByStudent() throws Exception {
        mockMvc.perform(get("/student/getmyclass"))
                .andExpect(status().isOk())
                .andExpect(view().name("/students/viewAllStudents"))
                .andExpect(content().string(containsString("Список одноклассников")));
    }

    @Test
    @WithUserDetails("nata")
    public void findTimeTableByStudent() throws Exception {
        mockMvc.perform(get("/student/gettimetablemyclass/0"))
                .andExpect(status().isOk())
                .andExpect(view().name("/students/viewTimeTableForStudent"))
                .andExpect(content().string(containsString("Расписание уроков")));
    }


    @Test
    @WithUserDetails("nata")
    public void selectDay() throws Exception {
        Day day = new Day();
        day.setNumberDay("1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/student/selectday", day))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(view().name("redirect:/student/gettimetablemyclass/" + day.getNumberDay()));
    }

    @Test
    @WithUserDetails("nata")
    public void findGradeStudentPost() throws Exception {
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";
        StudentCJFilter studentCJFilter = new StudentCJFilter();
        studentCJFilter.setStartDate(startDate);
        studentCJFilter.setEndDate(endDate);
        studentCJFilter.setStartDate(startDate);
        studentCJFilter.setEndDate(endDate);
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/student/mygrade", studentCJFilter))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/student/mygrade?startDate=" + studentCJFilter.getStartDate() + "&endDate=" + studentCJFilter.getEndDate()));
    }

    @Test
    @WithUserDetails("nata")
    public void findGradeStudent() throws Exception {
        mockMvc.perform(get("/student/mygrade?startDate=2023-01-01&endDate=2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(view().name("/students/viewGradeStudent"))
                .andExpect(content().string(containsString("Мои оценки")));
    }

    @Test
    @WithUserDetails("nata")
    public void myProfile() throws Exception {
        mockMvc.perform(get("/student/myprofile"))
                .andExpect(status().isOk())
                .andExpect(view().name("/students/myProfile"))
                .andExpect(content().string(containsString("Мой профиль")));
    }
}
