package br.com.fiap.tiulanches.infra.kafka.pedido;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoListener;

@Component
public class PedidoKafka implements PedidoListener {
    
	private final PedidoController pedidoController; 	

	public PedidoKafka(PedidoController pedidoController) {
		this.pedidoController = pedidoController;
	}
    
    @Override    
    @KafkaListener(topics = { "topico-pedido-producao" }, groupId = "grupo-producao")
    public void processaMensagem(PedidoEvent pedidoEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        if (pedidoEvent.getEvento() == EventoEnum.CREATE){
            pedidoController.cadastrar(pedidoEvent.getPedidoDto());
        } 
    }   
}
