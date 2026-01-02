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
            System.out.println(e.getMessage());
        }
    }

    // view all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
            System.out.println(e.getMessage());
        }

        return products;
    }

    // update quantity
    public void updateQuantity(int productId, int newQuantity, Connection con) throws SQLException {
        String sql = "UPDATE products SET quantity = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    public int getQuantityByProductId(int productId, Connection con) throws SQLException {
        String sql = "SELECT quantity FROM products WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("quantity");
        }
        return -1;
    }

    //view low stock products
    public List<Product> getLowStockProducts(int threshold){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE quantity < ?";
        try(Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.executeUpdate();
            System.out.println("Product deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

}
