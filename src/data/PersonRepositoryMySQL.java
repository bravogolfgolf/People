package data;

class PersonRepositoryMySQL {
    void connect() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

    }
}
