package br.com.fiap.tiulanches.adapter.repository.produto;

import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoDto(@Schema(description = "Código do produto após ser criado", example = "1")
						 long idProduto,
						 @Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE")
						 Categoria categoria,
						 @Schema(description = "Nome do produto", example = "X-Tudo", maxLength = 30)
						 String nome,
						 @Schema(description = "Tempo em minutos necessário para preparar todo o produto.", example = "35")
						 int tempoPreparo) {
	public ProdutoDto(Produto produto) {
		this(produto.getIdProduto(), 
			 produto.getCategoria(), 
			 produto.getNome(), 
			 produto.getTempoPreparo());
	}	
}
