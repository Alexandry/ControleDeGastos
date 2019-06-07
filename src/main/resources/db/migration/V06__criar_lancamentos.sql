CREATE TABLE lancamentos (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data TIMESTAMP,
	valor DECIMAL(10,2) NOT NULL,
	cdcategoria BIGINT(20),
	FOREIGN KEY (cdcategoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Salário mensal', '2017-06-10', 6500.00, 1);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Passagem Aérea', '1992-03-27', 1200.00, 1);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Viagen', '2017-02-10', 500.00, null);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Medicamentos', '2019-05-10', 60.33, 4);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Auto posto Shell', '2019-05-16', 650.00, 5);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Extra Supermercados', '2019-06-10', 6.25, 3);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Comida mexicana', '2018-06-02', 65.00, 2);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Compras do mês', '2016-06-03', 550.50, 3);
INSERT INTO lancamentos (descricao, data, valor, cdcategoria) values ('Doces', '2016-01-03', 50.00, null);