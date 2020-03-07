package Model;

import javafx.collections.ObservableList;

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

    }

    public ObservableList<Part> lookupPart(String name){

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
