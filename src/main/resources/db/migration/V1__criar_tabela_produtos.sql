CREATE TABLE tlproducao.produtos(
	id_produto bigint(20) NOT NULL,
	categoria tinyint NOT NULL,
	nome varchar(30) NOT NULL,
	tempo_preparo tinyint DEFAULT 0,
	PRIMARY KEY (id_produto)
);