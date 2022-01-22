
package pl.com.myprojekt;

import pl.com.myprojekt.db_in_memory.controller.StudentControllerTrue;

public class SystemDziekanatuMain {
    public static void main(String[] args) {

        /*ProgramRun programRun= new ProgramRun();
        programRun.run();*/
        StudentControllerTrue studentControllerTrue = new StudentControllerTrue("Dean 's Office UITM");
        studentControllerTrue.setVisible(true);
       studentControllerTrue.startCreate();

    }


    // В начале строки потом колонки
}
