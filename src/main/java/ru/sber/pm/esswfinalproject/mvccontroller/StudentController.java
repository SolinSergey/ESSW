package ru.sber.pm.esswfinalproject.mvccontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.Student;
import ru.sber.pm.esswfinalproject.services.ClassJournalService;
import ru.sber.pm.esswfinalproject.services.StudentService;
import ru.sber.pm.esswfinalproject.services.TimeTableService;
import ru.sber.pm.esswfinalproject.utils.Day;
import ru.sber.pm.esswfinalproject.utils.StudentCJFilter;
import ru.sber.pm.esswfinalproject.utils.UserUtils;
import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final UserUtils userUtils;
    private final TimeTableService timeTableService;
    private final ClassJournalService classJournalService;

    @GetMapping("/getall")
    public String getAllStudents(Model model) {
        List<StudentDto> studentList = studentService.listAll();
        model.addAttribute("listStudents", studentList);
        return "/students/viewAllStudents";
    }

    @GetMapping("/getmyclass")
    public String findAllByStudent(Model model) {
        String username = userUtils.getUsernameForLoginUser();
        Student student = studentService.findByUserUsername(username);
        List<MyClassDto> studentDtoList = studentService.findAllByStudent(student);
        model.addAttribute("listMyClass", studentDtoList);
        return "/students/viewAllStudents";
    }

    @GetMapping("/gettimetablemyclass/{numberDay}")
    public String findTimeTableByStudent(Model model, @PathVariable int numberDay) {
        String username = userUtils.getUsernameForLoginUser();
        Student student = studentService.findByUserUsername(username);
        Day day = new Day();
        day.setNumberDay(String.valueOf(numberDay));
        model.addAttribute("day", day);
        List<TimeTableDto> timeTableDtos = timeTableService.findTimetablebyStudentUsername(username, numberDay);
        model.addAttribute("timeTable", timeTableDtos);
        return "/students/viewTimeTableForStudent";
    }

    @PostMapping(path="/selectday",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String selectDay(@ModelAttribute Day day) {
        return "redirect:/student/gettimetablemyclass/" + day.getNumberDay();
    }

    @GetMapping("/mygrade")
    public String findGradeStudent(Model model, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        String username = userUtils.getUsernameForLoginUser();
        Student student = studentService.findByUserUsername(username);
        List<ClassJournalDtoWithString> classJournalList = classJournalService.findClassJournalByStudentUserName(student, startDate, endDate);
        StudentCJFilter studentCJFilter = new StudentCJFilter();
        studentCJFilter.setStartDate(startDate);
        studentCJFilter.setEndDate(endDate);
        model.addAttribute("filterData", studentCJFilter);
        model.addAttribute("gradeList", classJournalList);
        return "/students/viewGradeStudent";
    }

    @PostMapping(path="/mygrade",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String selectDay(@ModelAttribute StudentCJFilter studentCJFilter) {
        return ("redirect:/student/mygrade" + "?startDate=" + studentCJFilter.getStartDate() + "&endDate=" + studentCJFilter.getEndDate());
    }

    @GetMapping("/myprofile")
    public String myProfile(Model model) {
        String username = userUtils.getUsernameForLoginUser();
        Student student = studentService.findByUserUsername(username);
        StudentProfileDto studentProfileDto = studentService.prepareStudentProfile(student);
        model.addAttribute("studentProfile", studentProfileDto);
        return "/students/myProfile";
    }
}
