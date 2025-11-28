package com.example.john_super;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class HelloController {

    @FXML
    private TableView<Items> tableView;
    @FXML
    private TableColumn<Items, String> codeColumn;
    @FXML
    private TableColumn<Items, String> NameColumn;
    @FXML
    private TableColumn<Items, String> brandColumn;
    @FXML
    private TableColumn<Items, String> categoryColumn;
    @FXML
    private TableColumn<Items, Integer> thresholdColumn;
    @FXML
    private TableColumn<Items, Integer> quantityColumn;
    @FXML
    private TableColumn<Items, Double> priceColumn;
    @FXML
    private TableColumn<Items, LocalDate> dateColumn;


    @FXML
    private TextField codeField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField thresholdField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField imageAddressField;

    @FXML
    private String imagePath;

    @FXML
    private ImageView itemImageView;

    @FXML
    private TableColumn<Dealer, String> DealerNameColumn;
    @FXML
    private TableColumn<Dealer, String> DealerContactColumn;
    @FXML
    private TableColumn<Dealer, String> DealerLocationColumn;
    @FXML
    private TableColumn<Dealer, String> DealerItemOneColumn;
    @FXML
    private TableColumn<Dealer, String> DealerItemTwoColumn;
    @FXML
    private TableColumn<Dealer, String> DealerItemThreeColumn;
    @FXML
    private TableColumn<Dealer, String> DealerItemFourColumn;
    @FXML
    private TableView<Dealer> DealerTable;



    @FXML
    private TextField searchField;

    ArrayList<Dealer> dealers = DealerManagement.loadAllDealers("Dealers.txt");
    ObservableList<Dealer> observableDealers = FXCollections.observableArrayList(dealers);
    ArrayList<Items> items = ItemStorageManager.loadItemsFromFile("Items.txt");
    ObservableList<Items> observableItems = FXCollections.observableArrayList(items);


    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("code"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemName"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemBrand"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("category"));
        thresholdColumn.setCellValueFactory(new PropertyValueFactory<Items,Integer>("stockThreshold"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Items,Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Items,Double>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Items,LocalDate>("purchasedDate"));

        DealerNameColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("name"));
        DealerContactColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("contactNumber"));
        DealerLocationColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("location"));
        DealerItemOneColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("itemOne"));
        DealerItemTwoColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("itemTwo"));
        DealerItemThreeColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("itemThree"));
        DealerItemFourColumn.setCellValueFactory(new PropertyValueFactory<Dealer,String>("itemFour"));

        DealerTable.setItems(observableDealers);

        ObservableList<Items> observableItems = FXCollections.observableArrayList(items);
        tableView.setItems(observableItems);

        tableView.setOnMouseClicked((MouseEvent event) -> {System.out.println("Table clicked!");
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                String imagePath = "/" + tableView.getSelectionModel().getSelectedItem().getItemImageAddres();

                InputStream imgStream = getClass().getResourceAsStream(imagePath);
                if (imgStream != null) {
                    itemImageView.setImage(new Image(imgStream));
                } else {
                    System.out.println("Image not found at: " + imagePath);
                    itemImageView.setImage(null);
                }
            }});

    }//end initializing
    
    public void LoadAllItems(){
        items = ItemStorageManager.loadItemsFromFile("Items.txt");
        ObservableList<Items> observableItems = FXCollections.observableArrayList(items);
        tableView.setItems(observableItems);
    }//end loadAllItems
    
    public void BelowThresholdItems(){
        ArrayList<Items> belowThresholdItems = new ArrayList<>();
        for (Items item : items){
            if (item.getQuantity() < item.getStockThreshold()){
                belowThresholdItems.add(item);
            }
        }
        ObservableList<Items> observableBelowThresholdItems = FXCollections.observableArrayList(belowThresholdItems);
        tableView.setItems(observableBelowThresholdItems);
    }//end belowThresholdItems


    public void DisplayAllItems(){
        ObservableList<Items> observableItems = FXCollections.observableArrayList(items);
        tableView.setItems(observableItems);
    }//end displayAllItems

    public void SearchItem(){
        ArrayList<Items> searchItems = new ArrayList<>();
        for (Items item : items){
            if (item.getCode().contains(searchField.getText())){
                searchItems.add(item);
            }
        }
        ObservableList<Items> observableSearchItem = FXCollections.observableArrayList(searchItems);
        tableView.setItems(observableSearchItem);

    }// end searchItem

    public void AddItem(){
        boolean validity = validation.codeValidation(codeField.getText(),items)
                && validation.nameValidation(itemNameField.getText())
                && validation.brandValidation(brandField.getText())
                && validation.priceValidation(priceField.getText())
                && validation.quantityValidation(quantityField.getText())
                && validation.categoryValidation(categoryField.getText())
                && validation.dateValidation(dateField.getValue())
                && validation.thresholdValidation(thresholdField.getText())
                && validation.imageValidation(imageAddressField.getText());
        
        if (validity){
            items.add(new Items(
                    // Order: code, name, brand, price, quantity, category, purchasedDate, imageAddress, stockThreshold
                    codeField.getText(),
                    itemNameField.getText(),
                    brandField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    categoryField.getText(),
                    dateField.getValue(),
                    imageAddressField.getText(),
                    Integer.parseInt(thresholdField.getText()))
            );
            ObservableList<Items> observableItems = FXCollections.observableArrayList(items);
            tableView.setItems(observableItems);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please check your input!");
            alert.showAndWait();
        }
    }// end addItem

    public void DeleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            // Show confirmation first
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this item?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Only delete after confirmation
                Items selectedItem = tableView.getSelectionModel().getSelectedItem();

                // Delete image from disk
                ItemStorageManager.deleteItemWithImage(selectedItem.getItemImageAddres());

                // Remove from list and update table
                items.remove(selectedItem);
                ObservableList<Items> observableList = FXCollections.observableArrayList(items);
                tableView.setItems(observableList);
            }
        }
    }


    public void UpdateItem(){
        if (tableView.getSelectionModel().getSelectedItem() != null){
            ItemStorageManager.deleteItemWithImage(tableView.getSelectionModel().getSelectedItem().getItemImageAddres());
            items.remove(tableView.getSelectionModel().getSelectedItem());
            codeField.setText(tableView.getSelectionModel().getSelectedItem().getCode());
            itemNameField.setText(tableView.getSelectionModel().getSelectedItem().getItemName());
            brandField.setText(tableView.getSelectionModel().getSelectedItem().getItemBrand());
            priceField.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getPrice()));
            quantityField.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getQuantity()));
            categoryField.setText(tableView.getSelectionModel().getSelectedItem().getCategory());
            dateField.setValue(tableView.getSelectionModel().getSelectedItem().getPurchasedDate());
            imageAddressField.setText(tableView.getSelectionModel().getSelectedItem().getItemImageAddres());
            thresholdField.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getStockThreshold()));
        }
    }// end UpdateItem

    public void SaveItemsToFile(){
        ItemStorageManager.saveItemsToFile(items,"Items.txt");
    }

    @FXML
    private void handleImageSelect() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                File destDir = new File("src/main/resources/images/");
                if (!destDir.exists()) destDir.mkdirs();

                File destFile = new File(destDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Set relative image path
                imagePath = "images/" + selectedFile.getName();

                // Show path in TextArea
                imageAddressField.setText(imagePath);

            } catch (IOException e) {
                imageAddressField.setText("Error saving file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }// end handleImageSelect

    @FXML
    private void viewAllTheDealers() {
        System.out.println("Viewing all the dealers...");
        DealerTable.setItems(observableDealers);
    }

    @FXML
    private void selectRandom(){
        System.out.println("Selecting random dealer...");
        ArrayList<Dealer> randomDealers = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            int randomIndex = (int)(Math.random() * dealers.size());
            randomDealers.add(dealers.get(randomIndex));
        }
        ObservableList<Dealer> RandomObservableDealers = FXCollections.observableArrayList(randomDealers);
        DealerTable.setItems(RandomObservableDealers);
    }//End Random Dealer








}// end class
















