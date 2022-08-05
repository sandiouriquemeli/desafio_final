package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.InBoundOrder;

import java.util.List;

public interface IInBoundOrderService {

    List<InBoundOrder> getAll();
    InBoundOrder create(InBoundOrder inBoundOrder);
    InBoundOrder update(InBoundOrder inBoundOrder);
}
