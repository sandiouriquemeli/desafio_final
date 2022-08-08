package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.ExNotFound;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import br.com.meli.desafio_final.util.TestAdsenseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdsenseServiceTest {

    @InjectMocks
    private AdsenseService service;

    @Mock
    private AdsenseRepository repository;

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna um anúncio completo quando o ID é válido.")
    void findById_returnAdsense_whenIdIsValid() {
        BDDMockito.when(repository.findById(anyLong()))
            .thenReturn(Optional.of(TestAdsenseGenerator.getAdsenseWithId()));

        Adsense adsense = TestAdsenseGenerator.getAdsenseWithId();

        Adsense adsenseFound = service.findById(1L);

        assertThat(adsenseFound).isNotNull();
        assertThat(adsenseFound.getId()).isEqualTo(adsense.getId());
    }

    @Test
    @DisplayName("Busca pelo ID: Valida se dispara a exceção NOT FOUND quando o ID é inválido.")
    void findById_throwException_whenIdInvalid() {
        assertThrows(ExNotFound.class, () -> {
           service.findById(0L);
        });
    }

    @Test
    @DisplayName("Listar anúncios: Valida se retorna uma lista de anúncios.")
    void findAll_returnListAdsense_whenAdsensesExists() {
        BDDMockito.when(repository.findAll())
            .thenReturn(List.of(TestAdsenseGenerator.getAdsenseWithId()));

        List<Adsense> adsenseList = service.findAll();

        assertThat(adsenseList).isNotNull();
        assertThat(adsenseList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Listar anúncios: Valida se dispara a execeção NOT FOUND quando não há anúncios cadastrados.")
    void findAll_throwException_whenAdsensesNotExists() {

        assertThrows(ExNotFound.class, () -> {
            service.findAll();
        });
    }
}