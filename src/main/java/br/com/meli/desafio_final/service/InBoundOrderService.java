package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InBoundOrderService implements IInBoundOrderService {

    @Autowired
    InboundOrderRepository repository;

    @Autowired
    ValidationService validationService;

    @Autowired
    AdsenseService adsenseService;

    @Autowired
    BatchService batchService;

    @Autowired
    SectionService sectionService;

    @Override
    public List<InBoundOrder> getAll() {
        return repository.findAll();
    }

    private List<InBoundOrderDto> saveOrUpdate(InBoundOrder inBoundOrder, long agentId) {
        List<Batch> batchList = this.validateInboundOrder(inBoundOrder, agentId);
        InBoundOrder newInboundOrder = repository.save(inBoundOrder);

        return batchList.stream().map((batch -> {
            batch.setInBoundOrder(newInboundOrder);
            return new InBoundOrderDto(batchService.saveBatch(batch));
        })).collect(Collectors.toList());
    }

    @Override
    public List<InBoundOrderDto> create(InBoundOrder inBoundOrder, long agentId) {
        if(inBoundOrder.getId() != null) {
            throw new RuntimeException("InboundOrder já cadastrada");
        }
        return saveOrUpdate(inBoundOrder, agentId);
    }

    @Override
    public List<InBoundOrderDto> update(InBoundOrder inBoundOrder, long agentId) {
         InBoundOrder oldInboundOrder = repository.findById(inBoundOrder.getId())
                .orElseThrow(() -> {
            throw new RuntimeException("InboundOrder não encontrada");
        });

        return saveOrUpdate(inBoundOrder, agentId);
    }

    //TODO: Fazer exceptions para Seller, Section e Product notFound
    // TODO: lembrar de criar um service pra cada ou um service validations

    /**
     * Metodo valida o InboundOrder e retorna a lista de Baths já validada.
     * @param inBoundOrder
     * @return BatchList
     */
    private List<Batch> validateInboundOrder(InBoundOrder inBoundOrder, long agentId) {
        Agent agent = validationService.validateAgent(agentId);
        Section section = validateSection(inBoundOrder, agent);
        //List<Batch>batchList = inBoundOrder.getBatchStock();
        validateBatchList(inBoundOrder, section);
        return inBoundOrder.getBatchStock();
    }

    private double bacthVolumen(int quantity, double volumen){
        return quantity * volumen;
    }

    private Section validateSection(InBoundOrder inBoundOrder, Agent agent){

        Section section = validationService.validateSection(inBoundOrder.getSection());
        if(agent.getWarehouse().getId().equals(section.getWarehouse().getId())){
            inBoundOrder.setAgent(agent);
        }else{
            throw new RuntimeException("Esse represente não está vinculado a esse armazen");
        }
        return section;
    }

    private void validateBatchList(InBoundOrder inBoundOrder, Section section){
        inBoundOrder.getBatchStock().forEach((batch) -> {
            Adsense adsense = adsenseService.findById(batch.getAdsense().getId());
            if (section.getCategory().equals(adsense.getProduct().getCategory())) {
                validationService.validateSeller(adsense.getSeller());
                validationService.validateProduct(adsense.getProduct());
            } else {
                throw new RuntimeException("Produto não pertence a esse setor.");
            }
            if(inBoundOrder.getId()==null){
                double batchVolum = bacthVolumen(batch.getCurrentQuantity(), adsense.getProduct().getVolumen());
                sectionService.setAndUpdateCapacity(batchVolum, section);
            }

        });
        }
    // TODO: fazer exception pra update e create
}
