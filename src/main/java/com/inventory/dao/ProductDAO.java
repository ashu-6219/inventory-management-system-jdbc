package com.inventory.dao;
import com.inventory.model.Product;
import com.inventory.util.DBUtil;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class ProductDAO {
    //add product
    public void addProduct(Product product){
        String sql = "INSERT INTO products(name, category, price, quantity) VALUES (?, ?, ?, ?)";
        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.executeUpdate();
            System.out.println("Product added successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // view all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DBUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // update quantity
    public void updateQuantity(int productId, int newQuantity) {
        String sql = "UPDATE products SET quantity = ? WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
