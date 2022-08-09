package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
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
    @DisplayName("Busca pelo ID: Valida se retorna uma Ordem de Compra quando um ID é válido")
    void findById_returnPurchaseOrder_whenIdValid() {
        BDDMockito.when(repository.findById(anyLong()))
            .thenReturn(Optional.of(PurchaseOrderUtils.newPurchase1ToSave()));

        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();

        PurchaseOrder purchaseFound = service.findById(1L);

        assertThat(purchaseFound).isNotNull();
        assertThat(purchaseFound.getId()).isEqualTo(purchaseOrder.getId());
    }
}