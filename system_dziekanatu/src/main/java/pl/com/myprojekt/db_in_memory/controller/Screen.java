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
    private JLabel dUITM;
    private JRadioButton radioButtonOne;
    private JRadioButton radioButtonTwo;
    private JRadioButton radioButtonTree;
    private JRadioButton radioButtonFour;
    private JRadioButton radioButtonFive;
    private JPanel panelTree;
    private JTextField averageResult1;
    private JTextField averageResult2;
    private JTextField averageResult3;
    private JTextField averageResult4;
    private JTextField averageResult5;
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

        radioButtonOne.setSelected(false);
        radioButtonTwo.setSelected(false);
        radioButtonTree.setSelected(false);
        radioButtonFour.setSelected(false);
        radioButtonFive.setSelected(false);

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
                    int[] array = studentUITM.getGradesForTheExam();
                    textId.setText(studentUITM.getId());
                    textName.setText(studentUITM.getName());
                    textGrades.setText(String.valueOf(studentService.ratingCalculation(studentUITM)));
                    textMath.setText(String.valueOf(array[0]));
                    textEnglish.setText(String.valueOf(array[1]));
                    textPrograming.setText(String.valueOf(array[2]));
                    textHistory.setText(String.valueOf(array[3]));
                    textPhysics.setText(String.valueOf(array[4]));
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
                        new int[]{
                                Integer.parseInt(setMathField.getText()),
                                Integer.parseInt(setEnglishField.getText()),
                                Integer.parseInt(setProgramingField.getText()),
                                Integer.parseInt(setHistoryField.getText()),
                                Integer.parseInt(setPhysicsField.getText())}
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
                studentUITM.setGradesForTheExam(new int[]{
                        Integer.parseInt(setMathField.getText()),
                        Integer.parseInt(setEnglishField.getText()),
                        Integer.parseInt(setProgramingField.getText()),
                        Integer.parseInt(setHistoryField.getText()),
                        Integer.parseInt(setPhysicsField.getText())});
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
        radioButtonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                averageResult1.setText(String.valueOf( studentService.numberStudentsWhitsMark(1)));
            }
        });
        radioButtonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageResult2.setText(String.valueOf( studentService.numberStudentsWhitsMark(2)));
            }
        });
        radioButtonTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageResult3.setText(String.valueOf( studentService.numberStudentsWhitsMark(3)));
            }
        });
        radioButtonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageResult4.setText(String.valueOf( studentService.numberStudentsWhitsMark(4)));
            }
        });
        radioButtonFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    averageResult5.setText(String.valueOf( studentService.numberStudentsWhitsMark(5)));

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
        for (StudentUITM student : students) {
            System.out.println(student.getId());
            listStudentModel.addElement(student.getName() + " " + student.getSurname());
        }
    }

    private void sortByLetter() {


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

    public static void startCreate() {
        StudentUITM[] array = new StudentUITM[6];
        StudentUITM studentUITM = new StudentUITM(2003, "Oleksandr", "Kovalevych", new int[]{5, 5, 5, 5, 5});
        StudentUITM studentUITM2 = new StudentUITM(2004, "Silvester", "Stallone", new int[]{3, 4, 5, 2, 5});
        StudentUITM studentUITM3 = new StudentUITM(2000, "Piotr", "Paschal", new int[]{5, 2, 5, 4, 5});
        StudentUITM studentUITM4 = new StudentUITM(2000, "Egor", "Wasserman", new int[]{1, 4, 5, 5, 4});
        StudentUITM studentUITM5 = new StudentUITM(1999, "Liza", "Block", new int[]{2, 4, 5, 2, 3});
        StudentUITM studentUITM6 = new StudentUITM(2000, "Betta", "Frac", new int[]{3, 3, 5, 1, 2});
        array[0] = studentUITM;
        array[1] = studentUITM2;
        array[2] = studentUITM3;
        array[3] = studentUITM4;
        array[4] = studentUITM5;
        array[5] = studentUITM6;
        for (StudentUITM item : array) update(item);
        refreshArray();
    }

}