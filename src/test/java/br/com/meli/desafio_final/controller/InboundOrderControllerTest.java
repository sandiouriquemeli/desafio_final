package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.InBoundOrder;
import br.com.meli.desafio_final.service.implementation.InBoundOrderService;
import br.com.meli.desafio_final.util.InboundOrderDtoUtils;
import br.com.meli.desafio_final.util.InboundOrderUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InboundOrderControllerTest {

    @InjectMocks
    private InBoundOrderController inBoundOrderController;

    @Mock
    private InBoundOrderService inBoundOrderService;

    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI
    // TODO: MUDAR PARA IMPORT ESTÁTICO DO ArgumentsMatchers PARA MELHORARAR A LEITURA E IDENTAÇÃO

    @Test
    public void testCreateInboundOrder() {
        BDDMockito.when(inBoundOrderService.create(
                ArgumentMatchers.any(InBoundOrder.class), ArgumentMatchers.any(Long.class)))
                .thenReturn(InboundOrderDtoUtils.inBoundOrderDtoList());

        ResponseEntity<List<InBoundOrderDto>> inboundOrderResponse = inBoundOrderController.createInBoundOrder(1L, InboundOrderUtils.newInboundOrder());

        Assertions.assertThat(inboundOrderResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testUpdateInboundOrder() {
        BDDMockito.when(inBoundOrderService.update(
                ArgumentMatchers.any(InBoundOrder.class), ArgumentMatchers.any(Long.class)))
                .thenReturn(InboundOrderDtoUtils.inBoundOrderDtoList());

        ResponseEntity<List<InBoundOrderDto>> inboundOrderResponse = inBoundOrderController.updateInBoundOrder(1L, InboundOrderUtils.newInboundOrder());

        Assertions.assertThat(inboundOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
