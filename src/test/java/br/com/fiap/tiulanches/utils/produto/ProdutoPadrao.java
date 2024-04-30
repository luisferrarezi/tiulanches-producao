package br.com.fiap.tiulanches.utils.produto;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;


public class ProdutoPadrao {

    public Produto createProduto(){
        return new Produto((Long) ProdutoEnum.ID_PRODUTO.getValor(), 
                           Categoria.LANCHE, 
                           (String) ProdutoEnum.NOME.getValor(),
                           (Integer) ProdutoEnum.TEMPO_PREPARO.getValor());
    }

    public ProdutoDto createProdutoDto(){
        return new ProdutoDto((Long) ProdutoEnum.ID_PRODUTO.getValor(),
                              Categoria.LANCHE, 
                              (String) ProdutoEnum.NOME.getValor(), 
                              (Integer) ProdutoEnum.TEMPO_PREPARO.getValor());
    }    
}
