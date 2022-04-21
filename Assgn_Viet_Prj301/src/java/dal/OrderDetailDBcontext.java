/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;

/**
 *
 * @author Admin
 */
public class OrderDetailDBcontext extends DBContext{
    public void saveCart(int orderId, Map<Integer, Cart> carts) {
        try {

            String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                    + "           ([order_id]\n"
                    + "           ,[productName]\n"
                    + "           ,[productImage]\n"
                    + "           ,[productPrice]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cart cart = entry.getValue();
                stm.setString(2, cart.getProduct().getName());
                stm.setString(3, cart.getProduct().getImageUrl());
                stm.setDouble(4, cart.getProduct().getPrice());
                stm.setDouble(5, cart.getQuantity());
                stm.executeUpdate();
            }

        } catch (Exception ex) {
            Logger.getLogger(OrderDetailDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
