package com.noithat.ui;

import com.noithat.model.*;
import com.noithat.service.ComboBuilder;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ComboUI extends JFrame {

    private JTree tree;
    private JLabel totalLabel;

    public ComboUI() {
        setTitle("Quản lý nội thất");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Tree
        tree = new JTree(new DefaultMutableTreeNode("Chưa load dữ liệu"));
        add(new JScrollPane(tree), BorderLayout.CENTER);

        // Panel dưới
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton loadBtn = new JButton("Load từ DB");
        totalLabel = new JLabel("Tổng tiền: 0");

        bottomPanel.add(loadBtn, BorderLayout.WEST);
        bottomPanel.add(totalLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
        
        // Sự kiện nút
        loadBtn.addActionListener(e -> loadData());
    }

    // Load dữ liệu từ DB
    private void loadData() {
        try {
            OrderComponent root = ComboBuilder.buildTree(1);

            DefaultMutableTreeNode rootNode = buildTreeNode(root);

            tree.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));

            totalLabel.setText("Tổng tiền: " + root.getTotalPrice());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    // Convert Composite → JTree
    private DefaultMutableTreeNode buildTreeNode(OrderComponent comp) {

        if (comp instanceof Product) {
            Product p = (Product) comp;
            return new DefaultMutableTreeNode(
                p.getName() + " (x) - " + p.getTotalPrice()
            );
        }

        Combo combo = (Combo) comp;

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            combo.getName() + " - " + combo.getTotalPrice()
        );

        //dùng getter thay vì reflection
        for (OrderComponent child : combo.getList()) {
            node.add(buildTreeNode(child));
        }

        return node;
    }

}