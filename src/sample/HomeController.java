package sample;

import DataClasses.Customer;
import DataClasses.Inventory;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import sample.DAO.JDBCDao;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
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
    public TableColumn<Inventory, ImageView> imgCol;
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
    public TableColumn<Customer, Date> dooCol;
    public TableColumn<Customer, Date> dodCol;
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
            System.out.println("Hsnsbsb");
            ordersTableView.setItems(getCustomer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        inventoryTableView.setEditable(true);
        ordersTableView.setEditable(true);
        System.out.println(inventoryTableView.isEditable());
        System.out.println(ordersTableView.isEditable());

        inventoryColEdit();
        ordersColEdit();
        //idCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    private void inventoryColEdit() {
        //Inventory inventory;
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(inventoryIntegerCellEditEvent -> {
            Inventory inventory =
                    inventoryIntegerCellEditEvent.getTableView().getItems().get(inventoryIntegerCellEditEvent.getTablePosition().getRow());
            inventory.setId(new SimpleIntegerProperty(inventoryIntegerCellEditEvent.getNewValue()));
            int nId = inventory.getId();
            System.out.println(nId);
        });

//        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        nameCol.setOnEditCommit(inventoryStringCellEditEvent -> {
//            Inventory inventory =
//                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent
//                    .getTablePosition().getRow());
//            inventory.setName(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
//            String nName = inventory.getName();
//            System.out.println(nName);
//
//            JDBCDao jdbcDao = new JDBCDao();
//            try {
//                jdbcDao.updateInventoryField(inventory, "prod_name", nName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(inventoryIntegerCellEditEvent -> {
            System.out.println("Old Value is : " + inventoryIntegerCellEditEvent.getOldValue());
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

//        imgCol.setCellFactory(new Callback<TableColumn<Inventory, ImageView>, TableCell<Inventory, ImageView>>() {
//            @Override
//            public TableCell<Inventory, ImageView> call(TableColumn<Inventory, ImageView> inventoryImageViewTableColumn) {
//                return null;
//            }
//        });
//        imgCol.setOnEditCommit(inventoryStringCellEditEvent -> {
//            Inventory inventory =
//                    inventoryStringCellEditEvent.getTableView().getItems().get(inventoryStringCellEditEvent.getTablePosition().getRow());
//            inventory.setImage(new SimpleStringProperty(inventoryStringCellEditEvent.getNewValue()));
//            String nImg = inventory.getImage();
//            System.out.println(nImg);
//
//            JDBCDao jdbcDao = new JDBCDao();
//            try {
//                jdbcDao.updateInventoryField(inventory, "img", nImg);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

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

    private void ordersColEdit() {
//        srnoCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        srnoCol.setOnEditCommit(ordersIntegerCellEditEvent -> {
//            Customer customer =
//                    ordersIntegerCellEditEvent.getTableView().getItems().get(ordersIntegerCellEditEvent
//                    .getTablePosition().getRow());
//            customer.setSrno(new SimpleIntegerProperty(ordersIntegerCellEditEvent.getNewValue()));
//            int nId = customer.getSrno();
//            System.out.println(nId);
//        });

        fnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fnameCol.setOnEditCommit(ordersStringCellEditEvent -> {
            Customer customer =
                    ordersStringCellEditEvent.getTableView().getItems().get(ordersStringCellEditEvent.getTablePosition().getRow());
            customer.setFirstName(new SimpleStringProperty(ordersStringCellEditEvent.getNewValue()));
            String nFirstName = customer.getFirstName();
            System.out.println(nFirstName);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateOrdersField(customer, "first_name", nFirstName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        lnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lnameCol.setOnEditCommit(ordersStringCellEditEvent -> {
            Customer customer =
                    ordersStringCellEditEvent.getTableView().getItems().get(ordersStringCellEditEvent.getTablePosition().getRow());
            customer.setLastName(new SimpleStringProperty(ordersStringCellEditEvent.getNewValue()));
            String nLastName = customer.getLastName();
            System.out.println(nLastName);

            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateOrdersField(customer, "last_name", nLastName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(ordersStringCellEditEvent -> {
            Customer customer =
                    ordersStringCellEditEvent.getTableView().getItems().get(ordersStringCellEditEvent.getTablePosition().getRow());
            customer.setEmail(new SimpleStringProperty(ordersStringCellEditEvent.getNewValue()));
            String nEmail = customer.getEmail();
            System.out.println(nEmail);
            JDBCDao jdbcDao = new JDBCDao();
            try {
                jdbcDao.updateOrdersField(customer, "email", nEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        dooCol.setCellFactory(new Callback<TableColumn<Customer, Date>, TableCell<Customer, Date>>() {
//            @Override
//            public TableCell<Customer, Date> call(TableColumn<Customer, Date> customerDateTableColumn) {
//                return null;
//            }
//        });
//        dooCol.setOnEditCommit(ordersDateCellEditEvent -> {
//            Customer customer =
//                    ordersDateCellEditEvent.getTableView().getItems().get(ordersDateCellEditEvent.getTablePosition
//                    ().getRow());
//            customer.setOrderDate(ordersDateCellEditEvent.getNewValue());
//            String nDoo = customer.getOrderDate().toString();
//            System.out.println(nDoo);
//        });

        //  dodCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
//        dodCol.setOnEditCommit(ordersDateCellEditEvent -> {
//            Customer customer =
//                    ordersDateCellEditEvent.getTableView().getItems().get(ordersDateCellEditEvent.getTablePosition
//                    ().getRow());
//            customer.setDeliveryDate(Date.valueOf(ordersDateCellEditEvent.getNewValue()));
//            String nDod = customer.getOrderDate().toString();
//            System.out.println(nDod);
//        });

//        productCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        productCol.setOnEditCommit(ordersStringCellEditEvent -> {
//            Customer customer =
//                    ordersStringCellEditEvent.getTableView().getItems().get(ordersStringCellEditEvent
//                    .getTablePosition().getRow());
//            customer.setProductName(new SimpleStringProperty(ordersStringCellEditEvent.getNewValue()));
//            String nProductName = customer.getProductName();
//            System.out.println(nProductName);
//
//            JDBCDao jdbcDao = new JDBCDao();
//            try {
//                jdbcDao.updateOrdersField(customer, "prod_name", nProductName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

//        priceOrderCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        priceOrderCol.setOnEditCommit(ordersIntegerCellEditEvent -> {
//            Customer customer =
//                    ordersIntegerCellEditEvent.getTableView().getItems().get(ordersIntegerCellEditEvent
//                    .getTablePosition().getRow());
//            customer.setProductPrice(new SimpleIntegerProperty(ordersIntegerCellEditEvent.getNewValue()));
//            int nPrice = customer.getProductPrice();
//            System.out.println(nPrice);
//
//            JDBCDao jdbcDao = new JDBCDao();
//            try {
//                jdbcDao.updateOrdersField(customer, "price", nPrice);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        qtyOrderCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtyOrderCol.setOnEditCommit(ordersIntegerCellEditEvent -> {
            ObservableList<Inventory> hObsL = inventoryTableView.getItems();
            System.out.println("Inventory:" + hObsL);

            int oQuantity = ordersIntegerCellEditEvent.getOldValue();//old quantity
            Customer customer =
                    ordersIntegerCellEditEvent.getTableView().getItems().get(ordersIntegerCellEditEvent.getTablePosition().getRow());
            String productName = customer.getProductName();
            System.out.println("Product Name is " + productName);

            customer.setProductQuantity(new SimpleIntegerProperty(ordersIntegerCellEditEvent.getNewValue()));
            int nQuantity = customer.getProductQuantity();
            System.out.println("Old Value: " + oQuantity + " New Value: " + nQuantity);
            //System.out.println(nQuantity);
            JDBCDao jdbcDao = new JDBCDao();
            HashMap<String, Integer> helperHashMap = null;
            try {
                helperHashMap = jdbcDao.buildHelperMap();
                System.out.println(helperHashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int reqId = helperHashMap.get(productName);
            System.out.println("Required Id is:" + reqId);

            int d = oQuantity - nQuantity;

            System.out.println("dIFFERENCE:" + d);


            for (Inventory i : hObsL) {
                System.out.println("looping");
                if (i.getId() == reqId) {
                    if (d > 0) {
                        System.out.println("in d>0");
                        i.setQuantity(new SimpleIntegerProperty(i.getQuantity() + d));
                        inventoryTableView.refresh();
                        try {
                            jdbcDao.updateInventoryField(i, "qty", reqId);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
                        }
                        break;
                    } else if (d < 0) {
                        System.out.println("in d<0");
                        i.setQuantity(new SimpleIntegerProperty(i.getQuantity() + d));
                        inventoryTableView.refresh();
                        try {
                            jdbcDao.updateInventoryField(i, "qty", reqId);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        i.setQuantity(new SimpleIntegerProperty(0));
                        inventoryTableView.refresh();
                        try {
                            jdbcDao.updateInventoryField(i, "qty", reqId);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
                        }
                        break;
                    }
                    //i.setQuantity(new SimpleIntegerProperty(i.getQuantity() - qty));
                }
            }
            customer.setTotalPrice(new SimpleIntegerProperty(nQuantity * customer.getProductPrice()));
            ordersTableView.refresh();

            try {
                jdbcDao.updateOrdersField(customer, "qty", nQuantity);
                jdbcDao.updateOrdersField(customer, "total", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

//        totalCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        totalCol.setOnEditCommit(ordersIntegerCellEditEvent -> {
//            Customer customer =
//                    ordersIntegerCellEditEvent.getTableView().getItems().get(ordersIntegerCellEditEvent
//                    .getTablePosition().getRow());
//            customer.setTotalPrice(new SimpleIntegerProperty(ordersIntegerCellEditEvent.getNewValue()));
//            int nTotal = customer.getProductQuantity();
//            System.out.println(nTotal);
//        });

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

        ObservableList<Inventory> inventoryRowList = jdbcDao.getRowInventory();

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

    public void addCustomerEntry(ActionEvent event) throws Exception {
        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/addCustomerWindow.fxml"));
        Parent root = fxmlLoader.load();

        AddCustomerWindowController addCustomerWindowController =
                fxmlLoader.getController();
        addCustomerWindowController.sOrdersTableView = ordersTableView;
        addCustomerWindowController.sInventoryTableView = inventoryTableView;
        addCustomerWindowController.initData(inventoryTableView.getItems());


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

    public void deleteCustomerEntry(ActionEvent event) throws Exception {
//        Stage parentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/deleteCustomerWindow.fxml"));
//        Parent root = fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(parentWindow);
//        stage.setTitle("Delete Order");
//        stage.setScene(new Scene(root));
//        stage.show();

        ObservableList<Customer> selectedRow = ordersTableView.getSelectionModel().getSelectedItems();
        ObservableList<Customer> allRows = ordersTableView.getItems();


        ObservableList<Inventory> mItems = inventoryTableView.getItems();

        Customer selectedCustomer = selectedRow.get(0);
        int srno = selectedCustomer.getSrno();
        String productName = selectedCustomer.getProductName();
        int qty = selectedCustomer.getProductQuantity();

        JDBCDao jdbcDao = new JDBCDao();
        HashMap<String, Integer> helperHashMap = jdbcDao.buildHelperMap();

        int reqId = helperHashMap.get(productName);


        for (Inventory i : mItems) {
            System.out.println("looping");
            if (i.getId() == reqId) {
                i.setQuantity(new SimpleIntegerProperty(i.getQuantity() + qty));
                inventoryTableView.refresh();
                jdbcDao.updateInventoryField(i, "qty", reqId);
                break;
            }
        }

        allRows.removeAll(selectedRow);
        jdbcDao.deleteCustomerFromOrders(srno);


//    public void editIdCol(TableColumn.CellEditEvent<Inventory, Integer> inventoryIntegerCellEditEvent) {
//        System.out.println("Im being edited!");
//
//    }
    }
}


        ObservableList<Inventory> mItems = inventoryTableView.getItems();

        Customer selectedCustomer = selectedRow.get(0);
        int srno = selectedCustomer.getSrno();
        String productName = selectedCustomer.getProductName();
        int qty = selectedCustomer.getProductQuantity();

        JDBCDao jdbcDao = new JDBCDao();
        HashMap<String, Integer> helperHashMap = jdbcDao.buildHelperMap();

        int reqId = helperHashMap.get(productName);


        for (Inventory i : mItems) {
            System.out.println("looping");
            if (i.getId() == reqId) {
                i.setQuantity(new SimpleIntegerProperty(i.getQuantity() + qty));
                inventoryTableView.refresh();
                jdbcDao.updateInventoryField(i, "qty", reqId);
                break;
            }
        }

        allRows.removeAll(selectedRow);
        jdbcDao.deleteCustomerFromOrders(srno);


//    public void editIdCol(TableColumn.CellEditEvent<Inventory, Integer> inventoryIntegerCellEditEvent) {
//        System.out.println("Im being edited!");
//
//    }
    }
}
