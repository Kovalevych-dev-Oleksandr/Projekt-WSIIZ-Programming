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
    private JTextField setIDField;
    private JTextField setNameField;
    private JTextField setSurnameFild;
    private JTextField setYearFild;
    private JTextField mathField;
    private JTextField englishField;
    private JTextField programingField;
    private JTextField historyField;
    private JTextField physicsField;
    private JButton deleteButton;
    private JButton deleteAllButton;
    private JList list1;
    private JTextField setMathField;
    private JTextField setEnglishField;
    private JTextField setProgramingField;
    private JTextField setHistoryField;
    private JTextField setPhysicsField;
    private JPanel Iformations;
    private JPanel CRUD;
    private JPanel iformations;
    private JLabel iformation;
    private JTextField setOperationIformation;
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
        updateButtonButton.setEnabled(true);

        mathField.setText(ExamsName.getFirstExam());
        englishField.setText(ExamsName.getSecondExam());
        programingField.setText(ExamsName.getThreadExam());
        historyField.setText(ExamsName.getForthExam());
        physicsField.setText(ExamsName.getFifthExam());

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
                        Integer.parseInt(setYearFild.getText()),
                        setNameField.getText(),
                        setSurnameFild.getText(),
                        setIDField.getText(),
                        new String[]{setMathField.getText(), setEnglishField.getText(), setProgramingField.getText(), setHistoryField.getText(), setPhysicsField.getText()}
                );
                setOperationIformation.setText(update(studentUITM));
                clear();
                refreshArray();
            }
        });

        updateButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StudentUITM studentUITM = new StudentUITM();
                studentUITM.setId(setIDField.getText());
                studentUITM.setName(setNameField.getText());
                studentUITM.setSurname(setSurnameFild.getText());
                studentUITM.setYearOfBirth(Integer.parseInt(setYearFild.getText()));
                studentUITM.setGradesForTheExam(new String[]{
                        setMathField.getText(),
                        setEnglishField.getText(),
                        setProgramingField.getText(),
                        setHistoryField.getText(),
                        setPhysicsField.getText()});
                setOperationIformation.setText(studentService.update(studentUITM));
                clear();
                refreshArray();

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
                setOperationIformation.setText(studentService.delete(setIDField.getText()));
                clear();
                refreshArray();
            }
        });
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationIformation.setText(studentService.deleteAll());
                refreshArray();
            }
        });
    }

    public static String update(StudentUITM studentUITM) {
        String result = studentService.create(studentUITM);
        refreshArray();
        return result;
    }

    private static void refreshArray() {
        students = Arrays.asList(studentService.findAll());
        listStudentModel.removeAllElements();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId());
            listStudentModel.addElement(students.get(i).getId());
        }
    }

    private void clear() {
        setYearFild.setText("");
        setNameField.setText("");
        setSurnameFild.setText("");
        setIDField.setText("");
        setMathField.setText("");
        setEnglishField.setText("");
        setProgramingField.setText("");
        setHistoryField.setText("");
        setPhysicsField.setText("");
    }

}