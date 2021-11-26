package pl.com.myprojekt.dbinmemory.db;

import pl.com.myprojekt.dbinmemory.entity.StudentUITM;

import java.util.Objects;
import java.util.UUID;

public class StudentDB {
    private static final int START_ARRAY_SIZE = 30;
    private static StudentUITM[] students = new StudentUITM[START_ARRAY_SIZE];

    public void create(final StudentUITM studentUITM) {
        boolean dataRecordingCapability = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = studentUITM;
                dataRecordingCapability = true;
                break;
            }
        }
        if (dataRecordingCapability) {
            return;
        }
        this.increasingArray(studentUITM);
    }

    private void increasingArray(StudentUITM studentUITM) {
        final StudentUITM[] newArray = new StudentUITM[students.length + START_ARRAY_SIZE];
        System.arraycopy(students, 0, newArray, 0, students.length);
        students = newArray;
        students[students.length] = studentUITM;
    }

    public void update(final StudentUITM studentUITM) {//worker
        StudentUITM current = this.findById(studentUITM.getId());
        if (current != null) {
            current.setName(studentUITM.getName());
            current.setSurname(studentUITM.getSurname());
            current.setYearOfBirth(studentUITM.getYearOfBirth());
            current.setGradesForTheExam(studentUITM.getGradesForTheExam());
        }
    }

    public StudentUITM findById(final String id) {
        int i;
        for (i = 0; i < students.length; i++) {
            if (students[i] == null) {
                break;
            }
            if (Objects.equals(students[i].getId(), id)) {
                return students[i];
            }
        }
        return null;
    }

    public StudentUITM[] findAll() {
        int sizeResultArray = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                sizeResultArray = i;
                break;
            }
        }
        final StudentUITM[] newResultArray = new StudentUITM[sizeResultArray];
        System.arraycopy(students, 0, newResultArray, 0, sizeResultArray);
        return newResultArray;
    }


    public void delete(final String id) {
        int studentDeletePoint = 0;
        for (int i = 0; i < students.length; i++) {
            if (null != students[i] && id.equals(students[i].getId())) {
                students[i] = null;
                studentDeletePoint = i;
                break;
            }
        }
        final StudentUITM[] newArray = new StudentUITM[students.length];

        System.arraycopy(students, 0, newArray, 0, studentDeletePoint);
        System.arraycopy(students, studentDeletePoint + 1, newArray, studentDeletePoint, students.length - (studentDeletePoint + 1));
        students = newArray;

    }


    private String generateId() {
        String id;
        do {
            id = this.generateStringUUID();
        } while (this.existId(id));
        return id;
    }

    private boolean existId(final String id) {
        for (final StudentUITM studentUITM : students) {
            if (null == studentUITM) break;
            if (id.equals(studentUITM.getId())) {
                return true;
            }
        }
        return false;
    }

    private String generateStringUUID() {
        return UUID.randomUUID().toString();
    }

}
