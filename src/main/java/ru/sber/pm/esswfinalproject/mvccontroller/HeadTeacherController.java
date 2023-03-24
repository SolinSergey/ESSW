package ru.sber.pm.esswfinalproject.mvccontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.pm.esswfinalproject.dto.*;
import ru.sber.pm.esswfinalproject.entities.*;
import ru.sber.pm.esswfinalproject.exceptions.TeacherNotFoundException;
import ru.sber.pm.esswfinalproject.services.*;
import ru.sber.pm.esswfinalproject.utils.Day;
import ru.sber.pm.esswfinalproject.utils.HeadCJFilter;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/headteacher")
public class HeadTeacherController {

    private final TeacherService teacherService;

    private final ClassTitleService classTitleService;

    private final DisciplinService disciplinService;

    private final HeadTeacherService headTeacherService;

    private final TimeTableService timeTableService;

    private final LecturesHallService lecturesHallService;

    private final ClassJournalService classJournalService;

    private final StudentService studentService;

    private final ProfileTeacherService profileTeacherService;

    private final UserService userService;

    @GetMapping("/listalldiscipline")
    public String getAllDiscipline(Model model) {
        List<DisciplinDto> disciplinDtoList = disciplinService.listAll();
        DisciplinDto disciplineDto = new DisciplinDto();
        model.addAttribute("discipline", disciplineDto);
        model.addAttribute("disciplineList", disciplinDtoList);
        return "/headteacher/viewAllDiscipline";
    }

