package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseByDueDateAndCategoryDto;
import br.com.meli.desafio_final.dto.AdsenseBySectionAndDueDateDto;
import br.com.meli.desafio_final.service.implementation.BatchService;
import br.com.meli.desafio_final.util.AdsenseByDueDateAndCategoryDtoUtils;
import br.com.meli.desafio_final.util.AdsenseBySectionAndDueDateDtoUtils;
import br.com.meli.desafio_final.util.SectionUtils;
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

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchControllerTest {

    @InjectMocks
    private BatchController batchController;

    @Mock
    private BatchService batchService;

    @Test
    void test_findAdsenseBySectionAndDueDate() {
        long sectionId = SectionUtils.newSectionFresh().getId();
        int numberOfDays = 20;

        BDDMockito.when(batchService.findAdsenseBySectionAndDueDate(sectionId, numberOfDays))
                .thenReturn(AdsenseBySectionAndDueDateDtoUtils.AdsenseBySectionAndDueDateListObject());

        ResponseEntity<List<AdsenseBySectionAndDueDateDto>> response = batchController.findAdsenseBySectionAndDueDate(sectionId, numberOfDays);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isNotNull().isPositive().isEqualTo(4);
    }

    @Test
    void test_findAdsenseByDueDateAndCategory() {
        int numberOfDays = 20;
        String category = "FROZEN";
        String order = "asc";

        BDDMockito.when(batchService.findAdsenseByDueDateAndCategory(numberOfDays, category, order))
                .thenReturn(AdsenseByDueDateAndCategoryDtoUtils.AdsensByDueDateAndCategoryListObjectAsc());

        ResponseEntity<List<AdsenseByDueDateAndCategoryDto>> response = batchController.findAdsenseByDueDateAndCategory(numberOfDays, category, order);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isNotNull().isPositive().isEqualTo(2);
    }
}