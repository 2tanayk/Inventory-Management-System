package sample;

import DataClasses.Customer;
import DataClasses.Inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.DAO.JDBCDao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Tab inventoryTab;
    public Tab orderTab;
    @FXML
    private TableView<Inventory> inventoryTableView;

    public TableColumn<Inventory, Integer> idCol;
    public TableColumn<Inventory, String> nameCol;
    public TableColumn<Inventory, Integer> priceCol;
    public TableColumn<Inventory, Integer> qtyCol;
    public TableColumn<Inventory, String> imgCol;
    public TableColumn<Inventory, String> descriptionCol;
    public TableColumn<Inventory, String> categoryCol;

    public Button addBtn;
    public Button updateBtn;
    public Button deleteBtn;

    public TableView<Customer> ordersTableView;
    public TableColumn<Customer, Integer> srnoCol;
    public TableColumn<Customer, String> fnameCol;
    public TableColumn<Customer, String> lnameCol;
    public TableColumn<Customer, String> emailCol;
    public TableColumn<Customer, LocalDate> dooCol;
    public TableColumn<Customer, LocalDate> dodCol;
    public TableColumn<Customer, String> productCol;
    public TableColumn<Customer, Integer> priceOrderCol;
    public TableColumn<Customer, Integer> qtyOrderCol;
    public TableColumn<Customer, Integer> totalCol;

    public Button cAddBtn;
    public Button cUpdateBtn;
    public Button cDeleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("quantity"));
        imgCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("image"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("description"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));

        srnoCol.setCellValueFactory(new PropertyValueFactory<>("srno"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        dooCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        dodCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        productCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceOrderCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        qtyOrderCol.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        try {
            inventoryTableView.setItems(getInventory());
            ordersTableView.setItems(getCustomer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Inventory> getInventory() throws Exception {
        ObservableList<Inventory> inventoryRow = FXCollections.observableArrayList();

//        Inventory inventory = new Inventory();
//        inventory.setId(new SimpleIntegerProperty(1));
//        inventory.setName(new SimpleStringProperty("Car"));
//        inventory.setPrice(new SimpleIntegerProperty(1000));
//        inventory.setQuantity(new SimpleIntegerProperty(12));
//        inventory.setImage(new SimpleStringProperty("No Image"));
//        inventory.setDescription(new SimpleStringProperty("JHEDVHEDHWDVW"));
//        inventory.setCategory(new SimpleStringProperty("Automobiles"));

        JDBCDao jdbcDao = new JDBCDao();

        List<Inventory> inventoryRowList = jdbcDao.getRowInventory();

        //Inventory inventory = jdbcDao.getRow();
        inventoryRow.addAll(inventoryRowList);

        return inventoryRow;
    }

    public ObservableList<Customer> getCustomer() throws Exception {
        ObservableList<Customer> customerRow = FXCollections.observableArrayList();

//        Inventory inventory = new Inventory();
//        inventory.setId(new SimpleIntegerProperty(1));
//        inventory.setName(new SimpleStringProperty("Car"));
//        inventory.setPrice(new SimpleIntegerProperty(1000));
//        inventory.setQuantity(new SimpleIntegerProperty(12));
//        inventory.setImage(new SimpleStringProperty("No Image"));
//        inventory.setDescription(new SimpleStringProperty("JHEDVHEDHWDVW"));
//        inventory.setCategory(new SimpleStringProperty("Automobiles"));


        JDBCDao jdbcDao = new JDBCDao();
        List<Customer> ordersRowList = jdbcDao.getRowOrders();
        // Customer customer = jdbcDao.getRowOrders();
        customerRow.addAll(ordersRowList);

        return customerRow;
    }

    public void addProductEntry(ActionEvent event) throws IOException {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/addProductWindow.fxml"));
        Parent root = fxmlLoader.load();
        AddProductWindowController addProductWindowController = (AddProductWindowController) fxmlLoader.getController();
        addProductWindowController.sInventoryTableView = inventoryTableView;

        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);

        stage.setTitle("Add Product");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void updateProductEntry(ActionEvent event) throws IOException {

        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/updateProductWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Update Product");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void deleteProductEntry(ActionEvent event) throws IOException {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/deleteProductWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Remove Product");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addCustomerEntry(ActionEvent event) throws IOException {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/deleteCustomerWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Add Order");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void updateCustomerEntry(ActionEvent event) throws IOException {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/updateCustomerWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Update Order");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void deleteCustomerEntry(ActionEvent event) throws IOException {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/deleteCustomerWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Delete Order");
        stage.setScene(new Scene(root));
        stage.show();
    }
}


