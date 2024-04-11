package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.ProducaoController;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProducaoService implements ProducaoController {
	private final PainelPedidoRepository painelPedidoRepository;
	private final PedidoRepository pedidoRepository;	
	private static final String ENTIDADE = "Pedido";
	
	public ProducaoService(PedidoRepository pedidoRepository, PainelPedidoRepository painelPedidoRepository) {
		this.painelPedidoRepository = painelPedidoRepository;
		this.pedidoRepository = pedidoRepository;		
	}
	
	public List<PedidoDto> consultaPainelPedido() {
		try {
			List<Pedido> listPainelPedido = painelPedidoRepository.consultaPedidos(StatusPedido.RECEBIDO.ordinal(), 
				 																   StatusPedido.PREPARACAO.ordinal(), 
				 														      	   StatusPedido.PRONTO.ordinal());
		
			return listPainelPedido.stream().map(PedidoDto::new).collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new EntityNotFoundException();		
			
		} 
	}

	public void cancelar(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermiteCancelar()) {
			pedido.cancelar();
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser cancelado!", HttpStatus.BAD_REQUEST, ENTIDADE);
		}
	}
	
	public void preparar(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermitePreparo()) {		
			pedido.preparar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser preparado!", HttpStatus.BAD_REQUEST, ENTIDADE);
		}
	}
	
	@Override
	public void entregar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermiteEntregar()) {		
			pedido.entregar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser entregue!", HttpStatus.BAD_REQUEST, ENTIDADE);
		}
	}

	@Override
	public void finalizar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermiteFinalizar()) {		
			pedido.finalizar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser finalizado!", HttpStatus.BAD_REQUEST, ENTIDADE);
		}
	}	
}
