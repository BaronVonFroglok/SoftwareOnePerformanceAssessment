package View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.text.TableView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
