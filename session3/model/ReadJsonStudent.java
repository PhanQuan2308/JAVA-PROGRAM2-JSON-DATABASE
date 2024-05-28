package session3.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import session3.entity.Students;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class ReadJsonStudent {
    private static final String JSON_FILE = "student.json";
    private static Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .setPrettyPrinting()
            .create();

    public List<Students> readStudent() {
        List<Students> students = new ArrayList<>();

        try {
            Type studentListType = new TypeToken<ArrayList<Students>>(){}.getType();
            students = gson.fromJson(new FileReader(JSON_FILE), studentListType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }
}
