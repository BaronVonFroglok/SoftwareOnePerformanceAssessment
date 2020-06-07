package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
        productPartTableList.clear();
        partAddTableList.setAll(inventory.getAllParts());
        partAddTableView.setItems(partAddTableList);
        partAddTableView.refresh();

        if(addOrMod.equalsIgnoreCase("add")){
            this.product = new Product();
            productIdTextField.setDisable(false);
            productIdTextField.setPromptText("ID");
        }
        else if(addOrMod.equalsIgnoreCase("mod")){

            productIdTextField.setText(Integer.toString(product.getId()));
            productNameTextField.setText(product.getName());
            productStockTextField.setText(Integer.toString(product.getStock()));
            productCostTextField.setText(Double.toString(product.getPrice()));
            productMinTextField.setText(Integer.toString(product.getMin()));
            productMaxTextField.setText(Integer.toString(product.getMax()));
            productPartTableList.setAll(product.getAllAssociatedParts());
            productPartTableView.setItems(productPartTableList);
            productPartTableView.refresh();
        }
    }

    public void productPartAddButtonClicked(MouseEvent mouseEvent){
        if (partAddTableView.getSelectionModel().getSelectedItem() !=null ){
            productPartTableList.add((Part)partAddTableView.getSelectionModel().getSelectedItem());
            productPartTableView.setItems(productPartTableList);
            productPartTableView.refresh();
        }
    }

    public void productPartDeleteButtonClicked(MouseEvent mouseEvent){
        if(productPartTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Click OK.");
            Optional<ButtonType> confirm = alert.showAndWait();
            if(confirm.get() == ButtonType.OK) {
                productPartTableList.remove(productPartTableView.getSelectionModel().getSelectedItem());
                productPartTableView.setItems(productPartTableList);
                productPartTableView.refresh();
            }
        }
    }

    public void partSearchClicked(MouseEvent mouseEvent){
        partAddTableList.setAll(inventory.lookupPart(partSearchTextField.getText().trim()));
        partAddTableView.setItems(partAddTableList);
        partAddTableView.refresh();
    }

    public void cancelButtonCLicked(MouseEvent mouseEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Click OK.");
        Optional<ButtonType> confirm = alert.showAndWait();
        if(confirm.get() == ButtonType.OK) {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
            MainScreenController mcs = new MainScreenController(inventory);
            fxLoader.setController(mcs);

            loadPage(fxLoader, mouseEvent);
        }
    }

    public void productSaveButtonClicked(MouseEvent mouseEvent) {
        if (!productPartTableList.isEmpty()) {
            this.product.setId(Integer.parseInt(productIdTextField.getText().trim()));
            this.product.setName(productNameTextField.getText().trim());
            this.product.setStock(Integer.parseInt(productStockTextField.getText()));
            this.product.setMin(Integer.parseInt(productMinTextField.getText().trim()));
            this.product.setMax(Integer.parseInt(productMaxTextField.getText().trim()));
            this.product.setPrice(Double.parseDouble(productCostTextField.getText().trim()));

            this.product.getAllAssociatedParts().clear();

            for (int x = 0; x < productPartTableList.size(); x++) {

                this.product.addAssociatedPart((Part) productPartTableList.get(x));
            }
            if (addOrMod.equalsIgnoreCase("mod")) {
                inventory.updateProduct(product);
            } else if (addOrMod.equalsIgnoreCase("add")) {

                inventory.addProduct(product);
            }

            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
            MainScreenController mcs = new MainScreenController(inventory);
            fxLoader.setController(mcs);

            loadPage(fxLoader, mouseEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Parts");
            alert.setHeaderText("A product must have at least one part");
            alert.setContentText("Please add a part");
            alert.showAndWait();
        }
    }

    public void loadPage(FXMLLoader loader, MouseEvent mouseEvent){

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
