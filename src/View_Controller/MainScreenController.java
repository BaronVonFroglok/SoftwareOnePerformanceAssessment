package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    Inventory inventory;

    @FXML
    private TextField searchPartsTextField;
    @FXML
    private TextField searchProductsTextField;
    @FXML
    private TableView partsTableView;
    @FXML
    private TableView productsTableView;
    private ObservableList<Part> partTableList = FXCollections.observableArrayList();


    public MainScreenController(Inventory inventory) {
        this.inventory = inventory;
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
    }


    public void AddPartClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partTableList.setAll(inventory.getAllParts());
        partsTableView.setItems(partTableList);
        partsTableView.refresh();
    }
}
