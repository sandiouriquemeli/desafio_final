package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.InBoundOrder;
import br.com.meli.desafio_final.repository.InboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InBoundOrderService implements IInBoundOrderService {
    @Autowired
    InboundOrderRepository repository;

    @Override
    public List<InBoundOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public InBoundOrder create(InBoundOrder inBoundOrder) {
        return null;
    }

    @Override
    public InBoundOrder update(InBoundOrder inBoundOrder) {
        return null;
    }
}
