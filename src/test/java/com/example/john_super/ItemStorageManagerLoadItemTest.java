package com.example.john_super;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemStorageManagerLoadItemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testLoadItemsFromFileValidData(@TempDir File tempDir) throws Exception {
        File tempFile = new File(tempDir, "items.txt");
        String content = "ITEM001 | Laptop | Dell | 999.99 | 10 | Electronics | 2023-10-15 | /images/laptop.jpg | 100\n" +
                "ITEM002 | Phone | Apple | 799.99 | 5 | Electronics | 2023-11-01 | /images/phone.jpg | 200";
        Files.writeString(tempFile.toPath(), content);

        ArrayList<Items> items = ItemStorageManager.loadItemsFromFile(tempFile.getAbsolutePath());

        assertEquals(2, items.size(), "Should load two items");
        Items item1 = items.get(0);
        assertEquals("ITEM001", item1.getCode());
        assertEquals("Laptop", item1.getItemName());
        assertEquals("Dell", item1.getItemBrand());
        assertEquals(999.99, item1.getPrice(), 0.01);
        assertEquals(10, item1.getQuantity());
        assertEquals("Electronics", item1.getCategory());
        assertEquals(LocalDate.of(2023, 10, 15), item1.getPurchasedDate());
        assertEquals("/images/laptop.jpg", item1.getItemImageAddres());
        assertEquals(100, item1.getStockThreshold());

        String output = outContent.toString();
        assertTrue(output.contains("Loading items from file..."));
        assertTrue(output.contains("Loaded 2 items from file."));
    }

    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}