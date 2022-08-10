package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.InBoundOrder;

import java.util.List;

public interface IInBoundOrderService {

    List<InBoundOrderDto> create(InBoundOrder inBoundOrder, long agentId);
    List<InBoundOrderDto> update(InBoundOrder inBoundOrder, long agentId);
}
