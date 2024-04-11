package br.com.fiap.tiulanches.adapter.controller;

import java.util.List;

import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;

public interface ProducaoController {		
	public List<PedidoDto> consultaPainelPedido();
	public void cancelar(Long id);
	public void preparar(Long id);	
	public void entregar(Long id);
	public void finalizar(Long id);
}
