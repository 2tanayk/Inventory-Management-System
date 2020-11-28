package DataClasses;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Inventory implements Observable {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty quantity;
    private ImageView image;
    private SimpleStringProperty description;
    private SimpleStringProperty category;
    private SimpleStringProperty url;

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(SimpleIntegerProperty price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(SimpleIntegerProperty quantity) {
        this.quantity = quantity;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(SimpleStringProperty description) {
        this.description = description;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(SimpleStringProperty category) {
        this.category = category;
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(SimpleStringProperty url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return getClass().getName() + "\nid=" + id + " name=" + name + " price=" + price + " quantity=" + quantity +
                " " +
                "image=" + image + " description=" + description + " category=" + category;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        System.out.println("There has been a change!");

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
