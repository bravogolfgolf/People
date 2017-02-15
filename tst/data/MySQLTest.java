package data;

import org.junit.Test;

public class MySQLTest {


    @Test
    public void databaseShouldAllowConnection() throws ClassNotFoundException {
        MySQL mySQL = new MySQL();
        mySQL.connect();

    }

}
