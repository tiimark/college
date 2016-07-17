package com.mark.college.controller;

import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import com.mark.college.service.GradeService;
import com.mark.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Controller
@SessionAttributes("studentObj")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student-grade-{studentId}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("studentId") int id){
        Student studentObj = studentService.findStudent(id);
        List<Grade> grades = gradeService.findGrades(studentObj);

        if(studentObj != null && !grades.isEmpty()) {
            ModelAndView mvFindGrades = new ModelAndView("findGrades");
            mvFindGrades.addObject("studentObj", studentObj);
            mvFindGrades.addObject("grades", grades);
            return mvFindGrades;
        }

        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Student not found. Try again!");
        return mvError;

    }

    @RequestMapping(value = "/grade/insert", method = RequestMethod.GET)
    public ModelAndView insertPage(){
        return new ModelAndView("insertGrade");
    }

    @RequestMapping(value = "/grade/inserted", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Grade grade){
        int studentId = grade.getStudent().getId();
        Student findStudent = studentService.findStudent(studentId);

        Boolean newGrade = gradeService.insertGrades(grade);

        if (newGrade) {
            ModelAndView mvInsertedGrade = new ModelAndView("insertedGrade");
            mvInsertedGrade.addObject("id", studentId);
            mvInsertedGrade.addObject("name", findStudent.getName());
            mvInsertedGrade.addObject("subject", grade.getSubject());
            mvInsertedGrade.addObject("average", grade.getAverage());
            return mvInsertedGrade;
        }

        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Grade was not inserted. Try again!");
        return mvError;
    }


}
