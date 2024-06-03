package br.com.fiap.tiulanches.infra.kafka.pedido;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoMessage;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;

@Component
public class EnviaPedido implements PedidoMessage {

    private final KafkaTemplate<String, Object> kafka;

    public EnviaPedido(KafkaTemplate<String, Object> kafka) {
		    this.kafka = kafka;
	  }

    @Override
    public void enviaStatusMensagem(EventoEnum evento, PedidoDto pedido) {
        PedidoEvent pedidoEvent = new PedidoEvent(evento, pedido);
        
        kafka.send("topico-producao-status-pedido", pedidoEvent);
    }    
}
