package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.implementation.AdsenseService;
import br.com.meli.desafio_final.util.AdsenseByWarehouseDtoUtils;
import br.com.meli.desafio_final.util.AdsenseUtils;
import br.com.meli.desafio_final.util.AdsenseUtilsDto;
import org.assertj.core.api.Assertions;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<AdsenseDto> adsenseList = AdsenseUtilsDto.generateAdsenseDtoList();
        BDDMockito.when(service.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(AdsenseUtils.generateAdsenseList());

        ResponseEntity<List<AdsenseDto>> response = controller.findByCategory(Category.FRESH);

        verify(service, atLeastOnce()).findByCategory(Category.FRESH);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: AJUSTAR O IMPORT
        assertEquals(response.getBody().size(), 3);
        assertEquals(response.getBody().get(0).getPrice(), adsenseList.get(0).getPrice());
        assertEquals(response.getBody().get(0).getProduct().getId(), adsenseList.get(0).getProduct().getId());
        assertEquals(response.getBody().get(0).getSeller().getId(), adsenseList.get(0).getSeller().getId());
    }

    @Test
    @DisplayName("Listar anúncios: Valida se retorna uma lista de anúncios.")
    public void findAll_returnListAdsense_whenAdsensesExists() {
        BDDMockito.when(service.findAll())
            .thenReturn(AdsenseUtils.generateAdsenseList());

        AdsenseDto adsenseDto = AdsenseUtilsDto.newAdsense1ToSave();

        ResponseEntity<List<AdsenseDto>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isPositive().isEqualTo(3);
        assertEquals(response.getBody().get(0).getPrice(), adsenseDto.getPrice());
        assertEquals(response.getBody().get(0).getProduct().getId(), adsenseDto.getProduct().getId());
        assertEquals(response.getBody().get(0).getSeller().getId(), adsenseDto.getSeller().getId());
    }

    @Test
    @DisplayName("Listar anúncios: Valida se dispara a execeção NOT FOUND quando não há anúncios cadastrados.")
    public void findAll_throwException_whenAdsensesNotExists() {
        BDDMockito.when(service.findAll())
            .thenAnswer((invocationOnMock) -> {
                throw new NotFound("💢 Lista de anúncios não encontrada");
            });

        Exception exception = null;
        try {
            controller.findAll();
        } catch (NotFound ex) {
            exception = ex;
        }

        verify(service, atLeastOnce()).findAll();
        assertThat(exception.getMessage()).isEqualTo("💢 Lista de anúncios não encontrada");
    }

    @Test
    public void testGetByAdsenseByWarehouse() {
        long adsenseId = AdsenseUtils.newAdsense1ToSave().getId();
        BDDMockito.when(service.findAdsenseByWarehouseAndQuantity(adsenseId))
                .thenReturn(AdsenseByWarehouseDtoUtils.AdsenseByWarehouseDtoListDto());

        ResponseEntity <List<AdsenseByWarehouseDto>> response = controller.getByAdsenseByWarehouse(adsenseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isNotNull().isPositive().isEqualTo(4);
    }

}

