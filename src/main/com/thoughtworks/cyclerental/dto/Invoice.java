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

    public String invoiceFor(Cycle cycle, int rentedDays) {
        if (rentedDays <= cycle.noOfDays) {
            return baseInvoice(cycle.basePrice, rentedDays);
        } else {
            return renewalInvoice(cycle, rentedDays);
        }
    }

    private String baseInvoice(Double basePrice, int noOfDays) {
        descriptions.add(String.format("%d Rent", noOfDays));
        amounts.add(basePrice);
        return toString();
    }

    private String renewalInvoice(Cycle cycle, int rentedDays) {
        descriptions.add(String.format("Base Rent for %d", cycle.noOfDays));
        amounts.add(cycle.basePrice);
        long extraDays = rentedDays - cycle.noOfDays;
        double extraRent = extraDays * cycle.pricePerDay;
        descriptions.add(String.format("Extra Rent for %d extra days", extraDays));
        amounts.add(extraRent);
        return toString();
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
