package View_Controller;

import Model.Inventory;
import Model.Outsourced;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyOutsourcedPartController implements Initializable {
    Outsourced part;
    String addOrMod;
    Inventory inventory;

    @FXML
    TextField partIdTextField;
    @FXML
    TextField partNameTextField;
    @FXML
    TextField stockTextField;
    @FXML
    TextField partCostTextField;
    @FXML
    TextField partMinTextField;
    @FXML
    TextField partMaxTextField;
    @FXML
    TextField partCompanyTextField;

    public AddModifyOutsourcedPartController(Outsourced part, String addOrMod, Inventory inventory) {
        this.part = part;
        this.addOrMod = addOrMod;
        this.inventory = inventory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (addOrMod.equalsIgnoreCase("add")){

        }
        else if (addOrMod.equalsIgnoreCase("mod")){
            partIdTextField.setText(Integer.toString(part.getId()));
            partNameTextField.setText(part.getName());
            stockTextField.setText(Integer.toString(part.getStock()));
            partCostTextField.setText(Double.toString(part.getPrice()));
            partMinTextField.setText(Integer.toString(part.getMin()));
            partMaxTextField.setText(Integer.toString(part.getMax()));
            partCompanyTextField.setText(part.getCompanyName());
        }
    }
}
