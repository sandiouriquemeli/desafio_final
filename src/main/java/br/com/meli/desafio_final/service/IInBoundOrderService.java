package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.InBoundOrder;

import java.util.List;

public interface IInBoundOrderService {

    List<InBoundOrder> getAll();
    List<InBoundOrderDto> create(InBoundOrder inBoundOrder);
    List<InBoundOrderDto> update(InBoundOrder inBoundOrder);
}
