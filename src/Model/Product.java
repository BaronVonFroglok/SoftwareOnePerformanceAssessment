package Model;

import java.util.ArrayList;

public class Product {
    private ArrayList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(){}
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part){
        if(part != null){
            associatedParts.add(part);
        }
    }

    public boolean deleteAssociatedPart(Part selectedPart){
        for(int x = 0; x >= this.associatedParts.size(); x++){
            if(associatedParts.get(x).getId() == selectedPart.getId()){
                associatedParts.remove(selectedPart);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
