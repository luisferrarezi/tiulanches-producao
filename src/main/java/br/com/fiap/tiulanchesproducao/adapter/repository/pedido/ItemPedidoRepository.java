package br.com.fiap.tiulanchesproducao.adapter.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanchesproducao.core.entitie.pedido.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
}
