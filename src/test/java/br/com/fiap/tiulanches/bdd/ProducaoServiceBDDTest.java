package br.com.fiap.tiulanches.bdd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoMessage;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.core.service.ProducaoService;
import br.com.fiap.tiulanches.utils.pedido.PedidoEnum;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;

class ProducaoServiceBDDTest {
    private ProducaoService producaoService;
    private Optional<Pedido> pedidoTeste;
    private PedidoPadrao pedidoPadrao;

    private final Long idPedidoPadrao = (Long) PedidoEnum.ID_PEDIDO.getValor();

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PainelPedidoRepository painelPedidoRepository;

    @Mock
    private PedidoMessage pedidoMessage;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){
        openMocks = MockitoAnnotations.openMocks(this);
        
        pedidoPadrao = new PedidoPadrao();        
        pedidoTeste = Optional.ofNullable(pedidoPadrao.createPedido());
        producaoService = new ProducaoService(pedidoRepository, painelPedidoRepository, pedidoMessage);
    }

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testCancelar(){
        given(pedidoRepository.findById(anyLong())).willReturn(pedidoTeste);
        doReturn(pedidoPadrao.createPedido()).when(pedidoRepository).save(any(Pedido.class));
        doNothing().when(pedidoMessage).enviaStatusMensagem(any(EventoEnum.class), any(PedidoDto.class));

        assertDoesNotThrow(()-> producaoService.cancelar(idPedidoPadrao));
    }
}
