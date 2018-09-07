package resources;
import java.sql.*;


public class MySqlConnector {
    public Connection con = null;
    private static volatile MySqlConnector mySqlConnectorInstance=null;
    private MySqlConnector(){
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "imdb_Api";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "root@123";

        try {
            Class.forName(driver);
            con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MySqlConnector getConnection(){
        if(mySqlConnectorInstance == null) {
            synchronized (MySqlConnector.class) {
                if(mySqlConnectorInstance==null) {
                    mySqlConnectorInstance = new MySqlConnector();
                }
            }
        }
        return mySqlConnectorInstance;
    }
}
