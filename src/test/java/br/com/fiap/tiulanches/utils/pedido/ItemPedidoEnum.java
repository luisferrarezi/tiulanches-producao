package br.com.fiap.tiulanches.utils.pedido;

public enum ItemPedidoEnum {
    ID_ITEM(1L),
    ID_PEDIDO(1L),    
    QUANTIDADE(2),
    OBSERVACAO("teste");

    private Object valor;

    ItemPedidoEnum(Object valor){
        this.valor = valor;
    }

    public Object getValor(){
        return valor;
    }    
}
