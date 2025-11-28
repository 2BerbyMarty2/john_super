package com.example.john_super;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.example.john_super.BubbleSort.bubbleSortByLocation;

public class DealerManagement {

    static ArrayList<Dealer> DealerInfo;

    public static ArrayList<Dealer> loadAllDealers(String filePath){
        System.out.println("Loading dealers from file...");

        DealerInfo = new ArrayList<>();

        try {
            File dealerFile = new File(filePath);
            Scanner fileRead = new Scanner(dealerFile);

            /* String name, int contactNumber, String location, String itemOne, String itemTwo, String itemThree, String itemFour;*/
            while (fileRead.hasNextLine()) {
                String[] data = Arrays.stream(fileRead.nextLine().split("\\|")).map(String::strip).toArray(String[]::new);
                DealerInfo.add(new Dealer(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim(),
                        data[6].trim()));
            }
            bubbleSortByLocation(DealerInfo);

            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + "dealerFile");
        } catch (Exception e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
        return DealerInfo;
    }


}
