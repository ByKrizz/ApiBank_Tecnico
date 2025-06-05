CREATE DATABASE pruebatecnica;

USE pruebatecnica;

CREATE TABLE tbl_clientes (
    cliente_id VARCHAR(255) NOT NULL PRIMARY KEY,
    identificacion VARCHAR(255),
    nombres VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    contrasena VARCHAR(255),
    estado BOOLEAN
);

CREATE TABLE tbl_cuentas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(255),
    tipo VARCHAR(255),
    saldo_inicial DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
    estado BOOLEAN,
    cliente_id VARCHAR(255),
    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES tbl_clientes(cliente_id)
);

CREATE TABLE tbl_movimiento (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cuenta_id BIGINT,
    tipo VARCHAR(255), 
    valor DECIMAL(19, 2),
    saldo_disponible DECIMAL(19, 2),
    descripcion VARCHAR(255),
    fecha DATETIME,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES tbl_cuentas(id)
);