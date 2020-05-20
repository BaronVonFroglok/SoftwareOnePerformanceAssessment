import Model.*;
import View_Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryApplication extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Inventory inventory = new Inventory();
        addTestItems(inventory);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
        MainScreenController mcs = new MainScreenController(inventory);
        fxLoader.setController(mcs);
        Parent root = fxLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void addTestItems(Inventory inv){
        InHouse part1 = new InHouse(1,"part 1",10.99,50,10,100, 1);
        InHouse part2 = new InHouse(2, "part 2", 5.99,40,10,100,2);
        Outsourced part3 = new Outsourced(3,"part 3",2.75,30,10,100,"Outsourced Matirial Inc");
        inv.addPart(part1);
        inv.addPart(part2);
        inv.addPart(part3);
        Product product1 = new Product(1,"product 1",19.73,10,2,10);
        product1.addAssociatedPart(inv.getAllParts().get(0));
        product1.addAssociatedPart(inv.getAllParts().get(1));
        product1.addAssociatedPart(inv.getAllParts().get(2));
    }
}
