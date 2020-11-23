package sample;

import DataClasses.Customer;
import DataClasses.Inventory;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

    @FXML
    private TableView<Customer> ordersTableView;

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
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        imgCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

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
        inventoryTableView.setEditable(true);
        System.out.println(inventoryTableView.isEditable());
        colEdit();
        //idCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void colEdit() {

        //Inventory inventory;
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(inventoryIntegerCellEditEvent -> {
            Inventory inventory =
                    inventoryIntegerCellEditEvent.getTableView().getItems().get(inventoryIntegerCellEditEvent.getTablePosition().getRow());
            inventory.setId(new SimpleIntegerProperty(inventoryIntegerCellEditEvent.getNewValue()));
            int nId = inventory.getId();
            System.out.println(nId);
        });

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(inventoryStringCellEditEvent -> {
            Inventory inventory =
                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent.getTablePosition().getRow());
            inventory.setName(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
            String nName = inventory.getName();
            System.out.println(nName);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "prod_name", nName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(inventoryIntegerCellEditEvent -> {
            Inventory inventory =
                    inventoryIntegerCellEditEvent.getTableView().getItems().get(inventoryIntegerCellEditEvent.getTablePosition().getRow());
            inventory.setPrice(new SimpleIntegerProperty(inventoryIntegerCellEditEvent.getNewValue()));
            int nPrice = inventory.getPrice();
            System.out.println(nPrice);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "price", nPrice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        qtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtyCol.setOnEditCommit(inventoryIntegerCellEditEvent -> {
            Inventory inventory =
                    inventoryIntegerCellEditEvent.getTableView().getItems().get(inventoryIntegerCellEditEvent.getTablePosition().getRow());
            inventory.setQuantity(new SimpleIntegerProperty(inventoryIntegerCellEditEvent.getNewValue()));
            int nQty = inventory.getQuantity();
            System.out.println(nQty);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "qty", nQty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        imgCol.setCellFactory(TextFieldTableCell.forTableColumn());
        imgCol.setOnEditCommit(inventoryStringCellEditEvent -> {
            Inventory inventory =
                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent.getTablePosition().getRow());
            inventory.setImage(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
            String nImg = inventory.getImage();
            System.out.println(nImg);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "img", nImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(inventoryStringCellEditEvent -> {
            Inventory inventory =
                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent.getTablePosition().getRow());
            inventory.setDescription(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
            String nDescription = inventory.getDescription();
            System.out.println(nDescription);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "des", nDescription);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(inventoryStringCellEditEvent -> {
            Inventory inventory =
                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent.getTablePosition().getRow());
            inventory.setCategory(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
            String nCategory = inventory.getCategory();
            System.out.println(nCategory);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateInventoryField(inventory, "category", nCategory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
        UpdateProductWindowController updateProductWindowController =
                (UpdateProductWindowController) fxmlLoader.getController();
        updateProductWindowController.sInventoryTableView = inventoryTableView;

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentWindow);
        stage.setTitle("Update Product");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void deleteProductEntry(ActionEvent event) throws Exception {
//        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/deleteProductWindow.fxml"));
//        Parent root = fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(parentWindow);
//        stage.setTitle("Remove Product");
//        stage.setScene(new Scene(root));
//        stage.show();
        ObservableList<Inventory> selectedRow = inventoryTableView.getSelectionModel().getSelectedItems();
        ObservableList<Inventory> allRows = inventoryTableView.getItems();

        JDBCDao jdbcDao = new JDBCDao();
        jdbcDao.deleteProductFromInventory(selectedRow.get(0).getId());


        allRows.removeAll(selectedRow);
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

//    public void editIdCol(TableColumn.CellEditEvent<Inventory, Integer> inventoryIntegerCellEditEvent) {
//        System.out.println("Im being edited!");
//
//    }
}


