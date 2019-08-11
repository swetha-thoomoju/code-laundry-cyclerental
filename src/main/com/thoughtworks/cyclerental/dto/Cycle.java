package com.thoughtworks.cyclerental.dto;

public class Cycle {
    public int id;
    public String name;
    public String brand;
    public Double basePrice;
    public Double pricePerDay;
    public int noOfDays = 0;
    public boolean isRented = false;

    public Cycle(int id, String brand, String name, Double basePrice, int noOfDays, Double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.basePrice = basePrice;
        this.noOfDays = noOfDays;
        this.pricePerDay = pricePerDay;
    }

    public String toString() {
        String price;
        if(noOfDays != 0) {
            price = String.format("%f for %d days. %f after each day.", this.basePrice, this.noOfDays, this.pricePerDay);
        } else {
            price = String.format("%f per day", this.pricePerDay);
        }
        return String.format("%d|%s|%s|%s", this.id, this.name, this.brand, price);
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public String invoice(int noOfDays) {
        if (noOfDays <= this.noOfDays) {
            return Invoice.baseInvoice(basePrice, noOfDays);
        } else {
            return renewalInvoice(this, noOfDays);
        }
    }

    private static String renewalInvoice(Cycle cycle, int noOfDays) {
        Invoice invoice = new Invoice();
        invoice.descriptions.add(String.format("Base Rent for %d", cycle.noOfDays));
        invoice.amounts.add(cycle.basePrice);
        if(noOfDays > cycle.noOfDays) {
            long extraDays = noOfDays - cycle.noOfDays;
            double extraRent = extraDays * cycle.pricePerDay;
            invoice.descriptions.add(String.format("Extra Rent for %d extra days", extraDays));
            invoice.amounts.add(extraRent);
        }
        return invoice.toString();
    }
}
