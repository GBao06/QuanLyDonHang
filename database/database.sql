CREATE DATABASE NoiThatDB;
GO

USE NoiThatDB;
GO
CREATE TABLE components (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    type VARCHAR(10) -- PRODUCT / COMBO
);
CREATE TABLE product (
    component_id INT PRIMARY KEY,
    price FLOAT,
    quantity INT,
    FOREIGN KEY (component_id) REFERENCES components(id)
);
CREATE TABLE combo_relations (
    parent_id INT,
    child_id INT,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES components(id),
    FOREIGN KEY (child_id) REFERENCES components(id)
);


INSERT INTO components (name, type) VALUES 
(N'Trọn bộ nội thất phòng khách', 'COMBO'), --1
(N'Combo Phòng khách cao cấp', 'COMBO'),    --2
(N'Combo Phòng khách cơ bản', 'COMBO'),     --3
(N'Combo Ánh sáng', 'COMBO'),               --4
(N'Combo Giải trí', 'COMBO'),               --5

(N'Sofa cao cấp', 'PRODUCT'),               --6
(N'Bàn trà gỗ', 'PRODUCT'),                 --7
(N'Thảm trải sàn lông cừu', 'PRODUCT'),     --8
(N'Gối tựa lưng', 'PRODUCT'),               --9 (x4)
(N'Đèn đứng cổ điển', 'PRODUCT'),           --10 (x2)
(N'Đèn bàn học', 'PRODUCT'),                --11
(N'Kệ TV', 'PRODUCT'),                      --12
(N'Màn hình 55 inch', 'PRODUCT'),           --13
(N'Loa soundbar', 'PRODUCT'),               --14
(N'Gối tựa lưng', 'PRODUCT');               --15 (x2)



INSERT INTO product VALUES 
(6, 10000, 1),  -- Sofa
(7, 3000, 1),
(8, 2000, 1),
(9, 200, 4),

(10, 1500, 2),
(11, 500, 1),

(12, 4000, 1),

(13, 12000, 1),
(14, 5000, 1),

(15, 200, 2);


-- Root
INSERT INTO combo_relations VALUES (1, 2);
INSERT INTO combo_relations VALUES (1, 5);
INSERT INTO combo_relations VALUES (1, 15);

-- Combo cao cấp
INSERT INTO combo_relations VALUES (2, 3);
INSERT INTO combo_relations VALUES (2, 4);
INSERT INTO combo_relations VALUES (2, 12);

-- Combo cơ bản
INSERT INTO combo_relations VALUES (3, 6);
INSERT INTO combo_relations VALUES (3, 7);
INSERT INTO combo_relations VALUES (3, 8);
INSERT INTO combo_relations VALUES (3, 9);

-- Combo ánh sáng
INSERT INTO combo_relations VALUES (4, 10);
INSERT INTO combo_relations VALUES (4, 11);

-- Combo giải trí
INSERT INTO combo_relations VALUES (5, 13);
INSERT INTO combo_relations VALUES (5, 14);
