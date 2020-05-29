package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyProductController implements Initializable {

    @FXML
    TextField partSearchTextField;
    @FXML
    TableView partAddTableView;
    @FXML
    TableView productPartTableView;
    @FXML
    TextField productIdTextField;
    @FXML
    TextField productNameTextField;
    @FXML
    TextField productStockTextField;
    @FXML
    TextField productCostTextField;
    @FXML
    TextField productMinTextField;
    @FXML
    TextField productMaxTextField;

    private ObservableList<Part> partAddTableList = FXCollections.observableArrayList();
    private ObservableList<Part> productPartTableList = FXCollections.observableArrayList();

    Inventory inventory;
    String addOrMod;
    Product product;

    public AddModifyProductController(Inventory inventory, String addOrMod, Product product) {
        this.inventory = inventory;
        this.addOrMod = addOrMod;
        this.product = product;
    }

    public AddModifyProductController(Inventory inventory, String addOrMod) {
        this.inventory = inventory;
        this.addOrMod = addOrMod;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partAddTableList.setAll(inventory.getAllParts());
        partAddTableView.setItems(partAddTableList);
        partAddTableView.refresh();

        if(addOrMod.equalsIgnoreCase("add")){
            this.product = new Product();
        }
        else if(addOrMod.equalsIgnoreCase("mod")){

            productIdTextField.setText(Integer.toString(product.getId()));
            productNameTextField.setText(product.getName());
            productStockTextField.setText(Integer.toString(product.getStock()));
            productCostTextField.setText(Double.toString(product.getPrice()));
            productMinTextField.setText(Integer.toString(product.getMin()));
            productMaxTextField.setText(Integer.toString(product.getMax()));
            productPartTableList.setAll(product.getAllAssociatedParts());
            productPartTableView.setItems(partAddTableList);
            productPartTableView.refresh();
        }
    }

}
