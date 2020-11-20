package sample.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbcdemo";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "vhFMHCb$&*^%Uw*#";
    private static final String INSERT_QUERY = "insert into registration (full_name, email_id, password) values (?, " +
            "?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";

    public void insertRecord(String fname, String email, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY);
        preparedStatement.setString(1, fname);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);

        int count = preparedStatement.executeUpdate();
        System.out.println("Rows affected " + count);

        preparedStatement.close();
        con.close();
    }

    public boolean validate(String email, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);


        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }

        preparedStatement.close();
        con.close();
        return false;
    }

}
