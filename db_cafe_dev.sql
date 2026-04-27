CREATE DATABASE db_cafe_dev;
USE db_cafe_dev;
SELECT * FROM cliente;

CREATE TABLE cliente ( -- lado one
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE pedido ( -- lado many
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 	
    -- ID numérico longo, AutoIncrement gera 1, 2, 3... automaticamente
    descricao VARCHAR(255) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP, -- Salva data/hora atual automaticamente
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) -- Vincula o pedido a um cliente real
);
