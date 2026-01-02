package com.inventory.service;
import java.sql.*;
import com.inventory.dao.*;
import com.inventory.util.DBUtil;
import com.inventory.model.*;
public class InventoryService {
    private final ProductDAO productDAO = new ProductDAO();
    private final StockTransactionDAO txDAO = new StockTransactionDAO();

    public void processTransaction(StockTransaction tx){
        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false); // START TRANSACTION

            int currentQty = productDAO.getQuantityByProductId(tx.getProductId(), con);

            if (currentQty == -1) {
                System.out.println("Product not found");
                con.rollback();
                return;
            }

            int updatedQty = tx.getTransactionType().equalsIgnoreCase("IN")
                    ? currentQty + tx.getQuantity()
                    : currentQty - tx.getQuantity();

            if (updatedQty < 0) {
                System.out.println("Insufficient stock");
                con.rollback();
                return;
            }

            productDAO.updateQuantity(tx.getProductId(), updatedQty, con);
            txDAO.insertTransaction(tx, con);

            con.commit();
            System.out.println("Stock transaction successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