    @PostMapping("/adddiscipline")
    public String addDiscipline(@Valid @ModelAttribute("discipline") DisciplinDto disciplinDto, BindingResult bindingResult, Model model) {
        List<DisciplinDto> disciplinDtoList = disciplinService.listAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("discipline", disciplinDto);
            model.addAttribute("disciplineList", disciplinDtoList);
            return "headteacher/viewAllDiscipline";
        }
        Disciplin disciplin = disciplinService.findDisciplinByTitle(disciplinDto.getTitle());
        if (disciplin != null) {
            model.addAttribute("discipline", disciplinDto);
            model.addAttribute("disciplineList", disciplinDtoList);
            return "headteacher/viewAllDisciplineWithErrorDisciplineIsPresent";
        }
        disciplinService.addDiscipline(disciplinDto);
        return "redirect:/headteacher/listalldiscipline";
    }

    @GetMapping("/listallteachers")
    public String getAllTeachers(Model model) {
        List<TeacherDto> teacherList = headTeacherService.listAllTeacher();
        model.addAttribute("teacherList", teacherList);
        return "/headteacher/viewAllTeachers";
    }

    @GetMapping("/listallclasstitle")
    public String getAllClassTitle(Model model) {
        List<ClassTitleDto> classTitleDtoList = classTitleService.listAll();
        model.addAttribute("classTitleList", classTitleDtoList);
        return "/headteacher/viewAllClassTitle";
    }

    @GetMapping("/addclasstitle")
    public String addClassTitle(Model model) {
        ClassTitle classTitle = new ClassTitle();
        model.addAttribute("classTitle", classTitle);
        return "/headteacher/formAddClassTitle";
    }

    @PostMapping("/addclasstitle")
    public String addClassTitle(@Valid @ModelAttribute("classTitle") ClassTitle classTitle, BindingResult bindingResult, Model model) {
        model.addAttribute("classTitle", classTitle);
        if (bindingResult.hasErrors()) {
            return "/headteacher/formAddClassTitle";
        }
        if (!classTitleService.isCheckNewTitle(classTitle.getTitle())) {
            return "/headteacher/formAddClassTitleWithErrorIncorrectSymbol";
        }
        if (!classTitleService.isClassTitlePresent(classTitle.getTitle())) {
            classTitleService.addClassTitle(classTitle);
        } else {
            return "/headteacher/formAddClassTitleWithErrorClassTitleIsPresent";
        }
        return "redirect:/headteacher/listallclasstitle";
    }

    @GetMapping("/addteacher")
    public String showFormAddTeacher(Model model) {
        RegistrationDataDto registrationDataDto = new RegistrationDataDto();
        model.addAttribute("registrationData", registrationDataDto);
        return "/headteacher/formAddTeacher";
    }

    @PostMapping("/addteacher")
    public String addTeacher(@Valid @ModelAttribute("registrationData") RegistrationDataDto registrationDataDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationData", registrationDataDto);
            return "/headteacher/formAddTeacher";
        }
        Optional<User> user = userService.findByUsername(registrationDataDto.getUserName());
        if (user.isPresent()) {
            model.addAttribute("registrationData", registrationDataDto);
            return "/headteacher/formAddTeacherWithErrorUsernameIsPresent";
        }
        registrationDataDto.setRole("ROLE_TEACHER");
        headTeacherService.createToTeacher(registrationDataDto);
        return "redirect:/headteacher/listallteachers";
    }

    @GetMapping("/setteacherdiscipline")
    public String showFormSetTeacherDiscipline(Model model) {
        List<TeacherDto> teacherDtoList = headTeacherService.listAllTeacher();
        List<DisciplinDto> disciplinDtoList = disciplinService.listAll();
        AppointDisciplinForTeacherDto appointDisciplinForTeacherDto = new AppointDisciplinForTeacherDto();
        model.addAttribute("teacherList", teacherDtoList);
        model.addAttribute("disciplineList", disciplinDtoList);
        model.addAttribute("appointdisciplinforteacher", appointDisciplinForTeacherDto);
        return "/headteacher/formAppointTeacherToDiscipline";
    }

    @PostMapping("/setteacherdiscipline")
    public String appointTeacherDiscipline(@ModelAttribute AppointDisciplinForTeacherDto appointDisciplinForTeacherDto, Model model) {
        headTeacherService.appointDisciplinToTeacher(appointDisciplinForTeacherDto.getDisciplinId(), appointDisciplinForTeacherDto.getTeacherId());
        return "redirect:/headteacher/listallteachers";
    }

    @GetMapping("/setteacherclassgroup")
    public String showFormSetTeacherClassGroup(Model model) {
        List<TeacherDto> teacherDtoList = headTeacherService.listAllTeacher();
        List<ClassTitleDto> classTitleDtoList = classTitleService.listAll();
        AppointTeacherToClassDto appointTeacherToClassDto = new AppointTeacherToClassDto();
        model.addAttribute("teacherList", teacherDtoList);
        model.addAttribute("classTitleList", classTitleDtoList);
        model.addAttribute("appointTeacherToClass", appointTeacherToClassDto);
        return "headteacher/formAppointTeacherToClass";
    }

    @PostMapping("/setteacherclassgroup")
    public String appointTeacherClassGroup(@ModelAttribute AppointTeacherToClassDto appointTeacherToClassDto, Model model) {
        Teacher teacher = teacherService.findById(appointTeacherToClassDto.getTeacherId()).orElseThrow(() -> new TeacherNotFoundException("Преподаватель с id:" + appointTeacherToClassDto.getTeacherId() + " не найден"));
        ClassTitle classTitle = classTitleService.findClassTitleByTeacher(teacher);
        if (classTitle != null) {
            List<TeacherDto> teacherDtoList = headTeacherService.listAllTeacher();
            List<ClassTitleDto> classTitleDtoList = classTitleService.listAll();
            model.addAttribute("teacherList", teacherDtoList);
            model.addAttribute("classTitleList", classTitleDtoList);
            model.addAttribute("appointTeacherToClass", appointTeacherToClassDto);
            return "/headteacher/formAppointTeacherToClassWithErrorClassAlreadyAppoint";
        } else {
            ProfileTeacher profileTeacher = profileTeacherService.findProfileTeacherByTeacher(teacher);
            if (profileTeacher == null) {
                List<TeacherDto> teacherDtoList = headTeacherService.listAllTeacher();
                List<ClassTitleDto> classTitleDtoList = classTitleService.listAll();
                model.addAttribute("teacherList", teacherDtoList);
                model.addAttribute("classTitleList", classTitleDtoList);
                model.addAttribute("appointTeacherToClass", appointTeacherToClassDto);
                return "/headteacher/formAppointTeacherToClassWithErrorNoDisciplineAppointByTeacher";
            }
        }
        headTeacherService.appointTeacherToAdvisorForClassGroup(appointTeacherToClassDto.getTeacherId(), appointTeacherToClassDto.getClassTitleId());
        return "redirect:/headteacher/listallteachers";
    }

    @GetMapping("/timetable/{numberDay}/{idclass}")
    public String findTimeTable(Model model, @PathVariable int numberDay, @PathVariable Long idclass) {
        Day day = new Day();
        day.setNumberDay(String.valueOf(numberDay));
        day.setNumberClass(String.valueOf(idclass));
        List<ClassTitleDto> classTitleList = classTitleService.listAll();
        List<TimeTableDto> timeTableDtos = timeTableService.findTimetableByClassTitleId(idclass, numberDay);
        List<DisciplinDto> disciplinDtoList = disciplinService.listAll();
        List<LecturesHallDto> lecturesHallsDtoList = lecturesHallService.listAll();
        CreateTimeTableDto createTimeTableDto = new CreateTimeTableDto();
        model.addAttribute("timeTable", timeTableDtos);
        model.addAttribute("classTitleList", classTitleList);
        model.addAttribute("day", day);
        model.addAttribute("disciplineList", disciplinDtoList);
        model.addAttribute("lecturesHallsList", lecturesHallsDtoList);
        model.addAttribute("timetableData", createTimeTableDto);
        return "/headteacher/viewTimeTableForHeadTeacher";
    }

    @GetMapping("/removefromtimetable")
    public String removeItemFromTimeTable(Model model, @RequestParam Long id) {
        timeTableService.delete(id);
        return "redirect:/headteacher/timetable/0/0";
    }

    @PostMapping("/additemtotimetable")
    public String addItemToTimeTable(@ModelAttribute CreateTimeTableDto createTimeTableDto, Model model) {
        String resultOperation = headTeacherService.createToTimeTable(createTimeTableDto);
        if (!resultOperation.equals("ok")) {
            return "redirect:/headteacher/additemtotimetableWithError?error=" + resultOperation;
        }
        return "redirect:/headteacher/timetable/0/0";
    }

    @GetMapping("/additemtotimetableWithError")
    public String removeItemFromTimeTable(Model model, @RequestParam String error) {
        if (error.equals("busyLesson")) {
            return "/headteacher/viewTimeTableForHeadTeacherWithErrorBusyLesson";
        } else if (error.equals("busyTeacher")) {
            return "/headteacher/viewTimeTableForHeadTeacherWithErrorBusyTeacher";
        } else if (error.equals("busyLecturesHall")) {
            return "/headteacher/viewTimeTableForHeadTeacherWithErrorBusyLecturesHall";
        } else {
            return "redirect:/headteacher/timetable/0";
        }
    }


    @PostMapping("/selectdayandclass")
    public String selectDayAndClassToTimeTable(@ModelAttribute Day day, Model model) {
        return "redirect:/headteacher/timetable/" + day.getNumberDay() + "/" + day.getNumberClass();
    }

    @GetMapping("/classjournal")
    public String findClassJournal(Model model, @RequestParam Long classTitleId,
                                   @RequestParam Long disciplineId,
                                   @RequestParam Long studentId,
                                   @RequestParam Long teacherId,
                                   @RequestParam String startDate,
                                   @RequestParam String endDate) throws ParseException {
        List<ClassJournalDtoWithString> classJournalDtoWithStringList = classJournalService.listAllWithFilterSelect(classTitleId, disciplineId, studentId, teacherId, startDate, endDate);
        List<ClassTitleDto> classTitleDtoList = classTitleService.listAll();
        List<DisciplinDto> disciplinDtoList = disciplinService.listAll();
        List<StudentDto> studentDtoList = studentService.listAll();
        List<TeacherDto> teacherDtoList = teacherService.listAll();
        HeadCJFilter headCJFilter = HeadCJFilter.builder()
                .classTitleId(String.valueOf(classTitleId))
                .disciplineId(String.valueOf(disciplineId))
                .studentId(String.valueOf(studentId))
                .teacherId(String.valueOf(teacherId))
                .startDate(startDate)
                .endDate(endDate)
                .build();
        model.addAttribute("classJournalList", classJournalDtoWithStringList);
        model.addAttribute("classTitleList", classTitleDtoList);
        model.addAttribute("disciplineList", disciplinDtoList);
        model.addAttribute("studentList", studentDtoList);
        model.addAttribute("teacherList", teacherDtoList);
        model.addAttribute("filterData", headCJFilter);
        return "/headteacher/viewAllGrade";
    }

    @PostMapping("/classjournal")
    public String selectItemFiltersInClassJournal(@ModelAttribute HeadCJFilter headCJFilter, Model model) {
        return "redirect:/headteacher/classjournal" + "?classTitleId=" + headCJFilter.getClassTitleId() +
                "&disciplineId=" + headCJFilter.getDisciplineId() +
                "&studentId=" + headCJFilter.getStudentId() +
                "&teacherId=" + headCJFilter.getTeacherId() +
                "&startDate=" + headCJFilter.getStartDate() +
                "&endDate=" + headCJFilter.getEndDate();
    }
}