package br.com.fiap.tiulanches.utils.pedido;

import br.com.fiap.tiulanches.adapter.repository.pedido.ItemPedidoDto;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

public class ItemPedidoPadrao {
    private ProdutoPadrao produtoPadrao;

    public ItemPedidoPadrao(){
        produtoPadrao = new ProdutoPadrao();
    }

    public ItemPedido createItemPedido(){
        return new ItemPedido((Long) ItemPedidoEnum.ID_ITEM.getValor(),
                              (Long) ItemPedidoEnum.ID_PEDIDO.getValor(),
                              produtoPadrao.createProduto(),                              
                              (Integer) ItemPedidoEnum.QUANTIDADE.getValor(),
                              (String) ItemPedidoEnum.OBSERVACAO.getValor());
    }

    public ItemPedidoDto createItemPedidoDto(){
        return new ItemPedidoDto((Long) ItemPedidoEnum.ID_ITEM.getValor(),
                                 (Long) ItemPedidoEnum.ID_PEDIDO.getValor(),
                                 produtoPadrao.createProdutoDto(),
                                 (Integer) ItemPedidoEnum.QUANTIDADE.getValor(),
                                 (String) ItemPedidoEnum.OBSERVACAO.getValor());
    }    
}
