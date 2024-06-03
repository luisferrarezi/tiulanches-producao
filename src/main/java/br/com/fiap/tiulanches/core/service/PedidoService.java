package br.com.fiap.tiulanches.core.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService implements PedidoController {
	private final PedidoRepository pedidoRepository;	
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	@Transactional
	public void cadastrar(PedidoDto dto){
		Pedido pedido = new Pedido();
		pedido.cadastrar(dto);
		
		ObjectMapper mapper = new ObjectMapper();
		List<ItemPedido> listItemPedido = mapper.convertValue(dto.listItemPedido(), new TypeReference<List<ItemPedido>>() {});
		
		for (ItemPedido itemPedido : listItemPedido) {
			pedido.adicionarItem(itemPedido);
		}		
		
		pedidoRepository.save(pedido);
	}

	@Transactional
	public void preparar(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		pedido.preparar();		
		pedidoRepository.save(pedido);
	}
}
