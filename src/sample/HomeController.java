package sample;

import DataClasses.Inventory;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DAO.JDBCDao;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Tab inventoryTab;
    public Tab orderTab;
    public TableView<Inventory> inventoryTableView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("quantity"));
        imgCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("image"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("description"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));

        try {
            inventoryTableView.setItems(getInventory());
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
        Inventory inventory = jdbcDao.getRow();
        inventoryRow.add(inventory);

        return inventoryRow;
    }

    public void addProductEntry(ActionEvent event) {
    }

    public void updateProductEntry(ActionEvent event) {
    }

    public void deleteProductEntry(ActionEvent event) {
    }
}
