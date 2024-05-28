package session3.model;

import session3.entity.Students;

import java.util.ArrayList;
import java.util.List;

public class SearchStudent {
    public List<Students> searchByName(String name, List<Students> students) {
        List<Students> result = new ArrayList<>();
        for (Students student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Students> searchByEmail(String email, List<Students> students) {
        List<Students> result = new ArrayList<>();
        for (Students student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                result.add(student);
            }
        }
        return result;
    }
}
