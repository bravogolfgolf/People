package data;

import org.junit.Test;

public class PersonRepositoryMySQLTest {


    @Test
    public void databaseShouldAllowConnection() throws ClassNotFoundException {
        PersonRepositoryMySQL mySQL = new PersonRepositoryMySQL();
        mySQL.connect();

    }

}
