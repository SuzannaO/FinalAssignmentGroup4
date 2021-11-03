import java.sql.*;

public class DatabasePerfumes {
    static final String DB_URL = "jdbc:mysql://group4database.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/Perfumes";
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

            selecting();
            adding();
            inserting();
            updating();
            deleting();
            insertingValue("Chanel", "Chance", "Eau de Toilette Spray", 50, "Fresh");
        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());
        }

    }

    public static void selecting() throws SQLException {
        String sql = "SELECT * FROM Perfumes";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("Brand") + " " + rs.getString("Title"));
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        rs.close();
    }

    public static void adding() throws SQLException {
        String sql = "ALTER TABLE Perfumes ADD Gender VARCHAR(15)";
        System.out.println("Added: " + stmt.executeUpdate(sql));
    }

    public static void inserting() throws SQLException {
        String sql = "INSERT INTO Perfumes (Brand, Title, Type, Capacity, Category) VALUES ('Christian Dior','Miss Dior',‘Eau de Parfum’, 100,'Floral')";
        System.out.println("Inserted: " + stmt.executeUpdate(sql));
    }

    public static void updating() throws SQLException {
        String sql = "UPDATE Perfumes\n" +
                "SET Title = 'Coco Mademoiselle'\n" +
                "WHERE Brand = 'Chanel'";
        System.out.println("Updated: " + stmt.executeUpdate(sql));
    }

    public static void deleting() throws SQLException {
        String sql = "DELETE FROM Perfumes WHERE Title='Bloom'";
        System.out.println("Deleted: " + stmt.executeUpdate(sql));
    }

    public static void insertingValue(String Brand, String Title, String Type, int Capacity,
                                      String Category) throws SQLException {
        String sql = "INSERT INTO Perfumes (Brand, Title, Type, Capacity, Category) VALUES (+" +
                Brand + ",'" + Title + "'," + Type + ",'" + Capacity + "','" + Category + "')";
        stmt.execute(sql);
    }
}
