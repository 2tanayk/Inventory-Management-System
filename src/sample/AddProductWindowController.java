package sample;

import DataClasses.Inventory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.DAO.JDBCDao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AddProductWindowController {
    public TextField addProductId;
    public TextField addProductName;
    public TextField addProductPrice;
    public TextField addProductQuantity;
    public Button addProductImage;
    public TextArea addProductDescription;
    public TextField addProductCategory;
    public Button addProductBtn;

    private FileChooser fileChooser;
    private File filePath;

    public TableView<Inventory> sInventoryTableView;

    Inventory nItem = new Inventory();


    public void addProductToInventory(ActionEvent event) throws Exception {
        String productId = addProductId.getText();
        String productName = addProductName.getText();
        String productPrice = addProductPrice.getText();
        String productQuantity = addProductQuantity.getText();
        // String productImage = addProductImage.getText();
        String productDescription = addProductDescription.getText();
        String productCategory = addProductCategory.getText();

        System.out.println(productId + " " + productName + " " + productPrice + " " + productQuantity + " " + " " + productDescription + " " + productCategory);
//productImage

        // nItem = new Inventory();

        nItem.setId(new SimpleIntegerProperty(Integer.parseInt(addProductId.getText())));
        nItem.setName(new SimpleStringProperty(addProductName.getText()));
        nItem.setPrice(new SimpleIntegerProperty(Integer.parseInt(addProductPrice.getText())));
        nItem.setQuantity(new SimpleIntegerProperty(Integer.parseInt(addProductQuantity.getText())));
        //nItem.setImage(addProductImage);//new SimpleStringProperty(addProductImage.getText())
        nItem.setDescription(new SimpleStringProperty(addProductDescription.getText()));
        nItem.setCategory(new SimpleStringProperty(addProductCategory.getText()));

        sInventoryTableView.getItems().add(nItem);

        JDBCDao jdbcDao = new JDBCDao();
        jdbcDao.insertProductToInventory(nItem);
    }

    public void addProductImageFromDrive(ActionEvent event) {
        Stage gStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");


        File userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        filePath = fileChooser.showOpenDialog(gStage);
        String nUrl = filePath.toString();

        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView imgV = new ImageView(image);
            imgV.setFitHeight(50);
            imgV.setFitWidth(50);
            imgV.setPreserveRatio(true);

            nItem.setUrl(new SimpleStringProperty(nUrl));
            nItem.setImage(imgV);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

