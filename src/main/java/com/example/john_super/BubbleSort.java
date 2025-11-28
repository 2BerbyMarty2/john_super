package com.example.john_super;

import java.util.ArrayList;

public class BubbleSort {

    public static void bubbleSortByLocation(ArrayList<Dealer> dealers) {
        for (int i = 0; i < dealers.size() - 1; i++) {
            for (int j = 0; j < dealers.size() - i - 1; j++) {
                if (dealers.get(j).getLocation().compareTo(dealers.get(j + 1).getLocation()) > 0) {
                    Dealer temp = dealers.get(j);
                    dealers.set(j, dealers.get(j + 1));
                    dealers.set(j + 1, temp);
                }
            }
        }
    }
}
