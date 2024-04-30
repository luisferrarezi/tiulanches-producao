package br.com.fiap.tiulanches.core.entity.produto;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Produto")
@Table(name = "PRODUTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idProduto")
public class Produto {
	@Id
	@Schema(description = "Código do produto após ser criado", example = "1")
	private long idProduto;
	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE")	
	private Categoria categoria;
	
	@Size(max=30)
	@Schema(description = "Nome do produto", example = "X-Tudo", maxLength = 30)	
	private String nome;
	
	@Schema(description = "Tempo em minutos necessário para preparar todo o produto.", example = "35")
	private int tempoPreparo;

	public void cadastrar(ProdutoDto produtoDto) {
		this.idProduto 	  = produtoDto.idProduto();
		this.categoria 	  = produtoDto.categoria();
		this.nome 	   	  = produtoDto.nome();
		this.tempoPreparo = produtoDto.tempoPreparo();
	}		

	public void atualizar(ProdutoDto produtoDto) {
		this.categoria 	  = produtoDto.categoria();
		this.nome 	   	  = produtoDto.nome();
		this.tempoPreparo = produtoDto.tempoPreparo();
	}				
}
