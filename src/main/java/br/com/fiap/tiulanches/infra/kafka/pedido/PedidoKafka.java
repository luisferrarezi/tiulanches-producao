package br.com.fiap.tiulanches.infra.kafka.pedido;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoListener;

@Service
public class PedidoKafka implements PedidoListener {
    
	private final PedidoController controller; 	

	public PedidoKafka(PedidoController controller) {
		this.controller = controller;		
	}
    
    @Override    
    @KafkaListener(topics = "topico-pedido-producao", groupId = "grupo-producao")
    public void processaMensagem(PedidoEvent pedidoEvent) {
        if (pedidoEvent.getEvento() == EventoEnum.CREATE){
            controller.cadastrar(pedidoEvent.getPedidoDto());
        }
    }
}
