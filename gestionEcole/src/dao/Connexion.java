package dao;

import java.sql.Connection;
import java.sql.DriverManager;

 
public abstract class Connexion {
 

    static Connection conn = null;
    public static Connection createConnection (String url, String user, String password ) throws Exception {
    
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    
    public static Connection getConnection ()
    {
    	return conn;
    }
    
}