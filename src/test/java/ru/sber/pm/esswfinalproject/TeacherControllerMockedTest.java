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
import org.springframework.web.context.WebApplicationContext;
import ru.sber.pm.esswfinalproject.entities.Teacher;
import ru.sber.pm.esswfinalproject.services.ProfileTeacherService;
import ru.sber.pm.esswfinalproject.services.TimeTableService;
import ru.sber.pm.esswfinalproject.utils.ClassUtil;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("application.yaml")
public class TeacherControllerMockedTest {
    @Autowired
    private
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProfileTeacherService profileTeacherService;

    @MockBean
    TimeTableService timeTableService;

    @Test
    @WithUserDetails("serg")
    public void myProfile2() throws Exception {
        Teacher teacher = new Teacher();
        Mockito.doReturn(null)
                .when(profileTeacherService)
                .prepareTeacherProfile(Mockito.any());
        mockMvc.perform(get("/teacher/myprofile"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/viewMyProfileWithError"));
    }

    @Test
    @WithUserDetails("serg")
    public void findTimeTableMyClass() throws Exception {
        Mockito.doReturn(null)
                .when(timeTableService)
                .findTimeTableMyClassByTeacherUserName("serg", 0);
        mockMvc.perform(get("/teacher/myclasstimetable/0"))
                .andExpect(status().isOk())
                .andExpect(view().name("/teacher/viewTimeTableForClassWithError"));
    }

    @Test
    @WithUserDetails("serg")
    public void callAddGradeForm() throws Exception {
        Mockito.doReturn(null)
                .when(profileTeacherService)
                .findProfileTeacherByTeacher((Mockito.any()));
        ClassUtil classUtil = new ClassUtil();
        classUtil.setNumberClass("1");
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/teacher/selectClassForFormGrade", classUtil))
                .andExpect(status().isOk())
                .andExpect(view().name("teacher/formAddGradeWhithError"));
    }
}
