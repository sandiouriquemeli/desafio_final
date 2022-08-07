package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.*;
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

    @Override
    public List<InBoundOrder> getAll() {
        return repository.findAll();
    }

    //TODO: Fazer exceptions para Seller, Section e Product notFound
    // TODO: lembrar de criar um service pra cada ou um service validations

    /**
     * Metodo valida o InboundOrder e retorna a lista de Baths já validada.
     * @param inBoundOrder
     * @return BatchList
     */
    private List<Batch> validateInboundOrder(InBoundOrder inBoundOrder) {
        validationService.validateSection(inBoundOrder.getSection());
        List<Batch>batchList = inBoundOrder.getBatchStock();
        batchList.forEach((batch) -> {
            Adsense adsense = adsenseService.findById(batch.getAdsense().getId());
            validationService.validateSeller(adsense.getSeller());
            validationService.validateProduct(adsense.getProduct());
        });
        return batchList;
    }

    private List<InBoundOrderDto> saveOrUpdate(InBoundOrder inBoundOrder) {

        List<Batch> batchList = this.validateInboundOrder(inBoundOrder);

        InBoundOrder newInboundOrder = repository.save(inBoundOrder);

        return batchList.stream().map((batch -> {
            batch.setInBoundOrder(newInboundOrder);
            return new InBoundOrderDto(batchService.saveBatch(batch));
        })).collect(Collectors.toList());
    }

    @Override
    public List<InBoundOrderDto> create(InBoundOrder inBoundOrder) {
       if(inBoundOrder.getId() != null) {
               throw new RuntimeException("InboundOrder já cadastrada");
       }

      return saveOrUpdate(inBoundOrder);
    }

    @Override
    public List<InBoundOrderDto> update(InBoundOrder inBoundOrder) {
        repository.findById(inBoundOrder.getId()).orElseThrow(() -> {
            throw new RuntimeException("InboundOrder não encontrada");
        });
        return saveOrUpdate(inBoundOrder);
    }

    // TODO: fazer exception pra update e create
    // TODO: Registrar o representante junto com o inboundOrder
    // TODO: validar que o setor corresponde ao tipo de produto
    // TODO: validar que o setor tem espaço disponível
}
