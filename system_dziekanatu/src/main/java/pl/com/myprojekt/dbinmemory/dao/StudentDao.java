package pl.com.myprojekt.dbinmemory.dao;

import pl.com.myprojekt.dbinmemory.db.StudentDB;
import pl.com.myprojekt.dbinmemory.entity.StudentUITM;

public class StudentDao {
    private final StudentDB db;

    public StudentDao( StudentDB db) {
        this.db = db;
    }

    public void create(StudentUITM studentUITM) {

        db.create(studentUITM);
    }

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
