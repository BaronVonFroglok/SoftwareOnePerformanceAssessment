package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddModifyPartMainController implements Initializable {

    @FXML
    RadioButton outsourcedRadioButton;
    @FXML
    RadioButton inHouseRadioButton;
    @FXML
    TextField partIdTextField;
    @FXML
    TextField partNameTextField;
    @FXML
    TextField partStockTextField;
    @FXML
    TextField partCostTextField;
    @FXML
    TextField partMinTextField;
    @FXML
    TextField partMaxTextField;
    @FXML
    TextField partCompanyTextField;
    @FXML
    Label companyLabel;

    Inventory inventory;
    String addOrMod;
    Part part;



    public AddModifyPartMainController(Inventory inventory, String addOrMod) {
        this.inventory = inventory;
        this.addOrMod = addOrMod;
    }

    public AddModifyPartMainController(Inventory inventory, String addOrMod, Part part) {
        this.inventory = inventory;
        this.addOrMod = addOrMod;
        this.part = part;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (addOrMod.equalsIgnoreCase("add")){
            companyLabel.setText("Machine ID");
            partCompanyTextField.setPromptText("Machine ID");

        }
        else if (addOrMod.equalsIgnoreCase("mod")){
            if (part instanceof InHouse){

                //outsourcedRadioButton.setDisable(true);
                companyLabel.setText("Machine ID");
                partCompanyTextField.setPromptText("Machine ID");

                partIdTextField.setText(Integer.toString(part.getId()));
                partIdTextField.setDisable(true);
                partNameTextField.setText(part.getName());
                partStockTextField.setText(Integer.toString(part.getStock()));
                partMaxTextField.setText(Integer.toString(part.getMax()));
                partMinTextField.setText(Integer.toString(part.getMin()));
                partCostTextField.setText(Double.toString(part.getPrice()));
                partCompanyTextField.setText(Integer.toString(((InHouse) part).getMachineId()));
            }
            else if (part instanceof Outsourced){

                //inHouseRadioButton.setDisable(true);
                inHouseRadioButton.setSelected(false);
                outsourcedRadioButton.setSelected(true);

                partIdTextField.setText(Integer.toString(part.getId()));
                partIdTextField.setDisable(true);
                partNameTextField.setText(part.getName());
                partStockTextField.setText(Integer.toString(part.getStock()));
                partMaxTextField.setText(Integer.toString(part.getMax()));
                partMinTextField.setText(Integer.toString(part.getMin()));
                partCostTextField.setText(Double.toString(part.getPrice()));
                partCompanyTextField.setText(((Outsourced) part).getCompanyName());
            }
        }
    }
    public void inHouseRadioButtonClicked(MouseEvent mouseEvent){
        outsourcedRadioButton.setSelected(false);
        companyLabel.setText("Machine ID");
        partCompanyTextField.setPromptText("Machine ID");
    }
    public void outSourcedRadioButtonClicked(MouseEvent mouseEvent){
        inHouseRadioButton.setSelected(false);
        companyLabel.setText("Company Name");
        partCompanyTextField.setPromptText("Company Name");
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

    public void addButtonClicked(MouseEvent mouseEvent) {
        try{
            if(inHouseRadioButton.isSelected()) {
                if (addOrMod.equalsIgnoreCase("add")) {
                    InHouse partToAdd = new InHouse();
                    partToAdd.setId(Integer.parseInt(partIdTextField.getText().trim()));
                    partToAdd.setName(partNameTextField.getText().trim());
                    partToAdd.setStock(Integer.parseInt(partStockTextField.getText().trim()));
                    partToAdd.setPrice(Double.parseDouble(partCostTextField.getText().trim()));
                    partToAdd.setMax(Integer.parseInt((partMaxTextField.getText().trim())));
                    partToAdd.setMin(Integer.parseInt(partMinTextField.getText().trim()));
                    partToAdd.setMachineId(Integer.parseInt(partCompanyTextField.getText().trim()));

                    inventory.addPart(partToAdd);
                } else if (addOrMod.equalsIgnoreCase("mod")) {
                    InHouse modPart = new InHouse();
                    modPart.setId(Integer.parseInt(partIdTextField.getText().trim()));
                    modPart.setName(partNameTextField.getText().trim());
                    modPart.setStock(Integer.parseInt(partStockTextField.getText().trim()));
                    modPart.setPrice(Double.parseDouble(partCostTextField.getText().trim()));
                    modPart.setMax(Integer.parseInt(partMaxTextField.getText().trim()));
                    modPart.setMin(Integer.parseInt(partMinTextField.getText().trim()));
                    modPart.setMachineId(Integer.parseInt(partCompanyTextField.getText().trim()));


                    inventory.updatePart(modPart);


                }

                FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
                MainScreenController mcs = new MainScreenController(inventory);
                fxLoader.setController(mcs);

                loadPage(fxLoader, mouseEvent);
            }

            else if (outsourcedRadioButton.isSelected()){
                if (addOrMod.equalsIgnoreCase("add")) {
                    part = new Outsourced(Integer.parseInt(partIdTextField.getText()), partNameTextField.getText(),
                            Double.parseDouble(partCostTextField.getText()), Integer.parseInt(partStockTextField.getText()),
                            Integer.parseInt(partMinTextField.getText()), Integer.parseInt(partMaxTextField.getText()),
                            partCompanyTextField.getText());
                    inventory.addPart(part);
                }
                else if (addOrMod.equalsIgnoreCase("mod")){

                    Outsourced modPart = new Outsourced();
                    modPart.setId(Integer.parseInt(partIdTextField.getText().trim()));
                    System.out.println(modPart.getId());
                    modPart.setName(partNameTextField.getText().trim());
                    System.out.println(modPart.getName());
                    modPart.setPrice(Double.parseDouble(partCostTextField.getText()));
                    modPart.setStock(Integer.parseInt(partStockTextField.getText().trim()));
                    modPart.setMax(Integer.parseInt(partMaxTextField.getText().trim()));
                    modPart.setMin(Integer.parseInt(partMinTextField.getText().trim()));
                    modPart.setCompanyName(partCompanyTextField.getText().trim());
                    System.out.println(modPart.getCompanyName());


                    inventory.updatePart(modPart);



                }
                FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
                MainScreenController mcs = new MainScreenController(inventory);
                fxLoader.setController(mcs);

                loadPage(fxLoader,mouseEvent);
            }
        }
        catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NaN");
            alert.setHeaderText("That's not a number!");
            alert.setContentText("Try again.");
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
