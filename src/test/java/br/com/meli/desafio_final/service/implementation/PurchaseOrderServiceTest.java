package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.ItemUtils;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
import org.junit.jupiter.api.Assertions;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.util.BatchUtils;
import br.com.meli.desafio_final.util.BuyerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PurchaseOrderServiceTest {

    @InjectMocks
    private PurchaseOrderService purchaseOrderService;

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private BuyerService buyerService;

    @Mock
    private AdsenseService adsenseService;

    @Mock
    private BatchService batchService;

    @Mock
    private ItemService itemService;

    @BeforeEach
    public void setup() {
        BDDMockito.when(purchaseOrderRepository.save(PurchaseOrderUtils.newPurchase1ToSave()))
                .thenReturn(PurchaseOrderUtils.newPurchase1ToSave());

        BDDMockito.when(purchaseOrderRepository.findById(PurchaseOrderUtils.newPurchase1ToSave().getId()))
                .thenReturn(Optional.ofNullable(PurchaseOrderUtils.newPurchase1ToSave()));

        BDDMockito.when(buyerService.findById(BuyerUtils.newBuyer1ToSave().getId()))
                .thenReturn(BuyerUtils.newBuyer1ToSave());

        BDDMockito.when(adsenseService.findById(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(AdsenseUtils.newAdsense1ToSave());
    }

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna uma Ordem de Compra quando um ID é válido.")
    public void findById_returnPurchaseOrder_whenIdValid() {
        BDDMockito.when(purchaseOrderRepository.findById(anyLong()))
                .thenReturn(Optional.of(PurchaseOrderUtils.newPurchase1ToSave()));

        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchase1ToSave();

        PurchaseOrder purchaseFound = purchaseOrderService.findById(1L);

        assertThat(purchaseFound).isNotNull();
        assertThat(purchaseFound.getId()).isEqualTo(purchaseOrder.getId());
    }

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna uma exceção quando o ID é inválido.")
    public void findById_throwException_whenIdInvalid() {
        Assertions.assertThrows(NotFound.class, () -> purchaseOrderService.findById(0L));
    }

    @Test
    @DisplayName("Atualiza Status: Valida se retorna uma Ordem de Compra com status atualizado e se ela foi salva.")
    public void updateToFinished_returnPurchaseOrderUpdated_whenUpdateStatus() {
        PurchaseOrder purchaseOrder = purchaseOrderService
                .updateToFinished(PurchaseOrderUtils.newPurchase1ToSave().getId());
        assertThat(purchaseOrder).isNotNull();
        assertThat(purchaseOrder.getStatus()).isEqualTo(Status.FINISHED);
        verify(purchaseOrderRepository, atLeastOnce()).save(purchaseOrder);
    }

    @Test
    @DisplayName("Busca Anúncios pelo ID da Ordem de Compra: Valida se retorna uma Lista de Anúncios " +
            "quando o ID da Ordem de Compra é válido.")
    public void findAdsensesByPurchaseOrderId() {
        BDDMockito.when(itemService.findItemsByPurchaseOrderId(anyLong()))
                .thenReturn(ItemUtils.generatedItemList());
        List<AdsenseDto> adsenseDtoList = purchaseOrderService
                .findAdsensesByPurchaseOrderId(AdsenseUtils.newAdsense3ToSave().getId());
        assertThat(adsenseDtoList).isNotNull();
        assertThat(adsenseDtoList.size()).isEqualTo(ItemUtils.generatedItemList().size());
    }

    @Test
    @DisplayName("Salvar carrinho: Retorna valor total passando nas validações")
    public void save_returnTotalPrice_whenIsValid() {
        BDDMockito.when(batchService.findAllByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.generatadBatchListFail());
        Double totralPrice = purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        Assertions.assertEquals(totralPrice, 7.90);
    }

    @Test
    @DisplayName("Salvar carrinho: Valida exception BAD REQUEST se o estoque é insuficiente. ")
    public void save_throwException_whenBatchinsufficient() {
        BDDMockito.when(batchService.findAllByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.generatadBatchListFail());
        try {
            purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        } catch (BadRequest badRequest) {
            assertEquals(badRequest.getMessage(), "Estoque insuficiente!");
        }
    }

    @Test
    @DisplayName("Salvar carrinho: Valida exception BAD REQUEST se o data é menor que 3 semanas. ")
    public void save_throwException_whenDateinsufficient() {
        BDDMockito.when(batchService.findAllByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.genetadBatchListDataFail());
        try {
            purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        } catch (BadRequest badRequest) {
            assertEquals(badRequest.getMessage(), "Data de validade inferior a 3 semanas!");
        }
    }

}