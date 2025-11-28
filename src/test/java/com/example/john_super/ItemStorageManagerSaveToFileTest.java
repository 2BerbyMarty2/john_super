package com.example.john_super;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ItemStorageManagerSaveToFileTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSaveItemsToFileValidData(@TempDir File tempDir) throws Exception {
        ArrayList<Items> items = new ArrayList<>();
        items.add(new Items("ITEM001", "Laptop", "Dell", 999.99, 10, "Electronics",
                LocalDate.of(2023, 10, 15), "/images/laptop.jpg", 100));
        items.add(new Items("ITEM002", "Phone", "Apple", 799.99, 5, "Electronics",
                LocalDate.of(2023, 11, 1), "/images/phone.jpg", 200));

        File tempFile = new File(tempDir, "items.txt");

        ItemStorageManager.saveItemsToFile(items, tempFile.getAbsolutePath());

        List<String> lines = Files.readAllLines(tempFile.toPath());

        assertEquals(2, lines.size(), "File should contain two lines");
        String expectedLine1 = "ITEM001|Laptop|Dell|999.99|10|Electronics|2023-10-15|/images/laptop.jpg|100";
        String expectedLine2 = "ITEM002|Phone|Apple|799.99|5|Electronics|2023-11-01|/images/phone.jpg|200";
        assertEquals(expectedLine1, lines.get(0), "First item should match expected format");
        assertEquals(expectedLine2, lines.get(1), "Second item should match expected format");

        String output = outContent.toString();
        assertTrue(output.contains("Saving items to file..."), "Console should print saving message");
    }

    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

}