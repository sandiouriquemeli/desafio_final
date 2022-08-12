package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.implementation.AdsenseService;
import br.com.meli.desafio_final.util.AdsenseByWarehouseDtoUtils;
import br.com.meli.desafio_final.util.AdsenseUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import br.com.meli.desafio_final.exception.ExNotFound;
import br.com.meli.desafio_final.util.TestAdsenseGenerator;
import org.junit.jupiter.api.DisplayName;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdsenseControllerTest {

    // TODO: PADRONIZAR NOME DOS MOCKS
    @InjectMocks
    private AdsenseController controller;

    @Mock
    private AdsenseService service;

    // TODO: REMOVER A PALAVRA "TEST" DOS NOMES DOS MÉTODOS, POIS A MAIORIA NÃO POSSUI
    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI
    // TODO: ADICIONAR O public AOS MÉTODOS

    @Test
    public void find_findByCategory_whenAdsensesByCategoryExist() {
        List<Adsense> adsenseList = AdsenseUtils.generateAdsenseList().subList(0, 2);
        BDDMockito.when(service.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(adsenseList);

        ResponseEntity<List<Adsense>> response = controller.findByCategory(Category.FRESH);

        verify(service, atLeastOnce()).findByCategory(Category.FRESH);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: AJUSTAR O IMPORT
        org.junit.jupiter.api.Assertions.assertEquals(response.getBody().size(), 2);
        org.junit.jupiter.api.Assertions.assertEquals(response.getBody(), adsenseList);
    }

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
                // TODO: ALTERAR EXCEPTION PARA A PADRÃO NotFound
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
    @DisplayName("Listar anúncios: Valida se retorna uma lista de anúncios.")
    void findAll_returnListAdsense_whenAdsensesExists() {
        BDDMockito.when(service.findAll())
            .thenReturn(List.of(TestAdsenseGenerator.getAdsenseWithId()));

        Adsense adsense = TestAdsenseGenerator.getAdsenseWithId();

        ResponseEntity<List<Adsense>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isNotNull().isPositive().isEqualTo(1);
        assertThat(response.getBody().get(0).getId()).isEqualTo(adsense.getId());
    }

    @Test
    @DisplayName("Listar anúncios: Valida se dispara a execeção NOT FOUND quando não há anúncios cadastrados.")
    void findAll_throwException_whenAdsensesNotExists() {
        BDDMockito.when(service.findAll())
            .thenAnswer((invocationOnMock) -> {
                // TODO: ALTERAR A EXCEPTION PARA A PADRÃO NotFound
                throw new ExNotFound("💢 Lista de anúncios não encontrada");
            });

        Exception exception = null;
        try {
            controller.findAll();
        } catch (ExNotFound ex) {
            exception = ex;
        }

        verify(service, atLeastOnce()).findAll();
        assertThat(exception.getMessage()).isEqualTo("💢 Lista de anúncios não encontrada");
    }

    @Test
    void testGetByAdsenseByWarehouse() {
        long adsenseId = AdsenseUtils.newAdsense1ToSave().getId();
        BDDMockito.when(service.findAdsenseByWarehouseAndQuantity(adsenseId))
                .thenReturn(AdsenseByWarehouseDtoUtils.AdsenseByWarehouseDtoListDto());

        ResponseEntity <List<AdsenseByWarehouseDto>> response = controller.getByAdsenseByWarehouse(adsenseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isNotNull().isPositive().isEqualTo(4);
    }

}

