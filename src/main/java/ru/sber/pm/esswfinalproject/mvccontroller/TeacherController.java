package ru.sber.pm.esswfinalproject.mvccontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.repositories.ClassTitleRepository;
import ru.sber.pm.esswfinalproject.services.*;
import ru.sber.pm.esswfinalproject.utils.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final ClassTitleService classTitleService;
    private final ClassGroupService classGroupService;
    private final ProfileTeacherService profileTeacherService;
    private final UserUtils userUtils;
    private final TimeTableService timeTableService;
    private final ClassJournalService classJournalService;
    private final StudentService studentService;
    private final UserService userService;

    @GetMapping("/myclass")
    public String getAllStudents(Model model) {
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        if (classTitle != null) {
            List<MyClassDto> classGroup = classGroupService.findClassGroupByClassTitles(classTitle);
            model.addAttribute("listMyClass", classGroup);
            return "teacher/viewAllStudents";
        }
        return "teacher/viewAllStudentWithError";
    }

    @PostMapping("/addstudent")
    public String addStudent(@Valid @ModelAttribute("registrationData") RegistrationDataDto registrationDataDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationData", registrationDataDto);
            return "teacher/formAddStudentsInClass";
        }
        Optional<User> user = userService.findByUsername(registrationDataDto.getUserName());
        if (user.isPresent()) {
            model.addAttribute("registrationData", registrationDataDto);
            return "teacher/formAddStudentWithErrorUsernameIsPresent";
        }
        registrationDataDto.setRole("ROLE_STUDENT");
        String resultOperation = teacherService.createToStudent(registrationDataDto);
        if (resultOperation.equals("ok")) {
            return "redirect:/teacher/myclass";
        }
        return "teacher/viewAddStudentInClassWithErrorTeacherWithoutClass";
    }


    @GetMapping("/addstudent")
    public String showFormAddStudent(Model model) {
        Teacher teacher = teacherService.findTeacherByUsername(userUtils.getUsernameForLoginUser());
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        if (classTitle != null) {
            RegistrationDataDto registrationDataDto = new RegistrationDataDto();
            model.addAttribute("registrationData", registrationDataDto);
            return "teacher/formAddStudentsInClass";
        }
        return "teacher/viewAddStudentInClassWithErrorTeacherWithoutClass";
    }

    @GetMapping("/myprofile")
    public String myProfile(Model model) {
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        TeacherProfileDto teacherProfileDto = profileTeacherService.prepareTeacherProfile(teacher);
        if (teacherProfileDto != null) {
            model.addAttribute("teacherProfile", teacherProfileDto);
            return "/teacher/myProfile";
        }
        return "teacher/viewMyProfileWithError";
    }

    @GetMapping("/mytimetable/{numberDay}")
    public String findTimetablebyTeachert(Model model, @PathVariable int numberDay) {
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        Day day = new Day();
        day.setNumberDay(String.valueOf(numberDay));
        model.addAttribute("day", day);
        List<TimeTableDto> timeTableDtos = timeTableService.findTimeTableByTeacherUserName(username, numberDay);
        model.addAttribute("timeTable", timeTableDtos);
        return "/teacher/viewTimeTableForTeacher";
    }


    @PostMapping(path = "/selectday", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String selectDay(@ModelAttribute Day day, Model model) {
        return "redirect:/teacher/mytimetable/" + day.getNumberDay();
    }

    @GetMapping("/myclasstimetable/{numberDay}")
    public String findTimeTableMyClass(Model model, @PathVariable int numberDay) {
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        Day day = new Day();
        day.setNumberDay(String.valueOf(numberDay));
        model.addAttribute("day", day);
        List<TimeTableDto> timeTableDtos = timeTableService.findTimeTableMyClassByTeacherUserName(username, numberDay);
        if (timeTableDtos != null) {
            model.addAttribute("timeTable", timeTableDtos);
            return "/teacher/viewTimeTableMyClass";
        }
        return "/teacher/viewTimeTableForClassWithError";
    }

    @PostMapping("/selectdate")
    public String selectDate(@ModelAttribute Day day, Model model) {
        return "redirect:/teacher/myclasstimetable/" + day.getNumberDay();
    }

    @GetMapping("/setgradetoclassjournal")
    public String selectClassForm(Model model) {
        List<ClassTitleDto> classTitleList = classTitleService.listAll();
        ClassUtil classUtil = new ClassUtil();
        model.addAttribute("classTitleList", classTitleList);
        model.addAttribute("classNumber", classUtil);
        return "/teacher/formSelectClass";
    }

    @PostMapping("/selectClassForFormGrade")
    public String callAddGradeForm(@ModelAttribute ClassUtil classUtil, Model model) {
        List<MyClassDto> studentDtos = classGroupService.findByClassTitlesId(Long.valueOf(classUtil.getNumberClass()));
        ClassJournalDtoWithString classJournalDtoWithString = new ClassJournalDtoWithString();
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(teacher);
        if (profileTeacher == null || profileTeacher.getDisciplin() == null) {
            return "teacher/formAddGradeWhithError";
        }
        classJournalDtoWithString.setDisciplinTitle(profileTeacher.getDisciplin().getTitle());
        classJournalDtoWithString.setClassId(classUtil.getNumberClass());
        ClassTitle classTitle = classTitleService.findClassTitleById(Long.valueOf(classUtil.getNumberClass()));
        classJournalDtoWithString.setClassTitle(classTitle.getTitle());
        model.addAttribute("studentList", studentDtos);
        model.addAttribute("gradeData", classJournalDtoWithString);
        return "/teacher/formAddGrade";
    }

    @PostMapping("/setgradetoclassjournal")
    public String addItemToTimeTable(@Valid @ModelAttribute("gradeData") ClassJournalDtoWithString
                                             classJournalDtoWithString, BindingResult bindingResult, Model
                                             model) throws ParseException {
        String classId = classJournalDtoWithString.getClassId();
        if (bindingResult.hasErrors()) {
            List<MyClassDto> studentDtos = classGroupService.findByClassTitlesId(Long.valueOf(classId));
            String username = userUtils.getUsernameForLoginUser();
            Teacher teacher = teacherService.findTeacherByUsername(username);
            ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(teacher);
            classJournalDtoWithString.setDisciplinTitle(profileTeacher.getDisciplin().getTitle());
            model.addAttribute("studentList", studentDtos);
            model.addAttribute("gradeData", classJournalDtoWithString);
            return "/teacher/formAddGrade";
        }
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(teacher);
        classJournalDtoWithString.setDisciplinTitle(profileTeacher.getDisciplin().getTitle());
        teacherService.addGradeToStudent(classJournalDtoWithString);
        return "redirect:/teacher/classjournal?studentId=0&classId=0&startDate=2023-01-01&endDate=2023-12-01";
    }

    @GetMapping("/classjournal")
    public String findGradeMyClass(Model model, @RequestParam String studentId, @RequestParam String
            classId, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        String username = userUtils.getUsernameForLoginUser();
        Teacher teacher = teacherService.findTeacherByUsername(username);
        List<ClassTitleDto> classTitles = classTitleService.listAll();
        List<StudentDto> studentDtoList = studentService.listAll();
        List<ClassJournalDtoWithString> classJournalList =
                classJournalService.findClassJournalByClassTitleIdAndStudentIdAndDateBetween(studentId, classId, startDate, endDate);
        ClassCjFilter classCjFilter = ClassCjFilter.builder()
                .startDate(startDate)
                .endDate(endDate)
                .classId(classId)
                .studentId(Long.valueOf(studentId))
                .build();
        model.addAttribute("studentList", studentDtoList);
        model.addAttribute("classTitle", classTitles);
        model.addAttribute("filterData", classCjFilter);
        model.addAttribute("gradeList", classJournalList);
        return "/teacher/viewAllGrade";
    }

    @PostMapping("/classjournal")
    public String selectDay(@ModelAttribute ClassCjFilter classCjFilter, Model model) {
        return "redirect:/teacher/classjournal" + "?studentId=" + classCjFilter.getStudentId() + "&classId=" + classCjFilter.getClassId() + "&startDate=" + classCjFilter.getStartDate() + "&endDate=" + classCjFilter.getEndDate();
    }
}