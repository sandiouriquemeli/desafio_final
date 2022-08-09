package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.InBoundOrder;

import java.time.LocalDate;

public class InboundOrderUtils {
    public static InBoundOrder newInboundOrder() {
        return InBoundOrder.builder()
                .id(1L)
                .section(SectionUtils.newSectionFresh())
                .date(LocalDate.now())
                .agent(AgentUtils.newAgent())
                .batchStock(BatchUtils.BatchList())
                .build();
    }

}
