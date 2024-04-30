package br.com.fiap.tiulanches.core.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoMessage;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.utils.pedido.PedidoEnum;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;

class ProducaoServiceTest {
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
    void testConsultaByCategoria() {
        List<Pedido> list = new ArrayList<>(Collections.emptyList());

        when(painelPedidoRepository.consultaPedidos(anyInt(), anyInt(), anyInt())).thenReturn(list);
        List<PedidoDto> pedidos = producaoService.consultaPainelPedido();
        assertTrue(pedidos.isEmpty());
    }

    @Test
    void testCancelar(){
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        doReturn(pedidoPadrao.createPedido()).when(pedidoRepository).save(any(Pedido.class));
        doNothing().when(pedidoMessage).enviaStatusMensagem(any(EventoEnum.class), any(PedidoDto.class));

        assertDoesNotThrow(()-> producaoService.cancelar(idPedidoPadrao));

        pedidoTeste.get().cancelar();
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        BusinessException exception = assertThrows(BusinessException.class, ()-> producaoService.cancelar(idPedidoPadrao));
        assertEquals("Pedido não pode ser cancelado!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testEntregar(){
        pedidoTeste.get().preparar();
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        doReturn(pedidoPadrao.createPedido()).when(pedidoRepository).save(any(Pedido.class));
        doNothing().when(pedidoMessage).enviaStatusMensagem(any(EventoEnum.class), any(PedidoDto.class));

        assertDoesNotThrow(()-> producaoService.entregar(idPedidoPadrao));

        pedidoTeste.get().cancelar();
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        BusinessException exception = assertThrows(BusinessException.class, ()-> producaoService.entregar(idPedidoPadrao));
        assertEquals("Pedido não pode ser entregue!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testFinalizar(){
        pedidoTeste.get().entregar();
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        doReturn(pedidoPadrao.createPedido()).when(pedidoRepository).save(any(Pedido.class));
        doNothing().when(pedidoMessage).enviaStatusMensagem(any(EventoEnum.class), any(PedidoDto.class));

        assertDoesNotThrow(()-> producaoService.finalizar(idPedidoPadrao));

        pedidoTeste.get().cancelar();
        when(pedidoRepository.findById(anyLong())).thenReturn(pedidoTeste);
        BusinessException exception = assertThrows(BusinessException.class, ()-> producaoService.finalizar(idPedidoPadrao));
        assertEquals("Pedido não pode ser finalizado!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }    
}
