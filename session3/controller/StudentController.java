package session3.controller;

import session3.entity.Students;
import session3.model.CreateJsonStudent;
import session3.model.ReadJsonStudent;
import session3.model.SearchStudent;
import session3.model.WriteJsonStudent;

import java.util.List;

public class StudentController {
    private CreateJsonStudent createJsonStudent = new CreateJsonStudent();
    private ReadJsonStudent readJsonStudent = new ReadJsonStudent();
    private SearchStudent searchStudent = new SearchStudent();
    private WriteJsonStudent writeJsonStudent = new WriteJsonStudent();

    public void createStudent(Students students){
        createJsonStudent.addStudentsToDatabase(List.of(students)); // Thêm sinh viên vào cơ sở dữ liệu
        writeJsonStudent.writeStudent();

    }

    public List<Students> readStudents() {
        return readJsonStudent.readStudent();
    }

    public List<Students> searchStudentsByName(String name, List<Students> students) {
        return searchStudent.searchByName(name, students);
    }

    public List<Students> searchStudentsByEmail(String email, List<Students> students) {
        return searchStudent.searchByEmail(email, students);
    }
}
