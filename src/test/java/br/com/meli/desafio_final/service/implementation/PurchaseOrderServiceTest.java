package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.BatchUtils;
import br.com.meli.desafio_final.util.BuyerUtils;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PurchaseOrderServiceTest {

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
        BDDMockito.when(buyerService.findById(BuyerUtils.newBuyer1ToSave().getId()))
                .thenReturn(BuyerUtils.newBuyer1ToSave());
        BDDMockito.when(adsenseService.findById(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(AdsenseUtils.newAdsense1ToSave());
    }


    @Test
    @DisplayName("Salvar carrinho: Retorna valor total passando nas validações")
    public void save_returnTotalPrice_whenIsValid() {
        BDDMockito.when(batchService.findBatchByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.genetadBatchList());
        Double totralPrice = purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        Assertions.assertEquals(totralPrice, 7.90);
    }

    @Test
    @DisplayName("Salvar carrinho: Valida exception BAD REQUEST se o estoque é insuficiente. ")
    public void save_throwException_whenBatchinsufficient() {
        BDDMockito.when(batchService.findBatchByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.genetadBatchListBatchFail());
        try {
            purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        } catch (BadRequest badRequest) {
            assertEquals(badRequest.getMessage(), "Estoque insuficiente!");
        }
    }

    @Test
    @DisplayName("Salvar carrinho: Valida exception BAD REQUEST se o data é menor que 3 semanas. ")
    public void save_throwException_whenDateinsufficient() {
        BDDMockito.when(batchService.findBatchByAdsenseId(AdsenseUtils.newAdsense1ToSave().getId()))
                .thenReturn(BatchUtils.genetadBatchListDataFail());
        try {
            purchaseOrderService.save(PurchaseOrderUtils.newPurchase1ToSave());
        } catch (BadRequest badRequest) {
            assertEquals(badRequest.getMessage(), "Data de validade inferior a 3 semanas!");
        }
    }

    @Test
    void findById() {
    }

    @Test
    void updateToFinished() {
    }

    @Test
    void findAdsensesByPurchaseOrderId() {
    }
}