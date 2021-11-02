import java.sql.*;

public class DatabasePerfumes {
    static final String DB_URL = "jdbc:mysql://?";
    static final String User = "?";
    static final String Password = "?";
    static Connection conn = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        try {
            //Return connection instance
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, User, Password);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            selectExample();
            addExample();
            insertExample();
            updateExample();
            deleteExample();
            insertWithValue("Chanel", "Chance", "Eau de Toilette Spray", 50, "Fresh");
        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());
        }
        // finally {
        // try {
        //    stmt.close();
        //    conn.close();
        //  }
        // catch (SQLException ex)
        //  {

        // }
        // }
    }

    public static void selectExample() throws SQLException {
        String sql = "SELECT * FROM Perfumes";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("Brand") + " " + rs.getString("Title"));
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        rs.close();
    }

    public static void addExample() throws SQLException {
        String sql = "ALTER TABLE Perfumes ADD Gender VARCHAR(15)";
        System.out.println("Added: " + stmt.executeUpdate(sql));
    }

    public static void insertExample() throws SQLException {
        String sql = "INSERT INTO shoes(Brand, Title, Type, Capacity, Category) VALUES ('Christian Dior','Miss Dior',‘Eau de Parfum’, 100,'Floral')";
        System.out.println("Inserted: " + stmt.executeUpdate(sql));
    }

    public static void updateExample() throws SQLException {
        String sql = """
                UPDATE Perfumes
                SET Title = 'Coco Mademoiselle'
                WHERE Brand = 'Chanel'""";
        System.out.println("Updated: " + stmt.executeUpdate(sql));
    }

    public static void deleteExample() throws SQLException {
        String sql = "DELETE FROM Perfumes WHERE Title='Bloom'";
        System.out.println("Deleted: " + stmt.executeUpdate(sql));
    }

    public static void insertWithValue(String Brand, String Title, String Type, int Capacity,
                                       String Category) throws SQLException {
        String sql = "INSERT INTO Perfumes (Brand, Title, Type, Capacity, Category) VALUES (+" +
                Brand + ",'" + Title + "'," + Type + ",'" + Capacity + "','" + Category + "')";
        stmt.execute(sql);
    }
}
