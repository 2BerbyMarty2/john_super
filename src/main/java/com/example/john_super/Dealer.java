package com.example.john_super;

public class Dealer {
    String name;
    String contactNumber;
    String location;
    String itemOne;
    String itemTwo;
    String itemThree;
    String itemFour;

    public Dealer(String name, String contactNumber, String location, String itemOne, String itemTwo, String itemThree, String itemFour) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
        this.itemThree = itemThree;
        this.itemFour = itemFour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemFour() {
        return itemFour;
    }

    public void setItemFour(String itemFour) {
        this.itemFour = itemFour;
    }

    public String getItemThree() {
        return itemThree;
    }

    public void setItemThree(String itemThree) {
        this.itemThree = itemThree;
    }

    public String getItemTwo() {
        return itemTwo;
    }

    public void setItemTwo(String itemTwo) {
        this.itemTwo = itemTwo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemOne() {
        return itemOne;
    }

    public void setItemOne(String itemOne) {
        this.itemOne = itemOne;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /* String name;
    int contactNumber;
    String location;
    String itemOne;
    String itemTwo;
    String itemThree;
    String itemFour;*/

    void allInfoDealer () {
        System.out.println("Name: " + name + "\ncontact number: " + contactNumber + "\nlocation: " + location + "\nitemOne: " + itemOne + "\nitemTwo: " + itemTwo + "\nitemThree: " + itemThree + "\nitemFour: " + itemFour);
        System.out.println("____________________________________________________________");

    }

}
