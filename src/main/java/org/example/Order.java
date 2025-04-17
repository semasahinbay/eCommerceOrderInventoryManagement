package org.example;

import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> productList;
    private double totalAmount;

    // Constructor
    public Order(Customer customer, List<Product> productList) {
        this.customer = customer;
        this.productList = productList;
        this.totalAmount = calculateTotalAmount();
    }

    // Method to calculate total order amount
    private double calculateTotalAmount() {
        double sum = 0;
        for (Product product : productList) {
            sum += product.getPrice();
        }
        return sum;
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        this.totalAmount = calculateTotalAmount();
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
