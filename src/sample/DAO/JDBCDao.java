package sample.DAO;

import DataClasses.Customer;
import DataClasses.Inventory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbcdemo";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "vhFMHCb$&*^%Uw*#";
    private static final String INSERT_QUERY = "insert into registration (full_name, email_id, password) values (?, " +
            "?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
    private static final String SELECT_ROW_QUERY_INVENTORY = "SELECT * FROM inventory";
    private static final String SELECT_ROW_QUERY_ORDERS = "SELECT * FROM orders";
    private static final String INSERT_INTO_INVENTORY = "INSERT INTO inventory (id,prod_name,price,qty,img,des," +
            "category) values (?,?,?,?,?,?,?)";


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

    public List<Inventory> getRowInventory() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ROW_QUERY_INVENTORY);

        List<Inventory> inventoryList = new ArrayList<>();

        //boolean result = rs.next();

        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setId(new SimpleIntegerProperty(rs.getInt(1)));
            inventory.setName(new SimpleStringProperty(rs.getString(2)));
            inventory.setPrice(new SimpleIntegerProperty(rs.getInt(3)));
            inventory.setQuantity(new SimpleIntegerProperty(rs.getInt(4)));
            inventory.setImage(new SimpleStringProperty(rs.getString(5)));
            inventory.setDescription(new SimpleStringProperty(rs.getString(6)));
            inventory.setCategory(new SimpleStringProperty(rs.getString(7)));

            inventoryList.add(inventory);
        }

        statement.close();
        con.close();

        return inventoryList;
    }

    public List<Customer> getRowOrders() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ROW_QUERY_ORDERS);

        // boolean result = rs.next();

        List<Customer> ordersList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setSrno(new SimpleIntegerProperty(rs.getInt(1)));
            customer.setFirstName(new SimpleStringProperty(rs.getString(2)));
            customer.setLastName(new SimpleStringProperty(rs.getString(3)));
            customer.setEmail(new SimpleStringProperty(rs.getString(4)));
            customer.setOrderDate(rs.getDate(5));
            customer.setDeliveryDate(rs.getDate(6));
            customer.setProductName(new SimpleStringProperty(rs.getString(7)));
            customer.setProductPrice(new SimpleIntegerProperty(rs.getInt(8)));
            customer.setProductQuantity(new SimpleIntegerProperty(rs.getInt(9)));
            customer.setTotalPrice(new SimpleIntegerProperty(rs.getInt(10)));

            ordersList.add(customer);
        }

        statement.close();
        con.close();

        return ordersList;
    }

    public void insertProductToInventory(Inventory inventory) throws Exception {
        //int id, String productName, int price, int qty, String img,String description, String category
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = con.prepareStatement(INSERT_INTO_INVENTORY);

        preparedStatement.setInt(1, inventory.getId());
        preparedStatement.setString(2, inventory.getName());
        preparedStatement.setInt(3, inventory.getPrice());
        preparedStatement.setInt(4, inventory.getQuantity());
        preparedStatement.setString(5, inventory.getImage());
        preparedStatement.setString(6, inventory.getDescription());
        preparedStatement.setString(7, inventory.getCategory());

        int count = preparedStatement.executeUpdate();
        System.out.println("Rows affected " + count);

        preparedStatement.close();
        con.close();
    }


}
