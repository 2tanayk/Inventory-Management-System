package sample;

import DataClasses.Customer;
import DataClasses.Inventory;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import sample.DAO.JDBCDao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

public class AddCustomerWindowController {

    public TextField customerFirstName;
    public TextField customerLastName;
    public TextField customerEmail;
    public DatePicker dOO;
    public DatePicker dOD;
    public TextField customerOrder;
    public TextField customerPrice;
    public TextField customerQuantity;
    public Button addCustomerBtn;

    public TableView<Customer> sOrdersTableView;
    public TableView<Inventory> sInventoryTableView;


    public void initData(ObservableList<Inventory> mItems) throws Exception {

        System.out.println(mItems);
        //System.out.println(new JDBCDao().buildHelperMap());
        //mItems.remove(2);
        //mItems.get(0).setQuantity(new SimpleIntegerProperty(10));
        //sInventoryTableView.refresh();
        System.out.println(mItems);
    }


    public void addCustomerOrder(ActionEvent event) throws Exception {
        ObservableList<Inventory> mItems = sInventoryTableView.getItems();

        JDBCDao jdbcDao = new JDBCDao();
        HashMap<String, Integer> helperHashMap = jdbcDao.buildHelperMap();
        // mItems.get(0).setQuantity(new SimpleIntegerProperty(10));
        //sInventoryTableView.refresh();

        String firstName = customerFirstName.getText();
        String lastName = customerLastName.getText();
        String email = customerEmail.getText();
        Date dateOfOrder = Date.valueOf(dOO.getValue());
        Date dateOfDelivery = Date.valueOf(dOD.getValue());
        //LocalDate dateOfDelivery = dOD.getValue();
        //I will grab hold of this
        String productName = customerOrder.getText();

        String price = customerPrice.getText();
        int qty = Integer.parseInt(customerQuantity.getText());

        int reqId = helperHashMap.get(productName);


        for (Inventory i : mItems) {
            System.out.println("looping");
            if (i.getId() == reqId) {
                i.setQuantity(new SimpleIntegerProperty(i.getQuantity() - qty));
                //i.setQuantity(new SimpleIntegerProperty(i.getQuantity() - qty));
                sInventoryTableView.refresh();
                jdbcDao.updateInventoryField(i, "qty", reqId);
                break;
            }
        }


        System.out.println(firstName + " " + lastName + " " + email + " " + dateOfOrder + " " + dateOfDelivery + " " + productName + " " + price + " " + qty);

        Customer nCustomer = new Customer();
        nCustomer.setSrno(new SimpleIntegerProperty(1));
        nCustomer.setFirstName(new SimpleStringProperty(firstName));
        nCustomer.setLastName(new SimpleStringProperty(lastName));
        nCustomer.setEmail(new SimpleStringProperty(email));
        nCustomer.setOrderDate(new SimpleObjectProperty<>(dateOfOrder));
        nCustomer.setDeliveryDate(new SimpleObjectProperty<>(dateOfDelivery));
        nCustomer.setProductName(new SimpleStringProperty(productName));
        nCustomer.setProductPrice(new SimpleIntegerProperty(Integer.parseInt(price)));
        nCustomer.setProductQuantity(new SimpleIntegerProperty(qty));
        nCustomer.setTotalPrice(new SimpleIntegerProperty(nCustomer.getProductPrice() * nCustomer.getProductQuantity()));

        sOrdersTableView.getItems().add(nCustomer);

        jdbcDao.insertProductToOrders(nCustomer);


    }
}
