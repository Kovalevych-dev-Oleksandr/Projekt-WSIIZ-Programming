package pl.com.myprojekt.db_in_memory.entity;

import java.util.Arrays;

public class StudentUITM extends BaseEntity {
    private String surname;
    private String name;
    private int yearOfBirth;
    private String[] gradesForTheExam =new String[SIZE_ARRAY_GRADES];
    public static final int SIZE_ARRAY_GRADES = 5;

    public StudentUITM(){}
    public StudentUITM(int yearOfBirth,String name,String surname,String id,String[] gradesForTheExam){
        this.yearOfBirth=yearOfBirth;
        this.name=name;
        this.surname=surname;
        super.setId(id);
        this.gradesForTheExam=gradesForTheExam;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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

    public String[] getGradesForTheExam() {
        return gradesForTheExam;
    }


    public void setGradesForTheExam(String[] gradesForTheExam) {

        this.gradesForTheExam = gradesForTheExam;

    }


    @Override
    public String toString() {
        return
                "Student{" +
                        "id='" + super.getId() + '\'' +
                        ", surname='" + surname + '\'' +
                        ", name='" + name + '\'' +
                        ", year of birth ='" + yearOfBirth + '\'' +
                        ", gradesForTheExam ='" + Arrays.deepToString(gradesForTheExam) + '\'' +
                        '}';
    }



}


