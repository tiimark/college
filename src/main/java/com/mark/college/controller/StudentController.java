package com.mark.college.controller;

import com.mark.college.entity.Absence;
import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import com.mark.college.entity.StudentSubject;
import com.mark.college.service.AbsenceService;
import com.mark.college.service.GradeService;
import com.mark.college.service.StudentService;
import com.mark.college.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Controller
@SessionAttributes("studentObj")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private AbsenceService absenceService;

    /*
    @Autowired
    private StudentSubjectService studentSubjectService;
    */

    @RequestMapping(value = "/student-login", method = RequestMethod.GET)
    public ModelAndView studentLoginPage(){
        return new ModelAndView("studentLogin");
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ModelAndView studentLogin(@ModelAttribute Student student){
        Student studentAuth = studentService.auth(student.getId(),student.getPassword());

        if(studentAuth != null) {
            ModelAndView mvStudentMenu = new ModelAndView("studentHomepage");
            mvStudentMenu.addObject("studentObj", studentAuth);
            return mvStudentMenu;
        }
        return new ModelAndView("studentLogin");

    }

    @RequestMapping(value = "/student-homepage", method = RequestMethod.GET)
    public ModelAndView studentHomepage(){
        return new ModelAndView("studentHomepage");
    }

    @RequestMapping(value = "/student-{studentId}-grades", method = RequestMethod.GET)
    public ModelAndView findGrades(@PathVariable("studentId") int id){
        Student studentObj = studentService.findStudent(id);
        List<Grade> grades = gradeService.findGrades(studentObj);

        if(studentObj != null && !grades.isEmpty()) {
            ModelAndView mvFindGrades = new ModelAndView("studentGrades");
            mvFindGrades.addObject("grades", grades);
            return mvFindGrades;
        }

        return new ModelAndView("studentHomepage");

    }

    @RequestMapping(value = "/student-{studentId}-absences", method = RequestMethod.GET)
    public ModelAndView findAbsences(@PathVariable("studentId") int id){
        Student studentObj = studentService.findStudent(id);
        List<Absence> absences = absenceService.findAbsences(studentObj);

        if(studentObj != null && !absences.isEmpty()){
            ModelAndView mvFindAbsences = new ModelAndView("studentAbsences");
            mvFindAbsences.addObject("absences",absences);
            return mvFindAbsences;
        }

        return new ModelAndView("studentHomepage");
    }

    /*
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("id") int id){

        Student student = studentService.findStudent(id);

        List<StudentSubject> studentSubjects = studentSubjectService.findSubjects(student);

        if(student != null && !studentSubjects.isEmpty()){
            ModelAndView mvFindStudent = new ModelAndView("findStudent");
            mvFindStudent.addObject("student", student);
            mvFindStudent.addObject("studentSubjects", studentSubjects);
            return mvFindStudent;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Student not found. Try again!");
        return mvError;
    }

    */

}
