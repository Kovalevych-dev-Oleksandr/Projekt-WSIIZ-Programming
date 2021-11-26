package pl.com.myprojekt.dbinmemory.service;


import pl.com.myprojekt.dbinmemory.dao.StudentDao;
import pl.com.myprojekt.dbinmemory.entity.StudentUITM;

public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao){
        this.studentDao=studentDao;
    }

    public void create (StudentUITM studentUITM) {

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
}


