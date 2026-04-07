package com.noithat.main;

import com.noithat.model.OrderComponent;
import com.noithat.service.ComboBuilder;

public class ChuongTrinh {
    public static void main(String[] args) {

        try {
            // 🔹 ID = 1 là combo gốc trong DB
            OrderComponent root = ComboBuilder.buildTree(1);

            if (root == null) {
                System.out.println("Không tìm thấy dữ liệu!");
                return;
            }

            // 🔹 In cấu trúc cây
            System.out.println("===== CẤU TRÚC COMBO =====");
            root.print("");

            // 🔹 Tính tổng tiền
            System.out.println("\n===== TỔNG TIỀN =====");
            System.out.println("Tổng tiền: " + root.getTotalPrice());

        } catch (Exception e) {
            System.out.println("Lỗi chương trình!");
            e.printStackTrace();
        }
    }
}
