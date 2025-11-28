package com.example.john_super;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class validation {
    //validate the input
//    private String code;
//    private String itemName;
//    private String itemBrand;
//    private double price;
//    private int quantity;
//    private String category;
//    private String purchasedDate;
//    private String itemImageAddres;

    public static boolean codeValidation(String code, ArrayList<Items> itemList) {
        if (code.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Code cannot be empty!");
            alert.showAndWait();
            return false;
        } else if (code.length() != 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Code must be at least 6 characters long!");
            alert.showAndWait();
            return false;
        } else {
            for (Items item : itemList) {
                if (item.getCode().equals(code)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Code already exists!");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        return true;
    }



    public static boolean nameValidation(String name){
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Name cannot be empty!");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }//end nameValidation


    public static boolean brandValidation(String brand){
        if (brand.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Brand cannot be empty!");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }// end brandValidation
    
    public static boolean priceValidation(String price){
        if (price.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Price cannot be empty!");
            alert.showAndWait();
            return false;
        } else {
            try {
                Double.parseDouble(price);
                return true;
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Price must be a number!");
                alert.showAndWait();
                return false;
            }
        }
    }//end priceValidation

    public static boolean quantityValidation(String quantity){
        if (quantity.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Quantity cannot be empty!");
            alert.showAndWait();
            return false;
        } else {
            try {
                Integer.parseInt(quantity);
                return true;
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Quantity must be a number!");
                alert.showAndWait();
                return false;
            }
        }
    }// end quantityValidation

    public static boolean categoryValidation(String category){
        if (category.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Category cannot be empty!");
            return false;
        }else {
            return true;
        }
    }// end categoryValidation

    public static boolean dateValidation(LocalDate date){
        if (date == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Purchase Date cannot be empty!");
            alert.showAndWait();
            return false;
        } else{
            return true;
        }
    }

    public static boolean thresholdValidation(String threshold){
        if (threshold.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Stock Threshold cannot be empty!");
            alert.showAndWait();
            return false;
        } else {
            try {
                Integer.parseInt(threshold);
                return true;
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Stock Threshold must be a number!");
                alert.showAndWait();
                return false;
            }

        }
    }// end thresholdValidation

    public static boolean imageValidation(String imageAddress){
        if (imageAddress.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Image Address cannot be empty!");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }// end imageValidation


}//end class



