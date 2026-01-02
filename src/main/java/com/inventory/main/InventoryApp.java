package com.inventory.main;
import com.inventory.model.*;
import com.inventory.dao.*;
import java.util.*;
import com.inventory.service.InventoryService;
public class InventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO();
        InventoryService inventoryService = new InventoryService();

        while (true){
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Stock IN");
            System.out.println("4. Stock OUT");
            System.out.println("5. View Low Stock Products");
            System.out.println("6. View Products By Category");
            System.out.println("7. Delete Product");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice){
                case 1: sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter initial quantity: ");
                    int qty = sc.nextInt();

                    Product product = new Product(name, category, price, qty);
                    productDAO.addProduct(product);
                    break;


                case 2:
                    List<Product> products = productDAO.getAllProducts();
                    System.out.println("\n--- PRODUCT LIST ---");
                    for (Product p : products) {
                        System.out.println(
                                p.getId() + " | " +
                                        p.getName() + " | " +
                                        p.getCategory() + " | " +
                                        p.getPrice() + " | Qty: " +
                                        p.getQuantity()
                        );
                    }
                    break;


                case 3:
                    System.out.print("Enter product ID: ");
                    int inId = sc.nextInt();

                    System.out.print("Enter quantity to add: ");
                    int inQty = sc.nextInt();

                    inventoryService.processTransaction(
                            new StockTransaction(inId, "IN", inQty)
                    );
                    break;


                case 4:
                    System.out.print("Enter product ID: ");
                    int outId = sc.nextInt();

                    System.out.print("Enter quantity to remove: ");
                    int outQty = sc.nextInt();

                    inventoryService.processTransaction(
                            new StockTransaction(outId, "OUT", outQty)
                    );
                    break;

                case 5:
                    List<Product> lowStock = productDAO.getLowStockProducts(5);
                    System.out.println("\n LOW STOCK PRODUCTS");
                    for(Product p : lowStock){
                        System.out.println(
                                p.getId() + " | " +
                                p.getName() + " | " +
                                p.getQuantity()
                        );
                    }
                    break;

                case 6:
                    sc.nextLine(); // consume newline
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();

                    List<Product> categoryProducts = productDAO.getProductsByCategory(cat);

                    if (categoryProducts.isEmpty()) {
                        System.out.println("No products found for category: " + cat);
                    } else {
                        System.out.println("\n--- PRODUCTS IN CATEGORY: " + cat + " ---");
                        for (Product p : categoryProducts) {
                            System.out.println(
                                    p.getId() + " | " + p.getName() + " | " + p.getPrice() + " | Qty: " + p.getQuantity()
                            );
                        }
                    }
                    break;

                case 7:
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = sc.nextInt();

                    productDAO.deleteProduct(deleteId);
                    break;

                case 8:
                    System.out.println("Exiting Inventory System...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
