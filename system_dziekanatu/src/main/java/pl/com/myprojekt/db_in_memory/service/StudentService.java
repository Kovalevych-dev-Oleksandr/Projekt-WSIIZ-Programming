package pl.com.myprojekt.db_in_memory.service;


import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

import java.util.Calendar;

public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void create(StudentUITM studentUITM) {
        studentDao.create(studentUITM);
    }

    public StudentUITM findById(String id) {
        return studentDao.findById(id);
    }

    public StudentUITM[] findAll() {
        return studentDao.findAll();
    }

    public void update(StudentUITM studentUITM) {
        studentDao.update(studentUITM);
    }

    public void delete(String id) {
        studentDao.delete(id);
    }

    public StudentUITM BirthYearOfStudent(String id) {
        StudentUITM studentUITM = studentDao.findById(id);
        studentUITM.setYearOfBirth(studentsYear(studentUITM));
        return studentUITM;
    }



    public  int studentsYear(StudentUITM studentUITM) {
        return Calendar.getInstance().get(Calendar.YEAR) - studentUITM.getYearOfBirth();
    }

  /*  public StudentUITM[] highRatingOfRatings() {
        StudentUITM[] studentsUITM = studentDao.findAll();
        for (int i = 0; i < studentsUITM.length; i++) {
            studentsUITM[i] = ratingCalculation(studentsUITM[i]);
        }
        return studentsUITM;
    }

    private StudentUITM ratingCalculation(StudentUITM studentUITM) {
        int result = 0;
        String[][] bufferArray = studentUITM.getGradesForTheExam();
        for (int i = 0; i < StudentUITM.SIZE_ARRAY_GRADES; i++) {
            result = result + Integer.parseInt(bufferArray[i][0]);
        }
        if (result / StudentUITM.SIZE_ARRAY_GRADES < 4) {
            studentUITM = null;
        }
        return studentUITM;
    }*/
    public int ratingCalculation(StudentUITM studentUITM) {
        int result = 0;
        String[] bufferArray = studentUITM.getGradesForTheExam();
        for (int i = 0; i < StudentUITM.SIZE_ARRAY_GRADES; i++) {
            result = result + Integer.parseInt(bufferArray[i]);
        }

        return result/StudentUITM.SIZE_ARRAY_GRADES;
    }


    public StudentUITM[] studentSort() {
        StudentUITM[] studentsUITM = studentDao.findAll();
        StudentUITM current;
        for (int i = 0; i < studentsUITM.length; i++) {
            for (int j = 0; j < studentsUITM.length; j++) {
                if (studentsUITM[i].getYearOfBirth() > studentsUITM[i].getYearOfBirth()) {
                    current = studentsUITM[i + 1];
                    studentsUITM[i] = studentsUITM[i + 1];
                    studentsUITM[i] = current;
                }
            }
        }
        return studentsUITM;
    }
}


