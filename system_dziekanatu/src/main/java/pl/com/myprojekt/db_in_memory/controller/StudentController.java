package pl.com.myprojekt.db_in_memory.controller;

import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

import javax.swing.*;

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

    /*public static void desktop() {
        JMenu menu;
        JMenuItem a1,a2;
        JFrame a = new JFrame("Example");
        menu = new JMenu("options");
        JMenuBar m1 = new JMenuBar();
        a1 = new JMenuItem("example");
        a2 = new JMenuItem("example1");
        JButton button=new JButton("as");
        menu.add(a1);
        menu.add(a2);
        m1.add(menu);
        a.setJMenuBar(m1);
        a.setSize(400,400);
        a1.add(button);
        a.setLayout(null);
        a.setVisible(true);
    }*/

}
