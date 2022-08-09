package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.InBoundOrder;

public class GenerationInboundOrder {

    public static InBoundOrder newInboundOrder(){
        return InBoundOrder.builder()
                .id()
                .section()
                .batchNumber()
                .adsense()
                .currentTemperatura()
                .minimumTemperatura()
                .initialQuantity()
                .currentQuantity()
                .manufacturingDate();

    }
}
