package br.com.fiap.tiulanches.api;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.fiap.tiulanches.adapter.controller.ProducaoController;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.exception.ExceptionErros;
import br.com.fiap.tiulanches.utils.pedido.PedidoEnum;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;

class ProducaoApiTest {
    private MockMvc mockMvc;
    private PedidoPadrao pedidoPadrao;

    private final Long idPedidoPadrao = (Long) PedidoEnum.ID_PEDIDO.getValor();

    @Mock
    ProducaoController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){
        pedidoPadrao = new PedidoPadrao();

        openMocks = MockitoAnnotations.openMocks(this);

        ProducaoApi api = new ProducaoApi(controller);
        
        mockMvc = MockMvcBuilders.standaloneSetup(api)
            .setControllerAdvice(new ExceptionErros())
            .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
            .build();    
    }

    @AfterEach
    void afterEach() throws Exception {
        openMocks.close();
    }    
    
    @Test
    void testCancelar() throws Exception {
        doNothing().when(controller).cancelar(anyLong());

        mockMvc.perform(put("/producao/cancelar/{id}", idPedidoPadrao.longValue()))
            .andExpect(status().isNoContent());
    }

    @Test
    void testConsultarPainelPedidos() throws Exception {
        PedidoDto pedidoDto = pedidoPadrao.createPedidoDto();
        List<PedidoDto> listPedido = new ArrayList<>(Collections.singletonList(
            pedidoDto
        ));

        when(controller.consultaPainelPedido()).thenReturn(listPedido);
        mockMvc.perform(get("/producao/painel")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testEntregar() throws Exception {
        doNothing().when(controller).entregar(anyLong());

        mockMvc.perform(put("/producao/entregar/{id}", idPedidoPadrao.longValue()))
            .andExpect(status().isNoContent());
    }

    @Test
    void testFinalizar() throws Exception {
        doNothing().when(controller).finalizar(anyLong());

        mockMvc.perform(put("/producao/finalizar/{id}", idPedidoPadrao.longValue()))
            .andExpect(status().isNoContent());
    }
}
