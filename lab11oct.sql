-- Crear la base de datos BDMunshi
CREATE DATABASE BDMunshi;

-- Usar la base de datos
USE BDMunshi;

-- Crear la tabla producto
CREATE TABLE producto (
    CodigoProducto VARCHAR(100) PRIMARY KEY,
    NombreProducto VARCHAR(300) NOT NULL,
    PrecioUnitario DECIMAL(10,2) NOT NULL,
    CantidadProducto INT NOT NULL,
    FechaVencimiento DATE DEFAULT "2024-10-06"
);

-- Mostrar la estructura de la tabla
SHOW COLUMNS FROM producto;

-- Insertar datos de prueba
INSERT INTO producto (CodigoProducto, NombreProducto, PrecioUnitario, CantidadProducto, FechaVencimiento) 
VALUES
("A1", "HeladoMenta1", "7.75", "1", "2024-07-10"),
("B2", "HeladoFresa2", "5.50", "13", "2024-09-10"),
("C3", "HeladoChocolate3", "10.25", "11", "2024-10-10"),
("D4", "HeladoMenta1", "7.50", "10", "2024-07-10"),
("E5", "HeladoFresa2", "5.50", "13", "2024-09-10"),
("F6", "HeladoPistacho3", "5.25", "63", "2024-09-10"),
("G8", "HeladoChocoMani3", "12.78", "4", "2024-10-10"),
("H9", "HeladoChocoCoco21", "7.24", "10", "2024-12-10"),
("I10", "HeladoMania34", "6.25", "11", "2024-12-10"),
("J11", "HeladoPasa65", "1.20", "45", "2024-10-10"),
("K12", "HeladoRonPasas23", "15.24", "68", "2024-10-10"),
("L13", "HeladoRonCola56", "11.78", "16", "2024-10-10"),
("M14", "HeladoYougurt345", "2.25", "71", "2024-07-10"),
("N15", "HeladoYougfresa543", "13.25", "45", "2024-05-10"),
("Ñ16", "HeladoYougHigo535", "2.25", "89", "2024-10-10"),
("O17", "HeladoYougFrambuesa3343", "10.25", "95", "2024-05-10"),
("P18", "HeladoChocoCrema343", "19.56", "63", "2024-01-10"),
("Q19", "HeladoChocoCafe434", "10.74", "64", "2024-01-10"),
("R20", "HeladoCafe673", "18.25", "14", "2024-09-10"),
("S21", "HeladoYougCoco143", "10.25", "78", "2024-06-10"),
("T22", "HeladoZeroAzucar123", "7.96", "15", "2024-05-10"),
("U23", "HeladoMixto46", "1.25", "93", "2024-07-10"),
("V24", "HeladoFrutasMix7642", "4.25", "44", "2024-03-10"),
("W25", "HeladoSandilla673", "3.25", "20", "2024-10-10");

-- Consultar los datos
SELECT * FROM producto;

-- Actualizaciones de productos
UPDATE producto SET PrecioUnitario = 6 WHERE CodigoProducto = "C3";
UPDATE producto SET PrecioUnitario = 1 WHERE CodigoProducto = "D4";
UPDATE producto SET PrecioUnitario = 3 WHERE CodigoProducto = "E5";
UPDATE producto SET PrecioUnitario = 15 WHERE CodigoProducto = "F6";
UPDATE producto SET PrecioUnitario = 12 WHERE CodigoProducto = "G8";

-- Eliminaciones de productos
DELETE FROM producto WHERE CodigoProducto = "A1";
DELETE FROM producto WHERE CodigoProducto = "D4";
DELETE FROM producto WHERE CodigoProducto = "E5";
DELETE FROM producto WHERE CodigoProducto = "F6";
DELETE FROM producto WHERE CodigoProducto = "B2";
