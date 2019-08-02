package com.thoughtworks.cyclerental.service;

import com.thoughtworks.cyclerental.dto.Customer;
import com.thoughtworks.cyclerental.dto.Cycle;
import com.thoughtworks.cyclerental.dto.Invoice;

import java.util.*;

public class RentalService {
    Map<Integer, Invoice> invoices = new HashMap<>();
    List<Cycle> cycles = new ArrayList<>();
    HashMap<Customer, Cycle> rentals = new HashMap<>();
    List<Customer> customers = new ArrayList<Customer>();

    public RentalService( List<Cycle> cycles, List<Customer> customers) {
        this.cycles = cycles;
        this.customers = customers;
    }

    public void rentCycle(int cycleId, int customerId) {
        Cycle cycle = cycles.stream().filter(c -> !c.isRented && c.id == cycleId).findFirst().get();
        Customer customer = customers.stream().filter(c -> c.id == customerId).findFirst().get();
        rentals.put(customer, cycle);
        cycle.isRented = true;
    }

    public String returnCycle(int customerId, int noOfDays) {
        Customer customer = customers.stream().filter(customer1 -> customer1.id == customerId).findFirst().get();
        Cycle cycle = rentals.get(customer);
        rentals.remove(customer);
        cycle.isRented = false;
        // Invoice for minimum days
        if (noOfDays <= cycle.noOfDays) {
            Invoice invoice = new Invoice();
            invoice.descriptions.add(String.format("%d Rent", noOfDays));
            invoice.amounts.add(cycle.basePrice);
            return invoice.toString();
        } else {
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

    // Do not remove this method, it will be used in future.
    private static String printInvoices(List<Invoice> invoices) {
        StringBuilder invoiceStr = new StringBuilder();
        for(Invoice invoice : invoices) {
            invoiceStr.append(invoice).append("\n");
        }
        return invoiceStr.toString();
    }
}
