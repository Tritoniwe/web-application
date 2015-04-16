package core;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataAccessLayer {

    /**
     * Method which makes connection to PostgreSQL DB
     *
     * @return Connection to DB
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost/UsersDB";
        Properties props = new Properties();
        props.setProperty("user", "admin");
        props.setProperty("password", "admin");
        return DriverManager.getConnection(url, props);

    }


}
