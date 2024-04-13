package br.com.fiap.tiulanches.adapter.message.pedido;

import org.springframework.messaging.handler.annotation.Header;

public interface PedidoListener {
    public void processaMensagem(PedidoEvent pedidoEvent, @Header String topic); 
}
