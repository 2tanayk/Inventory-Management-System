package DataClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {
    private SimpleIntegerProperty srno;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private Date orderDate;
    private Date deliveryDate;
    private SimpleStringProperty productName;
    private SimpleIntegerProperty productPrice;
    private SimpleIntegerProperty productQuantity;
    private SimpleIntegerProperty totalPrice;

    public int getSrno() {
        return srno.get();
    }

    public void setSrno(SimpleIntegerProperty srno) {
        this.srno = srno;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(SimpleStringProperty productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(SimpleIntegerProperty productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity.get();
    }

    public void setProductQuantity(SimpleIntegerProperty productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(SimpleIntegerProperty totalPrice) {
        this.totalPrice = totalPrice;
    }
}
