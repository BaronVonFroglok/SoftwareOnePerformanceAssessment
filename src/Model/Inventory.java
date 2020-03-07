package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Inventory {

    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart){

    }

    public void addProduct(Product newProduct){

    }

    public Part lookupPart(int partId){

    }

    public Product lookupProduct(int productId){
       Product pr = new Product();
             allProducts.forEach( p -> {
                if(p.getId() == productId){
                     pr.setId(p.getId());
                     pr.
                }
            });
             return pr;
    }

    public ObservableList<Part> lookupPart(String name){
        ObservableList<Part> returnList = FXCollections.observableArrayList();
        allParts.forEach( part -> {
            if(part.getName().contains(name)){
                returnList.add(part);
            }
        });

        return returnList;
    }

    public ObservableList<Product> lookupProduct(String name){

    }

    public void updatePart(int index, Part selectedPart){

    }

    public void updateProduct(int index, Product selectedProduct){

    }

    public void deletePart(Part selectedPart){

    }
    public void deleteProduct(Product selectedProduct){

    }
    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
