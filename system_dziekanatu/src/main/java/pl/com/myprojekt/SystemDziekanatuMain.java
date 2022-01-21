
package pl.com.myprojekt;

import pl.com.myprojekt.db_in_memory.controller.Screen;
import pl.com.myprojekt.db_in_memory.dao.StudentDao;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;
import pl.com.myprojekt.db_in_memory.service.StudentService;

public class SystemDziekanatuMain {
    public static void main(String[] args) {

        /*ProgramRun programRun= new ProgramRun();
        programRun.run();*/
        Screen screen = new Screen("Dean 's Office UITM");
        screen.setVisible(true);
       screen.startCreate();
    }


    // В начале строки потом колонки
}
