package com.thoughtworks.cyclerental.dto;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    public List<String> descriptions;
    public List<Double> amounts;

    public Invoice() {
        this.amounts = new ArrayList<Double>();
        this.descriptions = new ArrayList<String>();
    }

    static String baseInvoice(Double basePrice, int noOfDays) {
        Invoice invoice = new Invoice();
        invoice.descriptions.add(String.format("%d Rent", noOfDays));
        invoice.amounts.add(basePrice);
        return invoice.toString();
    }

    private Double sum() {
        Double totalAmount = 0.0;
        for (int i = 0; i < this.amounts.size(); i++) {
            totalAmount = totalAmount + this.amounts.get(i);
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(int i=0;i<descriptions.size();i++) {
            returnString.append(descriptions.get(i)).append(": ").append(amounts.get(i).toString());
        }
        return String.format("%s\n-------------------------------------------\nTotal: %s", returnString, this.sum());
    }
}
