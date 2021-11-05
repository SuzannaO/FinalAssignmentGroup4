import java.sql.*;
import java.util.Scanner;

public class DatabasePerfumes {
    static final String DB_URL = "jdbc:mysql://group4database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/PERFUMES";
    static final String User = "admin";
    static final String Password = "Group4database";
    static Connection conn = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        try {
            System.out.println("Connecting to Perfumes database...");
            conn = DriverManager.getConnection(DB_URL, User, Password);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter brand:");
            String brand = input.nextLine();
            System.out.println("Please enter title:");
            String title = input.nextLine();
            System.out.println("Please enter type:");
            String type = input.nextLine();
            System.out.println("Please enter capacity(ml):");
            int capacityml = input.nextInt();
            System.out.println("Please enter the scent:");
            String scent = input.next();

            //selecting();
            insertingValue(brand, title, type, capacityml, scent);
           // adding();
           // inserting();
           // updating();
            //deleting();

        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());
        }

    }
    public static void selecting() throws SQLException {
        String sql = "SELECT * FROM Perfumes";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString("Brand") + " " + rs.getString("Title"));
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
        rs.close();
    }

    public static void insertingValue(String brand, String title, String type, int capacityml,
                                      String category) throws SQLException {
        String sql = "INSERT INTO Perfumes (Brand, Title, Type, Capacity, Category) VALUES ('" +
                brand + "','" + title + "','" + type + "'," + capacityml + ",'" + category + "')";
        stmt.execute(sql);
    }

    public static void adding() throws SQLException {
        System.out.println("An option to leave a comment has been added");
        String sql = "ALTER TABLE Perfumes ADD Comment VARCHAR(50)";
        System.out.println("Added: " + stmt.executeUpdate(sql));
    }

    public static void inserting() throws SQLException {
        String sql = "INSERT INTO Perfumes (Brand, Title, Type, Capacity, Category) VALUES ('D&G','Light Blue','Eau de Toilette', 100,'Floral')";
        System.out.println("Inserted: " + stmt.executeUpdate(sql));
    }

    public static void updating() throws SQLException {
        String sql = "UPDATE Perfumes\n" +
                "SET Title = 'Chance'\n" +
                "WHERE Brand = 'Chanel'";
        System.out.println("Updated: " + stmt.executeUpdate(sql));
    }

    public static void deleting() throws SQLException {
        String sql = "DELETE FROM Perfumes WHERE Title='Donna'";
        System.out.println("Deleted: " + stmt.executeUpdate(sql));
    }

}
