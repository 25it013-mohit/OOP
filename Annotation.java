import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "StudentID")
    private int id;

    @Column(name = "StudentName")
    private String name;

    @Column(name = "StudentAge")
    private int age;

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class RowMapper {

    public static <T> T populate(
            Class<T> clazz, String[] header, String[] data)
            throws Exception {

        if (header.length != data.length) {
            throw new IllegalArgumentException(
                    "Header and data lengths must match");
        }

        Map<String, String> row = new HashMap<>();

        for (int i = 0; i < header.length; i++) {
            row.put(header[i], data[i]);
        }

        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = field.getAnnotation(Column.class);
            String columnName = column.name();

            // Missing columns are skipped, leaving default field values.
            if (!row.containsKey(columnName)) {
                continue;
            }

            String value = row.get(columnName);
            field.setAccessible(true);

            if (field.getType() == String.class) {
                field.set(obj, value);
            } else if (field.getType() == int.class) {
                field.setInt(obj, Integer.parseInt(value));
            } else if (field.getType() == double.class) {
                field.setDouble(obj, Double.parseDouble(value));
            } else if (field.getType() == boolean.class) {
                field.setBoolean(obj, Boolean.parseBoolean(value));
            }
        }

        return obj;
    }
}

public class Annotation {
    public static void main(String[] args) throws Exception {
        String[] header = {"StudentName", "StudentID"};
        String[] data = {"Rahul", "101"};

        Student student = RowMapper.populate(Student.class, header, data);
        student.display();
    }
}