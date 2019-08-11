package com.thoughtworks.cyclerental.service;

import com.thoughtworks.cyclerental.dto.Customer;
import com.thoughtworks.cyclerental.dto.Customers;
import com.thoughtworks.cyclerental.dto.Cycle;
import com.thoughtworks.cyclerental.dto.Cycles;
import com.thoughtworks.cyclerental.dto.Invoice;

import java.util.*;

public class RentalService {
    Map<Integer, Invoice> invoices = new HashMap<>();
    Cycles cycles;
    HashMap<Customer, Cycle> rentals = new HashMap<>();
    Customers customers;

    public RentalService( Cycles cycles, Customers customers) {
        this.cycles = cycles;
        this.customers = customers;
    }

    public void rentCycle(int cycleId, int customerId) {
        Cycle cycle = cycles.cycleFor(cycleId);
        Customer customer = customers.customerFor(customerId);
        rentals.put(customer, cycle);
        cycle.isRented = true;
    }

    public String returnCycle(int customerId, int noOfDays) {
        Customer customer = customers.customerFor(customerId);
        Cycle cycle = rentals.get(customer);
        rentals.remove(customer);
        cycle.isRented = false;
        return cycle.invoice(noOfDays);
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
