package sample;

import DataClasses.Inventory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DAO.JDBCDao;


public class AddProductWindowController {
    public TextField addProductId;
    public TextField addProductName;
    public TextField addProductPrice;
    public TextField addProductQuantity;
    public TextField addProductImage;
    public TextArea addProductDescription;
    public TextField addProductCategory;
    public Button addProductBtn;

    public TableView<Inventory> sInventoryTableView;


    public void addProductToInventory(ActionEvent event) throws Exception {
        String productId = addProductId.getText();
        String productName = addProductName.getText();
        String productPrice = addProductPrice.getText();
        String productQuantity = addProductQuantity.getText();
        String productImage = addProductImage.getText();
        String productDescription = addProductDescription.getText();
        String productCategory = addProductCategory.getText();

        System.out.println(productId + " " + productName + " " + productPrice + " " + productQuantity + " " + productImage + " " + productDescription + " " + productCategory);


        Inventory nItem = new Inventory();

        nItem.setId(new SimpleIntegerProperty(Integer.parseInt(addProductId.getText())));
        nItem.setName(new SimpleStringProperty(addProductName.getText()));
        nItem.setPrice(new SimpleIntegerProperty(Integer.parseInt(addProductPrice.getText())));
        nItem.setQuantity(new SimpleIntegerProperty(Integer.parseInt(addProductQuantity.getText())));
        nItem.setImage(new SimpleStringProperty(addProductImage.getText()));
        nItem.setDescription(new SimpleStringProperty(addProductDescription.getText()));
        nItem.setCategory(new SimpleStringProperty(addProductCategory.getText()));

        sInventoryTableView.getItems().add(nItem);

        JDBCDao jdbcDao=new JDBCDao();
        jdbcDao.insertProductToInventory(nItem);
    }
}
