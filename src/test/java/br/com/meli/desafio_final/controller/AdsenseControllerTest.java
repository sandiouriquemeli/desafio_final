package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.exception.CategoryNotFoundException;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.AdsenseService;
import br.com.meli.desafio_final.util.AdsenseUtils;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdsenseControllerTest {

    @InjectMocks
    private AdsenseController controller;

    @Mock
    private AdsenseService service;

    @Test
    public void find_findByCategory_whenAdsensesByCategoryExist() {
        List<Adsense> adsenseList = AdsenseUtils.generateAdsenseList().subList(0, 2);
        BDDMockito.when(service.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(adsenseList);

        ResponseEntity<List<Adsense>> response = controller.findByCategory(Category.FRESH);

        verify(service, atLeastOnce()).findByCategory(Category.FRESH);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        org.junit.jupiter.api.Assertions.assertEquals(response.getBody().size(), 2);
        org.junit.jupiter.api.Assertions.assertEquals(response.getBody(), adsenseList);
    }

    @Test
    public void find_findByCategory_whenAdsensesByCategoryDontExist() {
        BDDMockito.when(service.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    throw new CategoryNotFoundException("Nenhum produto com essa categoria foi encontrado");
                });

        Exception exception = null;
        ResponseEntity<List<Adsense>> adsenseList = null;
        try {
            adsenseList = controller.findByCategory(Category.FRESH);
        } catch (CategoryNotFoundException e) {
            exception = e;
        }

        verify(service, atLeastOnce()).findByCategory(Category.FRESH);
        org.junit.jupiter.api.Assertions.assertNull(adsenseList);
        assertThat(exception.getMessage()).isEqualTo("Nenhum produto com essa categoria foi encontrado");
    }
}
