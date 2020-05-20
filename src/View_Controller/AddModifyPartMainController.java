package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyPartMainController implements Initializable {

    @FXML
    RadioButton outsourcedRadioButton;
    @FXML
    RadioButton inHouseRadioButton;
    @FXML
    AnchorPane switchAnchorPane;
    @FXML
    AnchorPane inHousePane;
    @FXML
    AnchorPane outsourcedPane;

    Inventory inventory;
    String addOrMod;



    public AddModifyPartMainController(Inventory inventory, String addOrMod) {
        this.inventory = inventory;
        this.addOrMod = addOrMod;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (addOrMod.equalsIgnoreCase("add")){
            try{
                inHousePane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyInHousePart.fxml"));
                switchAnchorPane.getChildren().add(inHousePane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (addOrMod.equalsIgnoreCase("mod")){

        }
    }
}
