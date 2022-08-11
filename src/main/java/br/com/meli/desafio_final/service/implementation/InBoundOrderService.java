package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.Unauthorized;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.*;
import br.com.meli.desafio_final.service.implementation.BatchService;
import br.com.meli.desafio_final.service.IInBoundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * Nesse metódo estamos salvando um produto
     * @param inBoundOrder
     * @param agentId
     * @return
     */
    private List<InBoundOrderDto> saveOrUpdate(InBoundOrder inBoundOrder, long agentId) {
        List<Batch> batchList = this.validateInboundOrder(inBoundOrder, agentId);
        InBoundOrder newInboundOrder = repository.save(inBoundOrder);

        return batchList.stream().map((batch -> {
            batch.setInBoundOrder(newInboundOrder);
            return new InBoundOrderDto(batchService.saveBatch(batch));
        })).collect(Collectors.toList());
    }

    /**
     * Esse método cria um produto e caso ele já exista retorna uma mensagem
     * @param inBoundOrder
     * @param agentId
     * @return
     */
    @Override
    public List<InBoundOrderDto> create(InBoundOrder inBoundOrder, long agentId) {
        if(inBoundOrder.getId() != null) {
            throw new BadRequest("Pedido de entrada já cadastrado");
        }
        return saveOrUpdate(inBoundOrder, agentId);
    }

    /**
     *
     * @param inBoundOrder
     * @param agentId
     * @return
     */

    @Override
    public List<InBoundOrderDto> update(InBoundOrder inBoundOrder, long agentId) {
         InBoundOrder oldInboundOrder = repository.findById(inBoundOrder.getId())
                .orElseThrow(() -> {
            throw new BadRequest("Pedido de entrada não cadastrado");
        });

        return saveOrUpdate(inBoundOrder, agentId);
    }

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

    /**
     *
     * @param quantity
     * @param volumen
     * @return
     */

    private double batchVolume(int quantity, double volumen){
        return quantity * volumen;
    }

    /**
     * Nesse método estamos validando o representando e o armazém
     * @param inBoundOrder
     * @param agent
     * @return
     */

    private Section validateSection(InBoundOrder inBoundOrder, Agent agent){

        Section section = validationService.validateSection(inBoundOrder.getSection());
        if(agent.getWarehouse().getId().equals(section.getWarehouse().getId())){
            inBoundOrder.setAgent(agent);
        }else{
            throw new Unauthorized("Esse represente não está vinculado a esse armazen");
        }
        return section;
    }

    /**
     * Nesse método estamos validando um produto
     * @param inBoundOrder
     * @param section
     */

    private void validateBatchList(InBoundOrder inBoundOrder, Section section){
        inBoundOrder.getBatchStock().forEach((batch) -> {
            Adsense adsense = adsenseService.findById(batch.getAdsense().getId());
            if (section.getCategory().equals(adsense.getProduct().getCategory())) {
                validationService.validateSeller(adsense.getSeller());
                validationService.validateProduct(adsense.getProduct());
            } else {
                throw new BadRequest("Produto não pertence a esse setor.");
            }
            if(inBoundOrder.getId()==null){
                double batchVolum = batchVolume(batch.getCurrentQuantity(), adsense.getProduct().getVolumen());
                sectionService.setAndUpdateCapacity(batchVolum, section);
            }else{
                Batch oldBatch = batchService.findById(batch.getBatchNumber());
                batch.setInitialQuantity(batch.getInitialQuantity() + oldBatch.getInitialQuantity());
                if(oldBatch.getCurrentQuantity() > batch.getCurrentQuantity()){
                    // aumentar o volume
                    double batchVolumen = batchVolume(oldBatch.getCurrentQuantity() - batch.getCurrentQuantity(),
                            adsense.getProduct().getVolumen());
                    sectionService.setAndUpdateCapacity(-batchVolumen, section);
                }else{
                    double batchVolumen = batchVolume(batch.getCurrentQuantity() - oldBatch.getCurrentQuantity(),
                            adsense.getProduct().getVolumen());
                    sectionService.setAndUpdateCapacity(batchVolumen, section);
                }
            }
        });
        }
}
