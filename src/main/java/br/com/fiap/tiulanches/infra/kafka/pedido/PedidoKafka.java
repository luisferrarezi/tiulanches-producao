package br.com.fiap.tiulanches.infra.kafka.pedido;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoListener;

@Service
public class PedidoKafka implements PedidoListener {
    
	private final PedidoController pedidoController; 	

	public PedidoKafka(PedidoController pedidoController) {
		this.pedidoController = pedidoController;
	}
    
    @Override    
    @KafkaListener(topics = { "topico-pedido-producao", "topico-pagamento-pedido" }, groupId = "grupo-producao")
    public void processaMensagem(PedidoEvent pedidoEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        if (topic.equalsIgnoreCase("topico-pedido-producao") && 
            (pedidoEvent.getEvento() == EventoEnum.CREATE)){
            pedidoController.cadastrar(pedidoEvent.getPedidoDto());

        } else 
        if (topic.equalsIgnoreCase("topico-pagamento-pedido") && 
            (pedidoEvent.getEvento() == EventoEnum.UPDATE)){
            pedidoController.preparar(pedidoEvent.getPedidoDto().idPedido());
        }
    }   
}
