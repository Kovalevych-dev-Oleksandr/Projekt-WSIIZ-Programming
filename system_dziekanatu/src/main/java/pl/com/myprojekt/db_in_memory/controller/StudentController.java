package pl.com.myprojekt.db_in_memory.controller;

import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

import javax.swing.*;
@Deprecated
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    public void create(StudentUITM studentUITM){ studentService.create(studentUITM);}
    public StudentUITM findById(String id) { return studentService.findById(id); }

    public StudentUITM[] findAll() { return studentService.findAll();}

    public void update(StudentUITM studentUITM) {
        studentService.update(studentUITM);
    }

    public void delete(String id) {
        studentService.delete(id);
    }



}
