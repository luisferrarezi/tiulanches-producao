CREATE TABLE tlproducao.itens_pedidos(
	id_item bigint(20) NOT NULL,
	id_pedido bigint(20) NOT NULL,
	id_produto bigint(20) NOT NULL,	
	quantidade tinyint NOT NULL DEFAULT 0,
	observacao varchar(100),	
	PRIMARY KEY (id_item)
);

ALTER TABLE itens_pedidos ADD CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES produtos (id_produto);