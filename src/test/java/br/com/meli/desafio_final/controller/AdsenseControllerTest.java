package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.exception.ExNotFound;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.service.AdsenseService;
import br.com.meli.desafio_final.service.IAdsenseService;
import br.com.meli.desafio_final.util.TestAdsenseGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdsenseControllerTest {

    @InjectMocks
    private AdsenseController controller;

    @Mock
    private AdsenseService service;

    @Test
    @DisplayName("Busca pelo ID: Valida se retorna um anúncio completo quando o ID é válido.")
    void findById_returnAdsense_whenIdIsValid() {
        BDDMockito.when(service.findById(anyLong()))
            .thenReturn(TestAdsenseGenerator.getAdsenseWithId());

        Adsense adsense = TestAdsenseGenerator.getAdsenseWithId();

        ResponseEntity<Adsense> response = controller.findById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull().isPositive();
        assertThat(response.getBody().getId()).isEqualTo(adsense.getId());
    }

    @Test
    @DisplayName("Busca pelo ID: Valida se dispara a exceção NOT FOUND quando o ID é inválido.")
    void findById_throwException_whenIdInvalid() {
        BDDMockito.when(service.findById(anyLong()))
            .thenAnswer((invocationOnMock) -> {
                throw new ExNotFound("💢 Anúncio não encontrado!");
            });

        Exception exception = null;
        try {
            controller.findById(0L);
        } catch (ExNotFound ex) {
            exception = ex;
        }

        verify(service, atLeastOnce()).findById(0L);
        assertThat(exception.getMessage()).isEqualTo("💢 Anúncio não encontrado!");
    }

    @Test
    void findAll() {
    }
}