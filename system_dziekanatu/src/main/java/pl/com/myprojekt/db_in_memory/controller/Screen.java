package pl.com.myprojekt.db_in_memory.controller;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.db.StudentDB;
import pl.com.myprojekt.db_in_memory.entity.ExamsName;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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


        listStudentModel = new DefaultListModel();
        listStudents.setModel(listStudentModel);
        list1.setModel(listStudentModel);

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

                averageResult1.setText(String.valueOf(studentService.numberStudentsWhitsMark(1)));
                clearUnActiveRadioButton(false, true, true, true, true);
            }
        });
        radioButtonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageResult2.setText(String.valueOf(studentService.numberStudentsWhitsMark(2)));
                clearUnActiveRadioButton(true, false, true, true, true);

            }
        });
        radioButtonTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                averageResult3.setText(String.valueOf(studentService.numberStudentsWhitsMark(3)));
                clearUnActiveRadioButton(true, true, false, true, true);
            }
        });
        radioButtonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageResult4.setText(String.valueOf(studentService.numberStudentsWhitsMark(4)));
                clearUnActiveRadioButton(true, true, true, false, true);

            }
        });
        radioButtonFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                averageResult5.setText(String.valueOf(studentService.numberStudentsWhitsMark(5)));
                clearUnActiveRadioButton(true, true, true, true, false);

            }
        });
    }

    public static String update(StudentUITM studentUITM) {
        String result = studentService.create(studentUITM);
        refreshArray();
        return result;
    }

    public static void refreshArray() {
        students = Arrays.asList(studentService.findAll());
        listStudentModel.removeAllElements();
        for (StudentUITM student : students) {
            System.out.println(student.getId());
            listStudentModel.addElement(student.getName() + " " + student.getSurname());
        }
    }

    private void clearUnActiveRadioButton(boolean first, boolean second, boolean thread, boolean fourth, boolean fifth) {
        if (first) {
            averageResult1.setText("");
            radioButtonOne.setSelected(false);
        }
        if (second) {
            averageResult2.setText("");
            radioButtonTwo.setSelected(false);
        }
        if (thread) {
            averageResult3.setText("");
            radioButtonTree.setSelected(false);
        }
        if (fourth) {
            averageResult4.setText("");
            radioButtonFour.setSelected(false);
        }
        if (fifth) {
            averageResult5.setText("");
            radioButtonFive.setSelected(false);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        CrudLeble = new JTabbedPane();
        CrudLeble.setEnabled(true);
        CrudLeble.setToolTipText("");
        MainPanel.add(CrudLeble, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        Iformations = new JPanel();
        Iformations.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        Iformations.setEnabled(true);
        Font IformationsFont = this.$$$getFont$$$(null, -1, 14, Iformations.getFont());
        if (IformationsFont != null) Iformations.setFont(IformationsFont);
        CrudLeble.addTab("Iformation about Student", Iformations);
        panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelLeft.setAutoscrolls(false);
        panelLeft.setEnabled(true);
        Iformations.add(panelLeft, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        listStudents = new JList();
        Font listStudentsFont = this.$$$getFont$$$(null, -1, 18, listStudents.getFont());
        if (listStudentsFont != null) listStudents.setFont(listStudentsFont);
        listStudents.setSelectionMode(0);
        panelLeft.add(listStudents, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        penelRight = new JPanel();
        penelRight.setLayout(new GridLayoutManager(16, 2, new Insets(0, 0, 0, 0), -1, -1));
        penelRight.setBackground(new Color(-1114881));
        Iformations.add(penelRight, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textName = new JTextField();
        penelRight.add(textName, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textDateOfBirth = new JTextField();
        penelRight.add(textDateOfBirth, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSurname = new JTextField();
        textSurname.setText("");
        penelRight.add(textSurname, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameLable = new JLabel();
        Font nameLableFont = this.$$$getFont$$$(null, -1, 22, nameLable.getFont());
        if (nameLableFont != null) nameLable.setFont(nameLableFont);
        nameLable.setText("Name:");
        penelRight.add(nameLable, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        surnameLable = new JLabel();
        Font surnameLableFont = this.$$$getFont$$$(null, -1, 22, surnameLable.getFont());
        if (surnameLableFont != null) surnameLable.setFont(surnameLableFont);
        surnameLable.setText("Surname:");
        penelRight.add(surnameLable, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthLable = new JLabel();
        Font birthLableFont = this.$$$getFont$$$(null, -1, 22, birthLable.getFont());
        if (birthLableFont != null) birthLable.setFont(birthLableFont);
        birthLable.setText("Year Of Birth:");
        penelRight.add(birthLable, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        examLable = new JLabel();
        Font examLableFont = this.$$$getFont$$$(null, -1, 22, examLable.getFont());
        if (examLableFont != null) examLable.setFont(examLableFont);
        examLable.setText("Average rating:");
        penelRight.add(examLable, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textGrades = new JTextField();
        textGrades.setText("");
        penelRight.add(textGrades, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 22, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Age:");
        penelRight.add(label1, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAge = new JTextField();
        penelRight.add(textAge, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textMath = new JTextField();
        penelRight.add(textMath, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textEnglish = new JTextField();
        penelRight.add(textEnglish, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textPrograming = new JTextField();
        textPrograming.setText("");
        penelRight.add(textPrograming, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textHistory = new JTextField();
        textHistory.setText("");
        penelRight.add(textHistory, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textPhysics = new JTextField();
        penelRight.add(textPhysics, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSubjectMath = new JTextField();
        penelRight.add(textSubjectMath, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSubjectEnglish = new JTextField();
        penelRight.add(textSubjectEnglish, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSubjectPrograming = new JTextField();
        penelRight.add(textSubjectPrograming, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSubjectHistory = new JTextField();
        penelRight.add(textSubjectHistory, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSubjectPhysics = new JTextField();
        penelRight.add(textSubjectPhysics, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textId = new JTextField();
        penelRight.add(textId, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelId = new JLabel();
        Font labelIdFont = this.$$$getFont$$$(null, -1, 22, labelId.getFont());
        if (labelIdFont != null) labelId.setFont(labelIdFont);
        labelId.setText("Id:");
        penelRight.add(labelId, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Iformations.add(panelTop, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dUITM = new JLabel();
        Font dUITMFont = this.$$$getFont$$$(null, -1, 22, dUITM.getFont());
        if (dUITMFont != null) dUITM.setFont(dUITMFont);
        dUITM.setText("Dean 's UITM");
        panelTop.add(dUITM, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CRUD = new JPanel();
        CRUD.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        CrudLeble.addTab("CRUD operations", CRUD);
        iformations = new JPanel();
        iformations.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        CRUD.add(iformations, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(14, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1114881));
        iformations.add(panel1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setNameField = new JTextField();
        panel1.add(setNameField, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setYearFild = new JTextField();
        panel1.add(setYearFild, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setSurnameFild = new JTextField();
        setSurnameFild.setText("");
        panel1.add(setSurnameFild, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 22, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Name:");
        panel1.add(label2, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 22, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Surname:");
        panel1.add(label3, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 22, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Year Of Birth:");
        panel1.add(label4, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setMathField = new JTextField();
        panel1.add(setMathField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setEnglishField = new JTextField();
        panel1.add(setEnglishField, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setProgramingField = new JTextField();
        setProgramingField.setText("");
        panel1.add(setProgramingField, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setHistoryField = new JTextField();
        setHistoryField.setText("");
        panel1.add(setHistoryField, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setPhysicsField = new JTextField();
        panel1.add(setPhysicsField, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mathField = new JTextField();
        panel1.add(mathField, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        englishField = new JTextField();
        panel1.add(englishField, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        programingField = new JTextField();
        panel1.add(programingField, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        historyField = new JTextField();
        panel1.add(historyField, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        physicsField = new JTextField();
        panel1.add(physicsField, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setIDField = new JTextField();
        panel1.add(setIDField, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 22, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Id:");
        panel1.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        list1 = new JList();
        iformations.add(list1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("DeleteById");
        iformations.add(deleteButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iformation = new JLabel();
        Font iformationFont = this.$$$getFont$$$(null, -1, 20, iformation.getFont());
        if (iformationFont != null) iformation.setFont(iformationFont);
        iformation.setText("Information about operations:");
        iformations.add(iformation, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setOperationIformation = new JTextField();
        iformations.add(setOperationIformation, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        updateButtonButton = new JButton();
        updateButtonButton.setText("UpdateById");
        iformations.add(updateButtonButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createButtonButton = new JButton();
        createButtonButton.setText("Create");
        iformations.add(createButtonButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteAllButton = new JButton();
        deleteAllButton.setText("DeleteAll");
        iformations.add(deleteAllButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTree = new JPanel();
        panelTree.setLayout(new GridLayoutManager(20, 2, new Insets(0, 0, 0, 0), -1, -1));
        CrudLeble.addTab("Number student and Student sort", panelTree);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("The number of students  according to the given rating");
        panelTree.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTree.add(panel2, new GridConstraints(2, 1, 18, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        radioButtonOne = new JRadioButton();
        radioButtonOne.setText("Assessment-1");
        panel2.add(radioButtonOne, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonTwo = new JRadioButton();
        radioButtonTwo.setText("Assessment-2");
        panel2.add(radioButtonTwo, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonTree = new JRadioButton();
        radioButtonTree.setText("Assessment-3");
        panel2.add(radioButtonTree, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonFour = new JRadioButton();
        radioButtonFour.setText("Assessment-4");
        panel2.add(radioButtonFour, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonFive = new JRadioButton();
        radioButtonFive.setText("Assessment-5");
        panel2.add(radioButtonFive, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        averageResult2 = new JTextField();
        panelTree.add(averageResult2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("People with Average Mark:1");
        panelTree.add(label7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("People with Average Mark:2");
        panelTree.add(label8, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("People with Average Mark:3");
        panelTree.add(label9, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("People with Average Mark:4");
        panelTree.add(label10, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("People with Average Mark:5");
        panelTree.add(label11, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelTree.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelTree.add(spacer4, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelTree.add(spacer5, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelTree.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panelTree.add(spacer7, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panelTree.add(spacer8, new GridConstraints(19, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        averageResult4 = new JTextField();
        panelTree.add(averageResult4, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        averageResult3 = new JTextField();
        panelTree.add(averageResult3, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        averageResult5 = new JTextField();
        panelTree.add(averageResult5, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        averageResult1 = new JTextField();
        panelTree.add(averageResult1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}