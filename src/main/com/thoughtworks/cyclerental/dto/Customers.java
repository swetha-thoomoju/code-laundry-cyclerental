package com.thoughtworks.cyclerental.dto;
import java.util.ArrayList;

public class Customers extends ArrayList<Customer> {

    public Customer customerFor(int customerId) {
        return stream().filter(customer1 -> customer1.id == customerId).findFirst().get();
    }
}
