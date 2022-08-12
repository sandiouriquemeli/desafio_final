package br.com.meli.desafio_final.service.implementation;

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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
// TODO: RENOMEAR ARQUIVO E CLASSE (ÚLTIMA PALAVRA), PARA MANTER O PADRÃO - REMOVER O "E" DA PALAVRA "TEST"
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

    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI
    // TODO: MELHORAR IDENTAÇÃO DENTRO DOS MÉTODOS, SEPARANDO COM ESPAÇOS ENTRE LINHAS OS CONJUNTOS DE LÓGICA

    @Test
    public void createInboundOrder(){
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();

        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);

        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);

        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);

        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());

        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());

        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(1500D, sectionFresh);

        // TODO: REMOVER COMENTÁRIO

        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);

        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);

        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.create(InboundOrderUtils.inBoundOrderToCreated(), 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }

    @Test
    public void updateInboundOrderWhenQuantityIsLess(){
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();
        InBoundOrder inBoundOrderToUpdate = InboundOrderUtils.inBoundOrderToCreated();
        inBoundOrderToUpdate.setId(1L);
        inBoundOrderToUpdate.getBatchStock().get(0).setCurrentQuantity(1);
        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(inBoundOrderToUpdate));

        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);

        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);

        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);

        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());

        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());

        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(-1485D, sectionFresh);

        BDDMockito.when(batchService.findByBatchNumberAndInboundOrderId(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
              .thenReturn(batch);

        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);

        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);

        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.update(inBoundOrderToUpdate, 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }

    @Test
    public void updateInboundOrderWhenQuantityisBigger(){
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();
        InBoundOrder inBoundOrderToUpdate = InboundOrderUtils.inBoundOrderToCreated();
        inBoundOrderToUpdate.setId(1L);
        inBoundOrderToUpdate.getBatchStock().get(0).setCurrentQuantity(110);
        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(inBoundOrderToUpdate));

        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);

        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);

        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);

        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());

        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());

        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(150D, sectionFresh);

        BDDMockito.when(batchService.findByBatchNumberAndInboundOrderId(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .thenReturn(batch);

        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);

        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);

        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.update(inBoundOrderToUpdate, 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }
}
