package com.example.john_super;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ItemStorageManager {

    public static ArrayList<Items> loadItemsFromFile(String filePath) {
        System.out.println("Loading items from file...");
        ArrayList<Items> items = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner fileRead = new Scanner(file);

            // Code | Name | Brand | Price | Quantity | Category | PurchasedDate | ImagePath
            while (fileRead.hasNextLine()) {
                String[] data = Arrays.stream(fileRead.nextLine().split("\\|")).map(String::strip).toArray(String[]::new);
                items.add(new Items(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        Double.parseDouble(data[3].trim()),
                        Integer.parseInt(data[4].trim()),
                        data[5].trim(),
                        LocalDate.parse(data[6].trim()),
                        data[7].trim(),
                        Integer.parseInt(data[8].trim())));
            }
            System.out.println("Loaded " + items.size() + " items from file.");
            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            System.out.println("Error loading items: " + e.getMessage());
        }

        return items;
    }

    public static void saveItemsToFile(ArrayList<Items> items, String filePath) {
        System.out.println("Saving items to file...");
        try {
            FileWriter file = new FileWriter(filePath);

            for (Items item : items) {
                file.write(item.getCode() + "|"
                        + item.getItemName() + "|"
                        + item.getItemBrand() + "|"
                        + item.getPrice() + "|"
                        + item.getQuantity() + "|"
                        + item.getCategory() + "|"
                        + item.getPurchasedDate() + "|"
                        + item.getItemImageAddres() + "|"
                        + item.getStockThreshold() + "\n"
                );
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }// end saveItemsToFile

    public static void deleteItemWithImage(String imagePath) {
        File imageFile = new File("src/main/resources/" + imagePath);
        if (imageFile.exists()) {
            boolean deleted = imageFile.delete();
            if (deleted) {
                System.out.println("Image file deleted: " + imageFile.getPath());
            } else {
                System.out.println("Failed to delete image file: " + imageFile.getPath());
            }
        } else {
            System.out.println("Image file not found: " + imageFile.getPath());
        }


    }//end deleteItemWithImage
}// end class