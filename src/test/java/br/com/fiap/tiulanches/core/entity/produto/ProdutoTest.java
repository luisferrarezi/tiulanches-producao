package br.com.fiap.tiulanches.core.entity.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.utils.produto.ProdutoEnum;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ProdutoTest {
    
    private Produto produto;
    private ProdutoDto produtoDto;
    private ProdutoPadrao produtoPadrao;

    private final Long idProdutoPadrao = (Long) ProdutoEnum.ID_PRODUTO.getValor();
    private final String nomePadrao = (String) ProdutoEnum.NOME.getValor();
    private final Integer tempoPreparoPadrao = (Integer) ProdutoEnum.TEMPO_PREPARO.getValor();

    @BeforeEach
    void beforeEach(){
        produtoPadrao = new ProdutoPadrao();
    }

    @Test
    void constructorAllArgumentsTest(){
        produto = produtoPadrao.createProduto();

        assertEquals(idProdutoPadrao.longValue(), produto.getIdProduto());
        assertEquals(Categoria.LANCHE, produto.getCategoria());
        assertEquals(nomePadrao, produto.getNome());
        assertEquals(tempoPreparoPadrao.intValue(), produto.getTempoPreparo());
    }

    @Test
    void constructorByProdutoTest(){
        produto = new Produto();

        produto.setIdProduto(idProdutoPadrao);
        produto.setCategoria(Categoria.LANCHE);
        produto.setNome(nomePadrao);
        produto.setTempoPreparo(tempoPreparoPadrao);

        assertEquals(idProdutoPadrao.longValue(), produto.getIdProduto());
        assertEquals(Categoria.LANCHE, produto.getCategoria());
        assertEquals(nomePadrao, produto.getNome());
        assertEquals(tempoPreparoPadrao.intValue(), produto.getTempoPreparo());
    }
    
    @Test
    void equalsTest(){
        produto = produtoPadrao.createProduto();
        Produto produto2 = produtoPadrao.createProduto();

        assertDoesNotThrow(()->produto.equals(produto2));
    }

    @Test
    void hashCodeTest(){
        produto = produtoPadrao.createProduto();

        assertDoesNotThrow(()->produto.hashCode());
    }

    @Test
    void produtoCadastrarTest(){
        produtoDto = produtoPadrao.createProdutoDto();

        produto = new Produto();
        produto.cadastrar(produtoDto);
        
        assertEquals(Categoria.LANCHE, produto.getCategoria());
        assertEquals(nomePadrao, produto.getNome());
        assertEquals(tempoPreparoPadrao.intValue(), produto.getTempoPreparo());
    }    
   
    @Test
    void produtoRegistrarNullTest(){
        produtoDto = new ProdutoDto(idProdutoPadrao, null, null, 0);

        produto = new Produto();
        produto.atualizar(produtoDto);
        
        assertEquals(null, produto.getCategoria());
        assertEquals(null, produto.getNome());
        assertEquals(0, produto.getTempoPreparo());
    }            
}
