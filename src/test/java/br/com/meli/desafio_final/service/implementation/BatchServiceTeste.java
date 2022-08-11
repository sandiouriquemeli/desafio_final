package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.model.entity.Batch;

import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.util.AdsenseByWarehouseDtoUtils;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.BatchUtils;
import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
// TODO: RENOMEAR ARQUIVO E CLASSE (ÚLTIMA PALAVRA), PARA MANTER O PADRÃO - REMOVER O "E" DA PALAVRA "TEST"
public class BatchServiceTeste {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    // TODO: REMOVER A PALAVRA "TEST" DOS NOMES DOS MÉTODOS, POIS A MAIORIA NÃO POSSUI
    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testSaveBatch() {
            Batch batch = BatchUtils.newBatch1ToSave();
            BDDMockito.when(batchRepository.save(batch))
                    .thenReturn(batch);

           Batch saveBatchResponse = batchService.saveBatch(batch);

            Assertions.assertThat(saveBatchResponse).isNotNull();
        }

    @Test
    public void testFindBatchByAdsenseId() {
        Batch batch = BatchUtils.newBatch1ToSave();
        BDDMockito.when(batchRepository.findBatchesByAdsenseId(batch.getBatchNumber()))
                .thenReturn(BatchUtils.BatchList());

        List<Batch> batchListByAdsenseId = batchService.findBatchByAdsenseId(batch.getBatchNumber());

        Assertions.assertThat(batchListByAdsenseId).isNotNull();
        Assertions.assertThat(batchListByAdsenseId.size()).isEqualTo(1);
    }

    @Test
    public void testIfFindBatchByAdsenseIdThrowsException() {
        Batch batch = BatchUtils.newBatch1ToSave();
        Exception exceptionResponse = null;

        BDDMockito.when(batchRepository.findBatchesByAdsenseId(batch.getBatchNumber()))
                .thenReturn(BatchUtils.BatchListEmpty());
        try {
            batchService.findBatchByAdsenseId(batch.getBatchNumber());
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Lote do anúncio não encontrado!");
    }

    @Test
    public void testFindBatchById() {
        Batch batch = BatchUtils.newBatch1ToSave();
        BDDMockito.when(batchRepository.findById(batch.getBatchNumber()))
                .thenReturn(Optional.of(batch));

        Batch saveBatchResponse = batchService.findById(batch.getBatchNumber());

        Assertions.assertThat(saveBatchResponse).isNotNull();
    }

    @Test
    public void testIfFindBatchByIdThrowsException() {
        Batch batch = BatchUtils.newBatch1ToSave();
        Exception exceptionResponse = null;

        BDDMockito.when(batchRepository.findById(batch.getBatchNumber()))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            batchService.findById(batch.getBatchNumber());
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Lote não encontrado");
    }

    @Test
    public void testAdsenseByWarehouseAndQuantity() {
        long adsenseId = AdsenseUtils.newAdsense1ToSave().getId();
        BDDMockito.when(batchRepository.getAdsenseByWarehouse(adsenseId))
                .thenReturn(AdsenseByWarehouseDtoUtils.AdsenseByWarehouseDtoList());

        List<AdsenseByWarehouseDto> saveBatchResponse = batchService.getAdsenseByWarehouseAndQuantity(adsenseId);

        Assertions.assertThat(saveBatchResponse).isNotNull();
    }

    @Test
    public void testFindAdsenseBySectionAndDueDate() {

    }
}
