package br.com.fiap.tiulanchesproducao.adapter.repository.pedido;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.tiulanchesproducao.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanchesproducao.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

public record PedidoDto (@Schema(description = "Código do pedido após ser criado", example = "17")
						 long idPedido,
						 @Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO")
						 StatusPedido status,
						 @Schema(description = "Lista itens do pedido")
						 List<ItemPedidoDto> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), 
			 pedido.getStatus(), 
			 pedido.getListItemPedido().stream().map(ItemPedidoDto::new).collect(Collectors.toList()));
	}	
}
