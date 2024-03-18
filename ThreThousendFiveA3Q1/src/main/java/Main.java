import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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
            Scanner scanner = new Scanner(System.in);
            while(true)
            {
                System.out.println("What do you want to do?\n(1)Print all students\n(2)Add a student\n(3)Update a student\n(4)Delete a student\n(0)Exit\n");
                System.out.print("Enter your selection:");
                String choice = scanner.nextLine();
                if(choice.equals("1"))
                {
                    getAllStudents();
                }
                else if(choice.equals("2"))
                {
                    System.out.print("Enter first name:");
                    String f_name = scanner.nextLine();
                    System.out.print("Enter last name:");
                    String l_name = scanner.nextLine();
                    System.out.print("Enter email:");
                    String email = scanner.nextLine();
                    System.out.print("Enter enrollment date (YYYY-MM-DD):");
                    String date = scanner.nextLine();
                    addStudent(f_name, l_name, email, date);
                }
                else if(choice.equals("3"))
                {
                    int id;
                    while(true) {
                        System.out.print("Enter student id:");
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid must be integer");
                        }
                    }
                    System.out.print("Enter email:");

                    String email = scanner.nextLine();
                    updateStudentEmail(id, email);
                }
                else if(choice.equals("4"))
                {
                    int id;
                    while(true) {
                        System.out.print("Enter student id:");
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid must be integer");
                        }
                    }
                    deleteStudent(id);
                }
                else if(choice.equals("0"))
                {
                    System.out.println("Exiting");
                    break;
                }
                else
                {
                    System.out.println("ERROR: Invalid choice");
                }
            }
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
