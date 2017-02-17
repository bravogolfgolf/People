package data;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionTest {


    @Ignore
    public void testDatabaseConnection() throws ClassNotFoundException, SQLException {
        System.setProperty("javax.net.debug","all");
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        Connection connection = DriverManager.getConnection(url,"briangibson","sKzuP3RMF");
        connection.close();
    }
}
