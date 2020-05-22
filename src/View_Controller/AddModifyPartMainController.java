package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
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
            try{
                inHousePane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyInHousePart.fxml"));
                InHouse newPart = new InHouse();
                AddModifyInHousePartController controller = new AddModifyInHousePartController(newPart,"add", inventory);
                switchAnchorPane.getChildren().add(inHousePane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (addOrMod.equalsIgnoreCase("mod")){
            if (part instanceof InHouse){
                try{
                    inHousePane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyInHousePart.fxml"));
                    InHouse modPart = (InHouse) part;
                    AddModifyInHousePartController controller = new AddModifyInHousePartController(modPart,"mod", inventory);
                    switchAnchorPane.getChildren().add(inHousePane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (part instanceof Outsourced){
                try{
                    outsourcedPane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyOutsourcedPart.fxml"));
                    Outsourced modPart = (Outsourced) part;
                    AddModifyOutsourcedPartController controller = new AddModifyOutsourcedPartController(modPart,"mod", inventory);
                    switchAnchorPane.getChildren().add(outsourcedPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void inHouseRadioButtonClicked(MouseEvent mouseEvent){
        switchAnchorPane.getChildren().clear();
        outsourcedRadioButton.setSelected(false);
        try{
            inHousePane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyInHousePart.fxml"));
            InHouse newPart = new InHouse();
            AddModifyInHousePartController controller = new AddModifyInHousePartController(newPart,"add", inventory);
            switchAnchorPane.getChildren().add(inHousePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void outSourcedRadioButtonClicked(MouseEvent mouseEvent){
        switchAnchorPane.getChildren().clear();
        inHouseRadioButton.setSelected(false);
        try{
            outsourcedPane = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyOutsourcedPart.fxml"));
            Outsourced newPart = new Outsourced();
            AddModifyOutsourcedPartController controller = new AddModifyOutsourcedPartController(newPart,"add", inventory);
            switchAnchorPane.getChildren().add(outsourcedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
