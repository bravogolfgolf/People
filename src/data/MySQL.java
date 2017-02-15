package data;

class MySQL {
    void connect() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

    }
}
