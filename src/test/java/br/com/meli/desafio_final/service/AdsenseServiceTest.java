package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.CategoryNotFoundException;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import br.com.meli.desafio_final.util.AdsenseUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdsenseServiceTest {

    @InjectMocks
    private AdsenseService service;

    @Mock
    private AdsenseRepository repository;

    @Test
    public void find_findByCategory_whenAdsensesByCategoryExist() {
        BDDMockito.when(repository.findAll())
                .thenReturn(AdsenseUtils.generateAdsenseList());

        List<Adsense> adsenseList = service.findByCategory(Category.FRESH);

        Assertions.assertThat(adsenseList).isNotNull();
        Assertions.assertThat(adsenseList.size()).isEqualTo(2);
    }

    @Test
    public void find_findByCategory_whenAdsensesByCategoryDontExist() {
        BDDMockito.when(repository.findAll()).thenReturn(Collections.emptyList());
        Exception exception = null;
        List<Adsense> adsenseList = null;
        try {
            adsenseList = service.findByCategory(Category.FRESH);
        } catch (CategoryNotFoundException e) {
            exception = e;
        }
        verify(repository, atLeastOnce()).findAll();
        Assertions.assertThat(adsenseList).isNull();
        assertThat(exception.getMessage()).isEqualTo("Nenhum produto com essa categoria foi encontrado");

    }
}
