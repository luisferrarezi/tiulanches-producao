package br.com.fiap.tiulanches.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tiulanches.adapter.controller.ProducaoController;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/producao")
public class ProducaoApi {
	
	private final ProducaoController pedidoController;	
	
	public ProducaoApi(ProducaoController pedidoController){
		this.pedidoController = pedidoController;		
	};
	
	private static Logger logger = LoggerFactory.getLogger(ProducaoApi.class);

	@GetMapping(value = "/painel", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista os pedidos com status Recebidos, Em Preparação e Prontos", description = "Lista não paginada os pedidos com status Recebidos, Em Preparação e Prontos", tags = {"Painel Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista os pedidos com status Recebidos, Em Preparação e Prontos sem paginação"),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public List<PedidoDto> consultarPainelPedidos(){
		logger.info("Consultar pedidos para o painel de pedidos");
		
		return pedidoController.consultaPainelPedido();
	}

	@PutMapping(value = "/cancelar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cancela o pedido", description = "Cancela o pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, pedido cancelado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> cancelar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1")
			                                  Long id){
		logger.info("Cancelar pedido pelo idPedido: {}", id);
		
		pedidoController.cancelar(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/preparar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Inicia preparação do pedido", description = "Inicia preparação do pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, iniciada preparação do pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de recebido ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> preparar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1")
			                                  Long id){
		logger.info("Iniciar preparação do pedido pelo idPedido: {}", id);
		
		pedidoController.preparar(id);		
		
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping(value = "/entregar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Entrega do pedido", description = "Entrega do pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, entrega o pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de preparação ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> entregar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1")
			                                  Long id){
		logger.info("Entrega do pedido pelo idPedido: {}", id);
		
		pedidoController.entregar(id);		
		
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping(value = "/finalizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finaliza o pedido", description = "Finaliza o pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, finaliza o pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de pronto ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> finalizar(@ParameterObject @PathVariable @NotNull
			                                   @Schema(description = "Código do pedido no sistema", example = "1")
			                                   Long id){
		logger.info("Finaliza o pedido pelo idPedido: {}", id);
		
		pedidoController.finalizar(id);		
		
		return ResponseEntity.noContent().build();
	}			
}
