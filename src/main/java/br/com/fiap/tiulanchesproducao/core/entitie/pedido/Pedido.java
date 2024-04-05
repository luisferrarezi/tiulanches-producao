package br.com.fiap.tiulanchesproducao.core.entitie.pedido;

import java.util.List;

import java.util.ArrayList;
import br.com.fiap.tiulanchesproducao.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Pedido")
@Table(name = "PEDIDOS")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	@Id
	@Getter	
	@Column(name = "id_pedido")
	@Schema(description = "Código do pedido após ser criado", example = "17")	
	private long idPedido;
	
	@Getter	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO")	
	private StatusPedido status;		
    
	@Getter
	@Setter
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_pedido")
	@Schema(description = "Lista itens do pedido")	
	private List<ItemPedido> listItemPedido = new ArrayList<>();
	
	public void adicionarItem(ItemPedido item) {
		item.setIdPedido(this.idPedido);
		this.listItemPedido.add(item);			
	}
	
	public void removerItem(ItemPedido item) {
		this.listItemPedido.remove(item);			
	}

	public void cancelar() {
		this.status = StatusPedido.CANCELADO;
	}	
	
	public void preparar() {
		this.status = StatusPedido.PREPARACAO;
	}		
	
	public void entregar() {
		this.status = StatusPedido.PRONTO;
	}		
	
	public void finalizar() {
		this.status = StatusPedido.FINALIZADO;
	}		
	
	public boolean isPermiteCancelar() {
		return this.status == StatusPedido.RECEBIDO;
	}			
	
	public boolean isPermitePreparo() {
		return this.status == StatusPedido.RECEBIDO;
	}			
	
	public boolean isPermiteEntregar() {
		return this.status == StatusPedido.PREPARACAO;
	}			
	
	public boolean isPermiteFinalizar() {
		return this.status == StatusPedido.PRONTO;
	}					
}
