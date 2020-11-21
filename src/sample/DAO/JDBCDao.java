package sample.DAO;

import DataClasses.Inventory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class JDBCDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbcdemo";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "vhFMHCb$&*^%Uw*#";
    private static final String INSERT_QUERY = "insert into registration (full_name, email_id, password) values (?, " +
            "?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
    private static final String SELECT_ROW_QUERY = "SELECT * FROM inventory";

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

    public Inventory getRow() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ROW_QUERY);

        boolean result = rs.next();

        Inventory inventory = new Inventory();
        inventory.setId(new SimpleIntegerProperty(rs.getInt(1)));
        inventory.setName(new SimpleStringProperty(rs.getString(2)));
        inventory.setPrice(new SimpleIntegerProperty(rs.getInt(3)));
        inventory.setQuantity(new SimpleIntegerProperty(rs.getInt(4)));
        inventory.setImage(new SimpleStringProperty(rs.getString(5)));
        inventory.setDescription(new SimpleStringProperty(rs.getString(6)));
        inventory.setCategory(new SimpleStringProperty(rs.getString(7)));

        statement.close();
        con.close();

        return inventory;
    }


}
