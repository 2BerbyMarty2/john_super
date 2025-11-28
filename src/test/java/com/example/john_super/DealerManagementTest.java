package com.example.john_super;

import com.example.john_super.Dealer;
import com.example.john_super.DealerManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSort {
    static void bubbleSortByLocation(ArrayList<Dealer> dealers) {
        dealers.sort((d1, d2) -> d1.getLocation().compareTo(d2.getLocation()));
    }
}

class DealerManagementTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testLoadAllDealersValidData(@TempDir File tempDir) throws Exception {
        File tempFile = new File(tempDir, "dealers.txt");
        String content = "John Doe | 1234567890 | Chicago | Laptop | Phone | Tablet | Monitor\n" +
                "Jane Smith | 0987654321 | Boston | Desktop | Keyboard | Mouse | Printer";
        Files.writeString(tempFile.toPath(), content);

        ArrayList<Dealer> dealers = DealerManagement.loadAllDealers(tempFile.getAbsolutePath());

        assertEquals(2, dealers.size(), "Should load two dealers");

        Dealer dealer1 = dealers.get(0);
        assertEquals("Jane Smith", dealer1.getName());
        assertEquals("0987654321", dealer1.getContactNumber());
        assertEquals("Boston", dealer1.getLocation());
        assertEquals("Desktop", dealer1.getItemOne());
        assertEquals("Keyboard", dealer1.getItemTwo());
        assertEquals("Mouse", dealer1.getItemThree());
        assertEquals("Printer", dealer1.getItemFour());

        Dealer dealer2 = dealers.get(1);
        assertEquals("John Doe", dealer2.name);
        assertEquals("1234567890", dealer2.contactNumber);
        assertEquals("Chicago", dealer2.location);
        assertEquals("Laptop", dealer2.itemOne);
        assertEquals("Phone", dealer2.itemTwo);
        assertEquals("Tablet", dealer2.itemThree);
        assertEquals("Monitor", dealer2.itemFour);

        String output = outContent.toString();
        assertTrue(output.contains("Loading dealers from file..."), "Console should print loading message");
    }

    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}