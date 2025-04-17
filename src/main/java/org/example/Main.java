package org.example;
import java.io.*;
import java.util.*;

public class Main {

    private static final String PRODUCT_FILE = "products.txt";
    private static final String CUSTOMER_FILE = "customers.txt";

    private Inventory inventory = new Inventory();
    private List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        Main managementSystem = new Main();
        managementSystem.run();
    }

    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Kullanıcıya menü seçenekleri:
            System.out.println("--- E-Ticaret Yönetim Sistemi ---");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Yeni Ürün Ekle");
            System.out.println("3. Müşteri Ekle");
            System.out.println("4. Stok Güncelle");
            System.out.println("5. Stok Azalt (Satış)");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminizi Yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Yeni satır karakterini temizlemek için kullandım.

            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    System.out.print("Yeni ürün adı giriniz: ");
                    String productName = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stok: ");
                    int stock = scanner.nextInt();
                    addProduct(productName, price, stock);
                    break;
                case 3:
                    System.out.print("Müşteri adını giriniz: ");
                    String customerName = scanner.nextLine();
                    System.out.print("E-posta giriniz: ");
                    String email = scanner.nextLine();
                    addCustomer(customerName, email);
                    break;
                case 4:
                    System.out.print("Stok güncellemek istediğiniz ürün adını giriniz: ");
                    String updateProduct = scanner.nextLine();
                    System.out.print("Güncellenecek miktar: ");
                    int updateQuantity = scanner.nextInt();
                    inventory.updateStock(updateProduct, updateQuantity);
                    break;
                case 5:
                    System.out.print("Satılan ürün adı: ");
                    String soldProduct = scanner.nextLine();
                    System.out.print("Satılan miktar: ");
                    int soldQuantity = scanner.nextInt();
                    inventory.reduceStock(soldProduct, soldQuantity);
                    break;
                case 6:
                    System.out.println("Sistemden çıkıldı...");
                    break;
                default:
                    System.out.println("Geçersiz seçim(!) Lütfen tekrar deneyin..");
            }
        } while (choice != 6);
        scanner.close();
    }

    // Ürünleri listeleme işlemlerinin yapıldığı kısım..
    private void listProducts() {
        if (inventory.getProducts().isEmpty()) {
            System.out.println("Henüz ürün eklenmedi.");
        } else {
            for (int i = 0; i < inventory.getProducts().size(); i++) {
                Product product = inventory.getProducts().get(i);
                System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + " TL, Stok: " + product.getStockQuantity());
            }
        }
    }

    // Yeni ürün ekleme işlemi
    private void addProduct(String name, double price, int stock) {
        Product product = new Product(name, price, stock);
        inventory.addProduct(product);
        writeProductToFile(product);
        System.out.println("Ürün başarıyla eklendi.");
    }

    // Yeni müşteri ekleme işlemi
    private void addCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        customers.add(customer);
        writeCustomerToFile(customer);
        System.out.println("Müşteri başarıyla eklendi.");
    }

    // Ürünleri dosyaya yazma işlemi
    private void writeProductToFile(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_FILE, true))) {
            writer.write(product.getName() + "," + product.getPrice() + "," + product.getStockQuantity());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ürün dosyasına yazarken bir hata oluştu.");
        }
    }

    // Müşterileri dosyaya yazma işlemi
    private void writeCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            writer.write(customer.getName() + "," + customer.getEmail());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Müşteri dosyasına yazarken bir hata oluştu.");
        }
    }
}
