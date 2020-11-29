package sample;

import DataClasses.Inventory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UpdateProductWindowController {
    public TableView<Inventory> sInventoryTableView;

    public void updateProductToInventory(TableColumn.CellEditEvent edittedCell) {
        TableView.TableViewSelectionModel<Inventory> selectionModel = sInventoryTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        Inventory selectedItem = (Inventory) selectionModel.getSelectedItems();
        selectedItem.setId((SimpleIntegerProperty) edittedCell.getNewValue());


    }
}
