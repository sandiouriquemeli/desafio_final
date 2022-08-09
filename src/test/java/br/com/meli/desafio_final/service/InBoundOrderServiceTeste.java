package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.InboundOrderRepository;
import br.com.meli.desafio_final.util.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class InBoundOrderServiceTeste {
    @InjectMocks
    private InBoundOrderService inBoundOrderService;

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private ValidationService validationService;

    @Mock
    private AdsenseService adsenseService;

    @Mock
    private BatchService batchService;

    @Mock
    private SectionService sectionService;

    @Test
    public void createInboundOrder(){
        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(AgentUtils.newAgent());

        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(SectionUtils.newSectionFresh());

        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(AdsenseUtils.newAdsense1ToSave());

        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(AdsenseUtils.newAdsense1ToSave());

        BDDMockito.willDoNothing().given(validationService).validateSeller(new Seller());

        BDDMockito.willDoNothing().given(validationService).validateProduct(new Product());

        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(400D, SectionUtils.newSectionFresh());

        BDDMockito.when(batchService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(BatchUtils.newBatch1ToSave());

        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(InboundOrderUtils.newInboundOrder());

        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(BatchUtils.newBatch1ToSave());

        List<InBoundOrderDto> inbourdOrderCreated= GeneratePerson.newPerson1ToSave();

        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.create(person); // ação

        assertThat(personSaved).isNotNull();
        assertThat(personSaved.getId()).isPositive();
        assertThat(personSaved.getName()).isEqualTo(person.getName());
        verify(personRepository, Mockito.times(1)).save(person);







    }

}
