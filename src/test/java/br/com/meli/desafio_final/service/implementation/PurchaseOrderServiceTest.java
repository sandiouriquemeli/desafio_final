package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PurchaseOrderServiceTest {

    @InjectMocks
    private PurchaseOrderService service;

    @Mock
    private PurchaseOrderRepository repository;

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna uma Ordem de Compra quando um ID é válido.")
    void findById_returnPurchaseOrder_whenIdValid() {
        BDDMockito.when(repository.findById(anyLong()))
            .thenReturn(Optional.of(PurchaseOrderUtils.newPurchase1ToSave()));

        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();

        PurchaseOrder purchaseFound = service.findById(1L);

        assertThat(purchaseFound).isNotNull();
        assertThat(purchaseFound.getId()).isEqualTo(purchaseOrder.getId());
    }

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna uma exceção quando o ID é inválido.")
    void findById_throwException_whenIdInvalid() {
        Assertions.assertThrows(NotFound.class, () -> {
            service.findById(0L);
        });
    }

    @Test
    @DisplayName("Atualiza Status: Valida se retorna uma Ordem de Compra com status atualizado e se ela foi salva.")
    void updateToFinished() {
        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();

        assertThat(purchaseOrder.getStatus()).isEqualTo(Status.OPEN);

        purchaseOrder.setStatus(Status.FINISHED);
        repository.save(purchaseOrder);

        assertThat(purchaseOrder).isNotNull();
        assertThat(purchaseOrder.getStatus()).isEqualTo(Status.FINISHED);
        verify(repository, atLeastOnce()).save(purchaseOrder);
    }

    void findAdsensesByPurchaseOrderId() {

    }
}