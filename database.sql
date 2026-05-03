CREATE DATABASE tcomplex;
USE tcomplex;

CREATE TABLE matbang (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         maMatBang VARCHAR(20) UNIQUE,
                         trangThai VARCHAR(50),
                         dienTich DOUBLE,
                         tang INT,
                         loaiMatBang VARCHAR(50),
                         gia DOUBLE,
                         ngayBatDau DATE,
                         ngayKetThuc DATE
);

INSERT INTO matbang
(maMatBang, trangThai, dienTich, tang, loaiMatBang, gia, ngayBatDau, ngayKetThuc)
VALUES
    ('MB001', 'Trống', 100, 2, 'Cho thuê', 2000000, '2020-11-20', '2021-11-25'),
    ('MB002', 'Trống', 240, 5, 'Trọn gói', 5000000, '2020-04-03', '2020-10-03'),
    ('MB003', 'Hạ tầng', 80, 1, 'Cho thuê', 1500000, '2021-01-01', '2021-08-01'),
    ('MB004', 'Đầy đủ', 300, 10, 'Trọn gói', 8000000, '2022-03-10', '2023-03-10'),
    ('MB005', 'Trống', 150, 3, 'Cho thuê', 3000000, '2021-06-15', '2022-06-15'),
    ('MB006', 'Hạ tầng', 220, 7, 'Trọn gói', 6000000, '2020-09-01', '2021-09-01'),
    ('MB007', 'Đầy đủ', 180, 4, 'Cho thuê', 4000000, '2022-01-01', '2022-12-31'),
    ('MB008', 'Trống', 90, 2, 'Trọn gói', 2500000, '2021-05-01', '2022-05-01');