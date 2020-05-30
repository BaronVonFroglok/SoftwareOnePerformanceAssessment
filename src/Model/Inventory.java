package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Inventory {

    private ArrayList<Part> allParts;
    private ArrayList<Product> allProducts;

    public Inventory() {
        allParts = new ArrayList<>();
        allProducts = new ArrayList<>();
    }

    public void addPart(Part newPart){
        if (newPart != null){
            allParts.add(newPart);
        }
    }

    public void addProduct(Product newProduct){
        if (newProduct != null){
            allProducts.add(newProduct);
        }
    }

    public Part lookupPart(int partId){

        for (int x = 0; x >= allParts.size();x++) {
            if (allParts.get(x).getId() == partId) {
                return allParts.get(x);
            }
        }
        return null;
    }

    public Product lookupProduct(int productId){
      for(int x = 0; x>= allProducts.size();x++){
          if (allProducts.get(x).getId()==productId){
              return allProducts.get(x);
          }
      }
      return null;
    }

    public ArrayList<Part> lookupPart(String name){
        ArrayList<Part> returnList = new ArrayList<>();
        allParts.forEach( part -> {
            if(part.getName().contains(name)){
                returnList.add(part);
            }
        });

        return returnList;
    }

    public ArrayList<Product> lookupProduct(String name){
        ArrayList<Product> returnList = new ArrayList<>();
        allProducts.forEach( product -> {
            if (product.getName().contains(name)){
                returnList.add(product);
            }
        });
        return returnList;
    }

    public void updatePart(Part selectedPart){
        for (int x = 0; x < allParts.size(); x++ ) {
            if (allParts.get(x).getId() == selectedPart.getId()) {
                allParts.remove(x);
                allParts.add(selectedPart);
            }
        }
    }

    public void updateProduct(Product selectedProduct){

        for (int x = 0; x < allProducts.size(); x++ ) {
            if (allProducts.get(x).getId() == selectedProduct.getId()) {
                allProducts.remove(x);
                allProducts.add(selectedProduct);
            }
        }

    }

    public void deletePart(Part selectedPart){
        for (int x = 0; x < allParts.size(); x++){
            if (allParts.get(x).getId() == selectedPart.getId()){
                allParts.remove(selectedPart);
            }
        }
    }
    public void deleteProduct(Product selectedProduct){
        for (int x = 0; x < allProducts.size(); x++){
            if(allProducts.get(x).getId() == selectedProduct.getId()){
                allProducts.remove(selectedProduct);
            }
        }
    }
    public ArrayList<Part> getAllParts(){
        return allParts;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }
}
