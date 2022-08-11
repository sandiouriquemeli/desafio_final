package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.entity.Item;
import br.com.meli.desafio_final.repository.ItemRepository;
import br.com.meli.desafio_final.util.ItemUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    // TODO: REMOVER A PALAVRA "TEST" DOS NOMES DOS MÉTODOS, POIS A MAIORIA NÃO POSSUI
    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testSaveItem() {
        Item item = ItemUtils.newItem1ToSave();
        BDDMockito.when(itemRepository.save(item))
                .thenReturn(item);

        itemService.save(item);

        verify(itemRepository, atLeastOnce()).save(item);
    }

    @Test
    public void testFindItemsByPurchaseOrderId() {
        Item item = ItemUtils.newItem1ToSave();
        BDDMockito.when(itemRepository.findItemsByPurchaseOrderId(item.getId()))
                .thenReturn(ItemUtils.generatedItemList());

        List<Item> itemListResponse = itemService.findItemsByPurchaseOrderId(item.getId());

        Assertions.assertThat(itemListResponse).isNotNull();
    }

    @Test
    public void testIfFindBatchByIdThrowsException() {
        Item item = ItemUtils.newItem1ToSave();
        Exception exceptionResponse = null;

        BDDMockito.when(itemRepository.findItemsByPurchaseOrderId(item.getId()))
                .thenReturn(new ArrayList<>());

        try {
            itemService.findItemsByPurchaseOrderId(item.getId());
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Item não encontrado!");
    }

}
