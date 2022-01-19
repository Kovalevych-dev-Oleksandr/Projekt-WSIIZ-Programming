package pl.com.myprojekt.db_in_memory.controller;

import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.db.StudentDB;
import pl.com.myprojekt.db_in_memory.entity.ExamsName;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Screen extends JFrame {
    private JTextField textName;
    private JTextField textSurname;
    private JTextField textDateOfBirth;
    private JTextField textGrades;
    private JLabel nameLable;
    private JLabel surnameLable;
    private JLabel birthLable;
    private JLabel examLable;
    private JList listStudents;
    private JPanel penelRight;
    private JPanel panelLeft;
    private JPanel MainPanel;
    private JTextField textAge;
    private JTextField textMath;
    private JTextField textEnglish;
    private JTextField textPrograming;
    private JTextField textHistory;
    private JTextField textPhysics;
    private JTextField textSubjectMath;
    private JTextField textSubjectEnglish;
    private JTextField textSubjectPrograming;
    private JTextField textSubjectHistory;
    private JTextField textSubjectPhysics;
    private JButton createButtonButton;
    private JButton updateButtonButton;
    private JTextField textId;
    private JLabel labelId;
    private JTabbedPane CrudLeble;
    private JPanel panelTop;
    private JTextField SetIDField;
    private JTextField SetNameField;
    private JTextField SetSurnameFild;
    private JTextField SetYearFild;
    private JTextField MathField;
    private JTextField EnglishField;
    private JTextField ProgramingField;
    private JTextField HistoryField;
    private JTextField PhysicsField;
    private JButton deleteButton;
    private JButton deleteAllButton;
    private JList list1;
    private JTextField SetMathField;
    private JTextField SetEnglishField;
    private JTextField SetProgramingField;
    private JTextField SetHistoryField;
    private JTextField SetPhysicsField;
    private JPanel Iformations;
    private JPanel CRUD;
    private static StudentService studentService = new StudentService(new StudentDao(new StudentDB()));
    private static List<StudentUITM> students;
    private static DefaultListModel listStudentModel;


    public Screen(String title) {
        super(title);

        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        students = new ArrayList<StudentUITM>();
        listStudentModel = new DefaultListModel();
        listStudents.setModel(listStudentModel);
        list1.setModel(listStudentModel);
        createButtonButton.setEnabled(true);

        MathField.setText(ExamsName.getFirstExam());
        EnglishField.setText(ExamsName.getSecondExam());
        ProgramingField.setText(ExamsName.getThreadExam());
        HistoryField.setText(ExamsName.getForthExam());
        PhysicsField.setText(ExamsName.getFifthExam());

        listStudents.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectionNumber = listStudents.getSelectedIndex();
                if (selectionNumber >= 0) {
                    StudentUITM studentUITM = students.get(selectionNumber);
                    String[] array = studentUITM.getGradesForTheExam();
                    textId.setText(studentUITM.getId());
                    textName.setText(studentUITM.getName());
                    textGrades.setText(String.valueOf(studentService.ratingCalculation(studentUITM)));
                    textMath.setText(array[0]);
                    textEnglish.setText(array[1]);
                    textPrograming.setText(array[2]);
                    textHistory.setText(array[3]);
                    textPhysics.setText(array[4]);
                    textSubjectMath.setText(ExamsName.getFirstExam());
                    textSubjectEnglish.setText(ExamsName.getSecondExam());
                    textSubjectPrograming.setText(ExamsName.getThreadExam());
                    textSubjectHistory.setText(ExamsName.getForthExam());
                    textSubjectPhysics.setText(ExamsName.getFifthExam());
                    textSurname.setText(studentUITM.getSurname());
                    textDateOfBirth.setText(String.valueOf(studentUITM.getYearOfBirth()));
                    textAge.setText(String.valueOf(studentService.studentsYear(studentUITM)));
                    createButtonButton.setEnabled(true);
                }
            }
        });
        createButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StudentUITM studentUITM = new StudentUITM(
                        Integer.parseInt(SetYearFild.getText()),
                        SetNameField.getText(),
                        SetSurnameFild.getText(),
                        SetIDField.getText(),
                        new String[]{SetMathField.getText(), SetEnglishField.getText(), SetProgramingField.getText(), SetHistoryField.getText(), SetPhysicsField.getText()}
                );
                addStudent(studentUITM);
            }
        });

        updateButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectionNumber = listStudents.getSelectedIndex();
                if (selectionNumber >= 0) {
                    StudentUITM studentUITM = students.get(selectionNumber);
                    studentUITM.setName(textName.getText());
                    studentUITM.setId(textId.getText());
                    studentUITM.setSurname(textSurname.getText());
                    studentUITM.setYearOfBirth(Integer.parseInt(textDateOfBirth.getText()));
                    studentUITM.setGradesForTheExam(new String[]{textMath.getText(), textEnglish.getText(), textPrograming.getText(), textHistory.getText(), textPhysics.getText()});
                    studentService.update(studentUITM);
                    refreshArray();
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void addStudent(StudentUITM studentUITM) {
        studentService.create(studentUITM);
        students = Arrays.asList(studentService.findAll());
        refreshArray();
    }

    private static void refreshArray() {
        listStudentModel.removeAllElements();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId());
            listStudentModel.addElement(students.get(i).getId());
        }
    }


}