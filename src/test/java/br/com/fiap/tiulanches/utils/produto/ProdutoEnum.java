package br.com.fiap.tiulanches.utils.produto;

public enum ProdutoEnum {
    ID_PRODUTO(10L),
    NOME("Teste"),    
    TEMPO_PREPARO(10);

    private Object valor;

    ProdutoEnum(Object valor){
        this.valor = valor;
    }

    public Object getValor(){
        return valor;
    }
}
