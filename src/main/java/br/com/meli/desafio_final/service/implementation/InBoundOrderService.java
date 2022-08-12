package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.Unauthorized;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.*;
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
        if(inBoundOrder.getId() == null) throw new BadRequest("O 'id' do inboundOrder precisa ser informado.");

        repository.findById(inBoundOrder.getId())
                .orElseThrow(() -> {
            throw new BadRequest("Pedido de entrada não cadastrado");
        });

        return saveOrUpdate(inBoundOrder, agentId);
    }

    /**
     * Nesse metódo estamos salvando um produto
     * @param inBoundOrder
     * @param agentId
     * @return
     */
    private List<InBoundOrderDto> saveOrUpdate(InBoundOrder inBoundOrder, long agentId) {
        List<Batch> batchList = validateInboundOrder(inBoundOrder, agentId);
        InBoundOrder newInboundOrder = repository.save(inBoundOrder);

        return batchList.stream().map((batch -> {
            batch.setInBoundOrder(newInboundOrder);
            return new InBoundOrderDto(batchService.saveBatch(batch));
        })).collect(Collectors.toList());
    }

    /**
     * Metodo valida o InboundOrder e retorna a lista de Baths já validada.
     * @param inBoundOrder
     * @return BatchList
     */
    private List<Batch> validateInboundOrder(InBoundOrder inBoundOrder, long agentId) {
        Agent agent = validationService.validateAgent(agentId);
        Section section = validateSection(inBoundOrder, agent);
        validateBatchList(inBoundOrder, section);
        return inBoundOrder.getBatchStock();
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
            validateProductBelongsToASector(section, adsense);
            if(inBoundOrder.getId()==null){
                validateAndSetCapacityInSector(batch, adsense, section);
            }else{
                validateAndUpdateCapacityInASector(batch, inBoundOrder, adsense, section);
            }
        });
    }

    /**
     * Esse método valida se um produto está sendo cadastrado no setor correto.
     * @param section
     * @param adsense
     */
    private void validateProductBelongsToASector(Section section, Adsense adsense) {
        if (section.getCategory().equals(adsense.getProduct().getCategory())) {
            validationService.validateSeller(adsense.getSeller());
            validationService.validateProduct(adsense.getProduct());
        } else {
            throw new BadRequest("Produto não pertence a esse setor.");
        }
    }

    /**
     * Esse método valida se um setor tem a capacidade para armazenar o volume de um novo lote e caso positivo, altera.
     * @param batch
     * @param adsense
     * @param section
     */
    private void validateAndSetCapacityInSector(Batch batch, Adsense adsense, Section section){
        batchService.findBatchByBatchNumberAndAdsenseId(batch.getBatchNumber(), batch.getAdsense().getId());
        double batchVolum = batchVolume(batch.getCurrentQuantity(), adsense.getProduct().getVolumen());
        sectionService.setAndUpdateCapacity(batchVolum, section);
    }

    /**
     * Esse método faz um update do volume de um setor, se este tiver a capacidade quando um lote é editado.
     * @param batch
     * @param inBoundOrder
     * @param adsense
     * @param section
     */
    private void validateAndUpdateCapacityInASector(Batch batch, InBoundOrder inBoundOrder, Adsense adsense, Section section) {
        Batch oldBatch = batchService.findByBatchNumberAndInboundOrderId(batch.getBatchNumber(), inBoundOrder.getId());
        batch.setId(oldBatch.getId());
        batch.setInitialQuantity(batch.getInitialQuantity() + oldBatch.getInitialQuantity());
        if(oldBatch.getCurrentQuantity() > batch.getCurrentQuantity()){
            double batchVolumen = batchVolume(oldBatch.getCurrentQuantity() - batch.getCurrentQuantity(),
                    adsense.getProduct().getVolumen());
            sectionService.setAndUpdateCapacity(-batchVolumen, section);
        }else{
           double batchVolumen = batchVolume(batch.getCurrentQuantity() - oldBatch.getCurrentQuantity(),
                   adsense.getProduct().getVolumen());
           sectionService.setAndUpdateCapacity(batchVolumen, section);
        }
    }

    /**
     * Esse método calcula o volume de um lote.
     * @param quantity
     * @param volumen
     * @return
     */
    private double batchVolume(int quantity, double volumen){
        return quantity * volumen;
    }
}
