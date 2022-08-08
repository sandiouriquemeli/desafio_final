package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import br.com.meli.desafio_final.util.TestAdsenseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdsenseServiceTest {

    @InjectMocks
    private AdsenseService service;

    @Mock
    private AdsenseRepository repository;

    @Test
    @DisplayName("Retorna um anúncio quando o ID é válido")
    void findById_returnAdsense_whenIdIsValid() {
        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(TestAdsenseGenerator.getAdsenseWithId()));

        Adsense adsense = TestAdsenseGenerator.getAdsenseWithId();

        Adsense adsenseFound = service.findById(1L);

        assertThat(adsenseFound).isNotNull();
        assertThat(adsenseFound.getId()).isEqualTo(adsense.getId());
    }

    @Test
    void findAll() {
    }
}