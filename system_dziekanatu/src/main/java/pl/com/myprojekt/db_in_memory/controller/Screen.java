package pl.com.myprojekt.db_in_memory.controller;

import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.db.StudentDB;
import pl.com.myprojekt.db_in_memory.entity.ExamsName;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

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
    private JPanel penelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel mainPanel;
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
    private static StudentService studentService = new StudentService(new StudentDao(new StudentDB()));
    private static ArrayList<StudentUITM> students;
    private static DefaultListModel listStudentModel;


    public Screen() {
        super("Dean 's Office UITM");

        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        students = new ArrayList<StudentUITM>();
        listStudentModel = new DefaultListModel();
        listStudents.setModel(listStudentModel);


        listStudents.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectionNumber = listStudents.getSelectedIndex();
                if (selectionNumber >= 0) {
                    StudentUITM studentUITM = students.get(selectionNumber);
                    String[] array=studentUITM.getGradesForTheExam();
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
                }
            }
        });
    }

    public static void addStudent(StudentUITM studentUITM) {
        studentService.create(studentUITM);
        students.add(studentUITM);
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