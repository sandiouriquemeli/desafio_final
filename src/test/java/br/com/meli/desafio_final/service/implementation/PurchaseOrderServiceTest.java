package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.Item;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.ItemUtils;
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

import javax.persistence.MapKeyClass;
import java.util.ArrayList;
import java.util.List;
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

    @Mock
    private ItemService itemService;

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
    void updateToFinished_returnPurchaseOrderUpdated_whenUpdateStatus() {
        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();

        assertThat(purchaseOrder.getStatus()).isEqualTo(Status.OPEN);

        purchaseOrder.setStatus(Status.FINISHED);
        repository.save(purchaseOrder);

        assertThat(purchaseOrder).isNotNull();
        assertThat(purchaseOrder.getStatus()).isEqualTo(Status.FINISHED);
        verify(repository, atLeastOnce()).save(purchaseOrder);
    }

    @Test
    @DisplayName("Busca Anúncios pelo ID da Ordem de Compra: Valida se retorna uma Lista de Anúncios " +
        "quando o ID da Ordem de Compra é válido.")
    void findAdsensesByPurchaseOrderId() {
        BDDMockito.when(itemService.findItemsByPurchaseOrderId(anyLong()))
            .thenReturn(ItemUtils.generatedItemList());

        List<Item> itemList = itemService.findItemsByPurchaseOrderId(1L);

        List<Adsense> adsenseList = new ArrayList<>();
        itemList.forEach(item -> adsenseList.add(item.getAdsense()));

        List<AdsenseDto> adsenseDtoList = AdsenseDto.convertDto(adsenseList);

        assertThat(adsenseDtoList).isNotNull();
        assertThat(adsenseDtoList.size()).isEqualTo(ItemUtils.generatedItemList().size());
    }
}