CREATE DATABASE achados_e_perdidos
USE achados_e_perdidos

CREATE TABLE Item_perdido(
id_item BIGINT IDENTITY PRIMARY KEY,
nome_item VARCHAR(50) NOT NULL,
local_encontrado VARCHAR(100) NOT NULL,
data_achado DATE NOT NULL,
status_item VARCHAR(50) NOT NULL,
descricao_devolucao VARCHAR(200) NULL

);