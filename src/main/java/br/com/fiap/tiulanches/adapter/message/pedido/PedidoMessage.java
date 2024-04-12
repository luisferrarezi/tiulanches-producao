package br.com.fiap.tiulanches.adapter.message.pedido;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;

public interface PedidoMessage {
    public void enviaStatusMensagem(EventoEnum evento, PedidoDto pedido);
}
