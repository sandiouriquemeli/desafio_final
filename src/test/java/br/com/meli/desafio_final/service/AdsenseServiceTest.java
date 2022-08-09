package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.CategoryNotFoundException;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import br.com.meli.desafio_final.util.AdsenseUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import java.util.Optional;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

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
