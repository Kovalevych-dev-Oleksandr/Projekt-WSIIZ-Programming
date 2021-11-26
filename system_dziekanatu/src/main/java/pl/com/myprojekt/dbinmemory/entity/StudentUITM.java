package pl.com.myprojekt.dbinmemory.entity;

import java.util.Arrays;

public class StudentUITM  {
    private String surname;
    private String name;
    private String id;
    private int  yearOfBirth;
    private int [] gradesForTheExam;
    private static final int SIZE_ARRAY_GRADES = 5;

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int[] getGradesForTheExam() {
        return gradesForTheExam;
    }


 public void setGradesForTheExam( int[] gradesForTheExam) {

        this.gradesForTheExam=gradesForTheExam;

    }


    @Override
    public String toString() {
        return
           "Student{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", year of birth ='" + yearOfBirth +'\'' +
                ", gradesForTheExam ='" + Arrays.toString(gradesForTheExam)+'\'' +
                '}';
    }

}


