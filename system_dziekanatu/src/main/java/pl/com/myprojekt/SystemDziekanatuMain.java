
package pl.com.myprojekt;

import pl.com.myprojekt.db_in_memory.controller.Screen;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

public class SystemDziekanatuMain {
    public static void main(String[] args) {

        /*ProgramRun programRun= new ProgramRun();
        programRun.run();*/
        Screen screen = new Screen("Dean 's Office UITM");
        screen.setVisible(true);
        startCreate();

    }

    private static void startCreate() {
        StudentUITM[] array = new StudentUITM[6];
        StudentUITM studentUITM = new StudentUITM(2003, "Oleksandr", "Kovalevych", "64036", new int[]{5, 5, 5, 5, 5});
        StudentUITM studentUITM2 = new StudentUITM(2004, "Silvester", "Stallone", "55226", new int[]{3, 4, 5, 2, 5});
        StudentUITM studentUITM3 = new StudentUITM(2000, "Piotr", "Paschal", "65426", new int[]{5, 2, 5, 4, 5});
        StudentUITM studentUITM4 = new StudentUITM(2000, "Egor", "Wasserman", "48526", new int[]{1, 4, 5, 5, 4});
        StudentUITM studentUITM5 = new StudentUITM(1999, "Liza", "Block", "54526", new int[]{2, 4, 5, 2, 3});
        StudentUITM studentUITM6 = new StudentUITM(2000, "Betta", "Frac", "64826", new int[]{3, 3, 5, 1, 2});
        array[0] = studentUITM;
        array[1] = studentUITM2;
        array[2] = studentUITM3;
        array[3] = studentUITM4;
        array[4] = studentUITM5;
        array[5] = studentUITM6;
        for (StudentUITM item : array) Screen.update(item);
    }
    // В начале строки потом колонки
}
