package pl.com.myprojekt.run;

import pl.com.myprojekt.dbinmemory.controller.StudentController;
import pl.com.myprojekt.dbinmemory.dao.StudentDao;
import pl.com.myprojekt.dbinmemory.db.StudentDB;
import pl.com.myprojekt.dbinmemory.entity.StudentUITM;
import pl.com.myprojekt.dbinmemory.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    public class ProgramRun {


        public void run() {
            StudentController controller = new StudentController(new StudentService(new StudentDao(new StudentDB())));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("select your option");
            String position;
            try {
                runNavigation();
                while ((position = reader.readLine()) != null) {
                    crud(position, reader, controller);
                    position = reader.readLine();
                    if (position.equals("0")) {
                        System.exit(0);
                    }
                    crud(position, reader, controller);
                }
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }

        private void runNavigation() {
            System.out.println();
            System.out.println("if you want create worker, please enter 1");
            System.out.println("if you want update worker, please enter 2");
            System.out.println("if you want delete worker, please enter 3");
            System.out.println("if you want findById worker, please enter 4");
            System.out.println("if you want findAll worker, please enter 5");
            System.out.println("if you want exit, please enter 0");
            System.out.println();
        }

        private void crud(String position, BufferedReader reader, StudentController controller) {
            switch (position) {
                case "1":
                    create(reader, controller);
                    break;
                case "2":
                    update(reader, controller);
                    break;
                case "3":
                    delete(reader, controller);
                    break;
                case "4":
                    findById(reader, controller);
                    break;
                case "5":
                    findAll(reader, controller);
                    break;
            }
            runNavigation();
        }

        private void create(BufferedReader reader, StudentController controller) {
            System.out.println("WorkerController.create");
            try {
                String name = getString(reader, "Please, enter your Name:");
                String surname = getString(reader, "Please, enter your Surname:");
                String yearOfBirthString = getString(reader, "Please, enter your Year of Birth:");
                int yearOfBirth=Integer.parseInt(yearOfBirthString);
                int[]newArray=new int [5];
                for(int i=0;i<5;i++){
                    String elementArray=getString(reader, "Please, enter "+ (i+1) +" mark:");
                    newArray[i]=Integer.parseInt(elementArray);
                }
                StudentUITM studentUITM = new StudentUITM();
                studentUITM.setName(name);
                studentUITM.setSurname(surname);
                studentUITM.setYearOfBirth(yearOfBirth);
                studentUITM.setGradesForTheExam(newArray);
                controller.create(studentUITM);
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }


        private void update(BufferedReader reader, StudentController controller) {
            System.out.println("WorkerController.update");
            try {

                String id = getString(reader, "Please, enter id");
                String name = getString(reader, "Please, enter your Name:");
                String surname = getString(reader, "Please, enter your Surname:");
                String yearOfBirthString = getString(reader, "Please, enter your Year of Birth:");
                int yearOfBirth=Integer.parseInt(yearOfBirthString);
                StudentUITM studentUITM = new StudentUITM();
                studentUITM.setYearOfBirth(yearOfBirth);
                studentUITM.setId(id);
                studentUITM.setName(name);
                studentUITM.setSurname(surname);
                //studentUITM.setYearOfBirth(newArray[5]);
                controller.update(studentUITM);
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }

        private void delete(BufferedReader reader, StudentController controller) {
            System.out.println("WorkerController.delete");
            try {
                String id = getString(reader, "Please, enter id");
                controller.delete(id);
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }

        private void findById(BufferedReader reader, StudentController controller) {
            System.out.println("WorkerController.findById");
            try {
                String id = getString(reader, "Please, enter id");
               StudentUITM studentUITM = controller.findById(id);
                if (studentUITM == null) {
                    System.out.println("studentUITM = not found");
                } else {
                    System.out.println("studentUITM = " + studentUITM);
                }
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }

        private void findAll(BufferedReader reader, StudentController controller) {
            System.out.println("WorkerController.findAll");
            StudentUITM[] students = controller.findAll();
            if (students != null && students.length != 0) {
                for (int i = 0; i < students.length; i++) {
                    System.out.println("worker = " + students[i]);
                }
            } else {
                System.out.println("worker empty");
            }
        }

        private String getString(BufferedReader reader, String messageLine) throws IOException {
            System.out.println(messageLine);
            return reader.readLine();
        }


        private int[] getIntArrayFromNumber(int firstNumber,int secondNumber,int thirdNumber,int fourthNumber,int fifthNumber)
        {
            return new int[]{firstNumber,secondNumber,thirdNumber,fourthNumber,fifthNumber};
        }
    }

