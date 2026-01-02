package com.inventory.dao;

import com.inventory.model.StockTransaction;
import com.inventory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockTransactionDAO {

    // record stock transaction
    public void insertTransaction(StockTransaction tx, Connection con) throws SQLException {

        String sql = "INSERT INTO stock_transactions(product_id, transaction_type, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tx.getProductId());
            ps.setString(2, tx.getTransactionType());
            ps.setInt(3, tx.getQuantity());
            ps.executeUpdate();
        }
    }
}

