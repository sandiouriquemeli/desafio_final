package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import br.com.meli.desafio_final.repository.BatchRepository;
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
public class BatchServiceTeste {

        @InjectMocks
        private BatchService batchService;

        @Mock
        private BatchRepository batchRepository;

        @Test
        public void testSaveBatch() {
            Batch batch = BatchUtils.newBatch1ToSave();
            BDDMockito.when(batchRepository.save(batch))
                    .thenReturn(batch);

           Batch saveBatchResponse = batchService.saveBatch(batch);

            Assertions.assertThat(saveBatchResponse).isNotNull();
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
}
