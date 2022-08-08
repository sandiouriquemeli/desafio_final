package br.com.meli.desafio_final.service;

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

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdsenseServiceTest {

    @InjectMocks
    AdsenseService service;

    @Mock
    AdsenseRepository repository;

    @Test
    public void find_findByCategory_whenAdsensesByCategoryExist() {
        BDDMockito.when(service.findAll())
                .thenReturn(AdsenseUtils.generateAdsenseList());

        List<Adsense> adsenseList = service.findByCategory(Category.FRESH);

        Assertions.assertThat(adsenseList).isNotNull();
        Assertions.assertThat(adsenseList.size()).isEqualTo(2);
    }

}
