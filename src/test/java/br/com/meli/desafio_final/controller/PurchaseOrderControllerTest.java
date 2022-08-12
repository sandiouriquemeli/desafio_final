package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.dto.PurchaseOrderDto;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.service.implementation.PurchaseOrderService;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class PurchaseOrderControllerTest {

    @InjectMocks
    private PurchaseOrderController purchaseOrderController;

    @Mock
    private PurchaseOrderService purchaseOrderService;

    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testCreateInboundOrder() {
        BDDMockito.when(purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave()))
                .thenReturn(555D);

        ResponseEntity<Double> purchaseOrderResponse = purchaseOrderController.save(PurchaseOrderUtils.newPurchase1ToSave());

        Assertions.assertThat(purchaseOrderResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testFindAdsensesByPurchaseOrderId() {
        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();
        List<AdsenseDto> adsenseDtoList = AdsenseDto.convertDto(AdsenseUtils.generateAdsenseList());

        BDDMockito.when(purchaseOrderService.findAdsensesByPurchaseOrderId(purchaseOrder.getId()))
                .thenReturn(adsenseDtoList);

        ResponseEntity<List<AdsenseDto>> purchaseOrderResponse = purchaseOrderController
                .findAdsensesByPurchaseOrderId(purchaseOrder.getId());

        Assertions.assertThat(purchaseOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(purchaseOrderResponse.getBody().size()).isEqualTo(3);
    }

    @Test
    public void testUpdateInboundOrder() {
        PurchaseOrder purchaseOrderFinish = PurchaseOrderUtils.newPurchase1ToSave();
        purchaseOrderFinish.setStatus(Status.FINISHED);
        BDDMockito.when(purchaseOrderService.updateToFinished(purchaseOrderFinish.getId()))
                .thenReturn(purchaseOrderFinish);

        ResponseEntity<PurchaseOrderDto> purchaseOrderResponse = purchaseOrderController.update(purchaseOrderFinish.getId());

        Assertions.assertThat(purchaseOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(purchaseOrderResponse.getBody().getStatus()).isEqualTo(Status.FINISHED);
    }

}

