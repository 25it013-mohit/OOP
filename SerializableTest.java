import java.io.*;

class StudentDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    StudentDetails(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class SerializableTest    {
    public static void main(String[] args) {
        StudentDetails[] students = {
                new StudentDetails(101, "Rahul", "rahul123"),
                new StudentDetails(102, "Priya", "priya456"),
                new StudentDetails(103, "Amit", "amit789")
        };

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("students.dat")
            );

            out.writeObject(students);
            out.close();

            System.out.println("Objects saved successfully.\n");

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("students.dat")
            );

            Student[] loadedStudents = (Student[]) in.readObject();
            in.close();

            System.out.println("Objects loaded successfully:\n");

            for (Student student : loadedStudents) {
                student.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}