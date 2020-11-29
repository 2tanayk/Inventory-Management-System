package DataClasses;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import sample.DAO.JDBCDao;

public class DatePickerCell<S, T> extends TableCell<Customer, Date> {

    private DatePicker datePicker;
    private ObservableList<Customer> rDate;
    //for retrieval of the row we need
    ObservableList<Customer> selectedRow;
    String wc2;
    TableView<Customer> tableView;
    Customer customer;
    int pos;

    public DatePickerCell(ObservableList<Customer> listCustomers, String wc2) {
        super();

        this.wc2 = wc2;

        this.rDate = listCustomers;

        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

    @Override
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                setDatepikerDate(smp.format(item));
                setText(smp.format(item));
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDatepikerDate(String dateAsStr) {

        LocalDate ld = null;
        int jour, mois, annee;

        jour = mois = annee = 0;
        try {
            jour = Integer.parseInt(dateAsStr.substring(0, 2));
            mois = Integer.parseInt(dateAsStr.substring(3, 5));
            annee = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(annee, mois, jour);
        datePicker.setValue(ld);
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("jj/mm/aaaa");
        datePicker.setEditable(true);

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                System.out.println("Action!");
                LocalDate date = datePicker.getValue();


                int index = getIndex();

                SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
                cal.set(Calendar.MONTH, date.getMonthValue() - 1);
                cal.set(Calendar.YEAR, date.getYear());

                setText(smp.format(cal.getTime()));
                commitEdit(cal.getTime());

                if (null != getrDate()) {
                    if (wc2.equals("doo")) {
                        getrDate().get(index).setOrderDate(new SimpleObjectProperty<java.sql.Date>(java.sql.Date.valueOf(date)));//String.valueOf(cal.getTime())
                        //smp.format(cal.getTime()
                    } else {
                        getrDate().get(index).setDeliveryDate(new SimpleObjectProperty<java.sql.Date>(java.sql.Date.valueOf(date)));//String.valueOf(cal.getTime())

                    }

                }
            }
        });


        setAlignment(Pos.CENTER);
    }

    @Override
    public void startEdit() {
        super.startEdit();
        System.out.println("Edit started!");
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        System.out.println("Edit canceled!");
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void commitEdit(Date date) {
        super.commitEdit(date);
        System.out.println("Edit committed!");

        tableView = getTableView();
        pos = tableView.getSelectionModel().getSelectedIndex();
        selectedRow = tableView.getSelectionModel().getSelectedItems();
        try {
            JDBCDao jdbcDao = new JDBCDao();

            customer = selectedRow.get(0);
            //java.sql.Date sDate = date.convertUtilToSql(date)
            if (wc2.equals("doo")) {
                System.out.println("in doo");
                customer.setOrderDate(new SimpleObjectProperty<java.sql.Date>(new java.sql.Date(date.getTime())));
                jdbcDao.updateOrdersField(customer, wc2, "");
            } else {
                System.out.println("in dod");
                customer.setDeliveryDate(new SimpleObjectProperty<java.sql.Date>(new java.sql.Date(date.getTime())));
                jdbcDao.updateOrdersField(customer, wc2, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        System.out.println(customer);

        // System.out.println(date);

//        datePicker.setOnSh
        //System.out.println(selectedRow.get(0).getOrderDate());

    }

    public ObservableList<Customer> getrDate() {
        return rDate;
    }

    public void setrDate(ObservableList<Customer> rDate) {
        this.rDate = rDate;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

}