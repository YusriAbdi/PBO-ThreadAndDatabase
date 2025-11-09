import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/apotek_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Gagal terkoneksi DB: " + e.getMessage());
            return null;
        }
    }
}