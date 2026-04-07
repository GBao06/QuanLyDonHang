# 🪑 Quản lý đơn hàng nội thất (Composite Pattern - Java + SQL Server)

## 📌 Giới thiệu

Dự án xây dựng hệ thống quản lý **đơn hàng nội thất**, bao gồm:

* Sản phẩm đơn lẻ (Product): bàn, ghế, sofa,...
* Bộ sản phẩm (Combo): tập hợp nhiều sản phẩm hoặc combo con

👉 Áp dụng **Composite Design Pattern** để biểu diễn cấu trúc dạng cây.

---

## 🧠 Mô hình thiết kế (Composite)

* `OrderComponent` (interface): thành phần chung
* `Product` (Leaf): sản phẩm đơn
* `Combo` (Composite): chứa nhiều Product hoặc Combo

📌 Cho phép:

* Lồng combo nhiều cấp
* Tính tổng tiền đệ quy
* In cấu trúc dạng cây

---

## 🌳 Cấu trúc dữ liệu

```
Trọn bộ nội thất phòng khách
├── Combo Phòng khách cao cấp
│   ├── Combo Phòng khách cơ bản
│   │   ├── Sofa cao cấp
│   │   ├── Bàn trà gỗ
│   │   ├── Thảm trải sàn lông cừu
│   │   └── Gối tựa lưng (x4)
│   ├── Combo Ánh sáng
│   │   ├── Đèn đứng cổ điển (x2)
│   │   └── Đèn bàn học
│   └── Kệ TV
├── Combo Giải trí
│   ├── Màn hình 55 inch
│   └── Loa soundbar
└── Gối tựa lưng (x2)
```

---

## 🗄️ Cơ sở dữ liệu (SQL Server)

### 🔹 Bảng `components`

```sql
CREATE TABLE components (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    type VARCHAR(10) -- PRODUCT / COMBO
);
```

### 🔹 Bảng `product`

```sql
CREATE TABLE product (
    component_id INT PRIMARY KEY,
    price FLOAT,
    quantity INT,
    FOREIGN KEY (component_id) REFERENCES components(id)
);
```

### 🔹 Bảng `combo_relations`

```sql
CREATE TABLE combo_relations (
    parent_id INT,
    child_id INT,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES components(id),
    FOREIGN KEY (child_id) REFERENCES components(id)
);
```

---

## ☕ Công nghệ sử dụng

* Java (OOP)
* JDBC
* SQL Server
* Design Pattern: Composite

---

## 📁 Cấu trúc project

```
com.noithat
├── model
│   ├── OrderComponent.java
│   ├── Product.java
│   └── Combo.java
├── db
│   └── DBConnection.java
├── service
│   └── ComboBuilder.java
└── main
    └── Main.java
```

---

## 🚀 Cách chạy chương trình

### 1. Cấu hình database

* Tạo database `NoiThatDB`
* Chạy script tạo bảng
* Insert dữ liệu

---

### 2. Cấu hình kết nối DB

Sửa file `DBConnection.java`:

```java
String url = "jdbc:sqlserver://localhost:1433;databaseName=NoiThatDB;encrypt=false";
String user = "sa";
String password = "123";
```

---

### 3. Chạy chương trình

```java
public static void main(String[] args) {
    OrderComponent root = ComboBuilder.buildTree(1);
    root.print("");
    System.out.println("Tổng tiền: " + root.getTotalPrice());
}
```

---

## 🖨️ Kết quả

```
+ Trọn bộ nội thất phòng khách
   + Combo Phòng khách cao cấp
      + Combo Phòng khách cơ bản
         - Sofa cao cấp (x1)
         - Bàn trà gỗ (x1)
         ...
```

---

## 🎯 Tính năng chính

✅ Quản lý sản phẩm & combo dạng cây
✅ Tính tổng tiền đệ quy
✅ Lưu trữ bằng CSDL quan hệ
✅ Load dữ liệu từ DB → dựng lại cấu trúc
✅ In cây phân cấp rõ ràng

---
