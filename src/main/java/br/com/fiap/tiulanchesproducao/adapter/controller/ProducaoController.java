package br.com.fiap.tiulanchesproducao.adapter.controller;

import br.com.fiap.tiulanchesproducao.adapter.repository.pedido.PedidoDto;

import java.util.List;

public interface ProducaoController {		
	public List<PedidoDto> consultaPainelPedido();
	public void cancelar(Long id);
	public void preparar(Long id);	
	public void entregar(Long id);
	public void finalizar(Long id);
}
