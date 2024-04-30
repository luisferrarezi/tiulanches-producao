package br.com.fiap.tiulanches.utils.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tiulanches.adapter.repository.pedido.ItemPedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.StatusPedido;

public class PedidoPadrao {

    private ItemPedidoPadrao itemPedidoPadrao;
    private List<ItemPedido> listItemPedido = new ArrayList<>();
    private List<ItemPedidoDto> listItemPedidoDto = new ArrayList<>();

    public PedidoPadrao(){
        itemPedidoPadrao = new ItemPedidoPadrao();
        
        listItemPedido.add(itemPedidoPadrao.createItemPedido());

        listItemPedidoDto.add(itemPedidoPadrao.createItemPedidoDto());
    }

    public Pedido createPedido(){
        return new Pedido((Long) PedidoEnum.ID_PEDIDO.getValor(),
                          StatusPedido.RECEBIDO,
                          listItemPedido);
    }

    public PedidoDto createPedidoDto(){
        return new PedidoDto((Long) PedidoEnum.ID_PEDIDO.getValor(),
                             StatusPedido.RECEBIDO,
                             listItemPedidoDto);
    }    
}
