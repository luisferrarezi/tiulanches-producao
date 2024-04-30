package br.com.fiap.tiulanches.core.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.utils.pedido.PedidoEnum;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;

class PedidoServiceTest {
    private PedidoService pedidoService;
    private Optional<Pedido> pedidoTeste;
    private PedidoDto pedidoDtoTeste;
    private PedidoPadrao pedidoPadrao;

    @Mock
    private PedidoRepository pedidoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){
        openMocks = MockitoAnnotations.openMocks(this);
        
        pedidoPadrao = new PedidoPadrao();
        pedidoDtoTeste = pedidoPadrao.createPedidoDto();
        pedidoTeste = Optional.ofNullable(pedidoPadrao.createPedido());
        pedidoService = new PedidoService(pedidoRepository);
    }

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testCadastrar() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPadrao.createPedido());

        assertDoesNotThrow(()-> pedidoService.cadastrar(pedidoDtoTeste));
    }

    @Test
    void testPreparar() {
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);       
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPadrao.createPedido());
        
        assertDoesNotThrow(()-> pedidoService.preparar((Long) PedidoEnum.ID_PEDIDO.getValor()));
    }    
}
