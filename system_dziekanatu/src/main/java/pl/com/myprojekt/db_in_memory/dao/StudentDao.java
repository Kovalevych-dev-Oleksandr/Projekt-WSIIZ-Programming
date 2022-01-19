package pl.com.myprojekt.db_in_memory.dao;

import pl.com.myprojekt.db_in_memory.db.StudentDB;
import pl.com.myprojekt.db_in_memory.entity.StudentUITM;

public class StudentDao {
    private final StudentDB db;

    public StudentDao( StudentDB db) {
        this.db = db;
    }

    public String create(StudentUITM studentUITM) { return db.create(studentUITM); }

    public StudentUITM findById(String id) {
        return db.findById(id);
    }

    public StudentUITM[] findAll() {
        return db.findAll();
    }

    public void update(StudentUITM studentUITM) {
        db.update(studentUITM);
    }

    public void delete(String id) {
        db.delete(id);
    }


}
