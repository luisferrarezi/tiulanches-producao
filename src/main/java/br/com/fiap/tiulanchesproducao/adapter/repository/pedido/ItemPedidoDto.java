package br.com.fiap.tiulanchesproducao.adapter.repository.pedido;

import br.com.fiap.tiulanchesproducao.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanchesproducao.core.entitie.pedido.ItemPedido;
import io.swagger.v3.oas.annotations.media.Schema;

public record ItemPedidoDto(@Schema(description = "Código do item após ser criado", example = "17")
		                    long idItem,
		                    @Schema(description = "Pedido que o item pertence")
							long idPedido,
							@Schema(description = "Produto informado")
							ProdutoDto produto,
							@Schema(description = "Quantidade total do produto", example = "3")
							int quantidade,
							@Schema(description = "Observações do cliente para esse produto", example = "Sem queijo e mostarda", maxLength = 100)
							String observacao) {
	public ItemPedidoDto(ItemPedido itemPedido) {
		this(itemPedido.getIdItem(), 
			 itemPedido.getIdPedido(), 
			 new ProdutoDto(itemPedido.getProduto()),
			 itemPedido.getQuantidade(), 
			 itemPedido.getObservacao());
	}
}
