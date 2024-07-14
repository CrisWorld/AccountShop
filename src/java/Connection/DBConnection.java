
package Connection;

/**
 *
 * @author CHUC DY
 */
import java.sql.*;

public class DBConnection {
    private static String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String username = "sa";
    private static String password = "thienbao";
    private static String jdbcURL = "jdbc:sqlserver://DESKTOP-V2LFEJ4;databaseName=shop_game;encrypt=true;trustServerCertificate=true;loginTimeout=30";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName(driverClass);
            con = (Connection) DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return con;
    }
}