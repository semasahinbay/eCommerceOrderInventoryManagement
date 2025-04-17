package org.example;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    // Constructor
    public Inventory() {
        this.products = new ArrayList<>();
    }

    // Ürün ekleme metodu
    public void addProduct(Product product) {
        products.add(product);
    }

    // Ürün listesini döndürme metodu
    public List<Product> getProducts() {
        return products;
    }

    // Ürün stokunu güncelleme metodu
    public void updateStock(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int newStock = product.getStockQuantity() + quantity;
                product.setStockQuantity(newStock);
                System.out.println("Stok güncellendi: " + productName + " yeni stok: " + newStock);
                return;
            }
        }
        System.out.println("Ürün bulunamadı: " + productName);
    }

    // Ürün stokunu azaltma (satış yapıldığında) metodu
    public void reduceStock(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                if (product.getStockQuantity() >= quantity) {
                    int newStock = product.getStockQuantity() - quantity;
                    product.setStockQuantity(newStock);
                    System.out.println("Stok azaltıldı: " + productName + " yeni stok: " + newStock);
                } else {
                    System.out.println("Yeterli stok yok.");
                }
                return;
            }
        }
        System.out.println("Ürün bulunamadı: " + productName);
    }
}
