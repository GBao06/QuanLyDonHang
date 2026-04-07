package com.noithat.service;

import com.noithat.model.*;
import com.noithat.db.DBConnection;
import java.sql.*;

public class ComboBuilder {

    public static OrderComponent buildTree(int id) throws Exception {
        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM components WHERE id = ?"
        );
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return null;

        String name = rs.getString("name");
        String type = rs.getString("type");

        if (type.equals("PRODUCT")) {
            PreparedStatement ps2 = conn.prepareStatement(
                "SELECT * FROM product WHERE component_id = ?"
            );
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();

            return new Product(name,
                rs2.getDouble("price"),
                rs2.getInt("quantity"));
        } else {
            Combo combo = new Combo(name);

            PreparedStatement ps3 = conn.prepareStatement(
                "SELECT child_id FROM combo_relations WHERE parent_id = ?"
            );
            ps3.setInt(1, id);
            ResultSet rs3 = ps3.executeQuery();

            while (rs3.next()) {
                combo.add(buildTree(rs3.getInt("child_id")));
            }

            return combo;
        }
    }
}