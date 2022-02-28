package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Course;
import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping(path = "/courses/{sid}", method = RequestMethod.GET)
    public String showCoursesOfOneStudent(Model model, @PathVariable("sid") int id) {
        //System.out.println(id);
        List<Course> courses = studentsService.getCoursesByStudentId((long) id);
        //System.out.println(courses);
        model.addAttribute("studentCourses", courses);
        model.addAttribute("studentId", id);
        model.addAttribute("studentMissingCourses", studentsService.getMissingCoursesByStudentId((long) id));
        //System.out.println(studentsService.getMissingCoursesByStudentId((long) id));
        return "student-courses-list";
    }

    @RequestMapping(path = "/remove/{sid}", method = RequestMethod.GET)
    public String deleteStudent(Model model, @PathVariable("sid") int id) {
        studentsService.removeById((long) id);
        model.addAttribute("studentsList", studentsService.getAllStudentsList());
        return "students-list";
    }

}
