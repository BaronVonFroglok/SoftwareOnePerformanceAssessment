package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private ObservableList<Product> productTableList = FXCollections.observableArrayList();


    public MainScreenController(Inventory inventory) {
        this.inventory = inventory;
    }

    public void AddProductClicked(MouseEvent mouseEvent) {
        Product product = new Product();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/AddModifyProduct.fxml"));
        AddModifyProductController controller = new AddModifyProductController(inventory,"add",product);
        fxLoader.setController(controller);

        loadPage(fxLoader,mouseEvent);

    }
    public void ModifyProductClicked(MouseEvent mouseEvent){
        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            Product product = (Product) productsTableView.getSelectionModel().getSelectedItem();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/AddModifyProduct.fxml"));
            AddModifyProductController controller = new AddModifyProductController(inventory, "mod", product);
            fxLoader.setController(controller);

            loadPage(fxLoader,mouseEvent);
        }

    }
    public void DeleteProductClicked(MouseEvent mouseEvent){
        if(productsTableView.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Click OK.");
            Optional<ButtonType> confirm = alert.showAndWait();
            if(confirm.get() == ButtonType.OK) {
                inventory.deleteProduct((Product) productsTableView.getSelectionModel().getSelectedItem());
                productTableList.remove(productsTableView.getSelectionModel().getSelectedItem());
                productsTableView.refresh();
            }
        }
    }
    public void DeletePartClicked(MouseEvent mouseEvent){

        if(partsTableView.getSelectionModel().getSelectedItem()!= null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Click OK.");
            Optional<ButtonType> confirm = alert.showAndWait();
            if(confirm.get() == ButtonType.OK) {
                inventory.deletePart((Part) partsTableView.getSelectionModel().getSelectedItem());
                partTableList.remove(partsTableView.getSelectionModel().getSelectedItem());
                partsTableView.refresh();
            }
        }
    }

    public void productSearchClicked(MouseEvent mouseEvent){
        productTableList.setAll(inventory.lookupProduct(searchProductsTextField.getText().trim()));
        productsTableView.setItems(productTableList);
        productsTableView.refresh();
    }

    public void ModifyPartClicked(MouseEvent mouseEvent){
        if(partsTableView.getSelectionModel().getSelectedItem()!= null) {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/AddModifyPartMain.fxml"));
            Part modPart = (Part)partsTableView.getSelectionModel().getSelectedItem();
            AddModifyPartMainController partMainController = new AddModifyPartMainController(inventory, "mod",modPart);
            fxLoader.setController(partMainController);

            loadPage(fxLoader,mouseEvent);
        }
    }

    public void partSearchClicked(MouseEvent mouseEvent){
        partTableList.setAll(inventory.lookupPart(searchPartsTextField.getText().trim()));
        partsTableView.setItems(partTableList);
        partsTableView.refresh();
    }

    public void AddPartClicked(MouseEvent mouseEvent) {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/AddModifyPartMain.fxml"));
        AddModifyPartMainController partMainController = new AddModifyPartMainController(inventory,"add");
        fxLoader.setController(partMainController);

        loadPage(fxLoader,mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partTableList.setAll(inventory.getAllParts());
        partsTableView.setItems(partTableList);
        partsTableView.refresh();
        productTableList.setAll(inventory.getAllProducts());
        productsTableView.setItems(productTableList);
        productsTableView.refresh();
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
