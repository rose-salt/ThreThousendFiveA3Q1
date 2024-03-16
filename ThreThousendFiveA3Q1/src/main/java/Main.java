import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static Connection connection;
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "comp3005";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            getAllStudents();
            //should not add
            System.out.println("Adding student should not add successfully because of date \n");
            addStudent("Rachel", "Stewart", "rachelstewart3@cmail.carleton.ca", "2021-13-01");
            getAllStudents();
            addStudent("Rachel", "Stewart", "rachelstewart3@cmail.carleton.ca", "2021-09-01");
            getAllStudents();
            //should not update
            System.out.println("Updating student email should not update successfully because of duplicate email \n");
            updateStudentEmail(2, "john.doe@example.com");
            getAllStudents();
            updateStudentEmail(2, "jane.smith@gmail.com");
            getAllStudents();
            addStudent("Suzie", "Q", "suzieq@cmail.carleton.ca", "2021-10-01");
            getAllStudents();
            deleteStudent(5);
            getAllStudents();
        }
        catch(Exception e){
            System.out.println(e.getMessage()+ "\n");
        }
    }

    public static void getAllStudents()
    {
        try{
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            System.out.printf("id\t%-15s%-15s%-40s enrollment_date\n", "first_name", "last_name", "email");
            while(resultSet.next())
            {
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.printf("%-15s",resultSet.getString("first_name"));
                System.out.printf("%-15s",resultSet.getString("last_name"));
                System.out.printf("%-40s",resultSet.getString("email"));
                System.out.println(" " + resultSet.getString("enrollment_date"));
            }
            System.out.print("\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage()+ "\n");
        }
    }

    public static void addStudent(String first_name, String last_name, String email, String enrollment_date)
    {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES\n('%s', '%s', '%s', '%s');", first_name, last_name, email, enrollment_date));
            System.out.println("Student added successfully\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage()+ "\n");
        }
    }

    public static void updateStudentEmail(int student_id, String new_email)
    {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE students SET email = '%s' WHERE student_id = %d",new_email, student_id));
            System.out.println("Student email updated successfully\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage()+ "\n");
        }
    }

    public static void deleteStudent(int student_id)
    {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM students WHERE student_id = %d", student_id));
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
    }

}
