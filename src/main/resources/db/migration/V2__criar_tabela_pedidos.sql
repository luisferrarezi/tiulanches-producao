CREATE TABLE tlproducao.pedidos(
	id_pedido bigint(20) NOT NULL,
	status tinyint NOT NULL DEFAULT 0,	
	PRIMARY KEY (id_pedido)
);
