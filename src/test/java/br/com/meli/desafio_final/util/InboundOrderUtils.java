package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Agent;
import br.com.meli.desafio_final.model.entity.InBoundOrder;
import br.com.meli.desafio_final.model.entity.Section;

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

    public static InBoundOrder inBoundOrderToCreated(){
        Section section = new Section();
        section.setId(1L);
        return InBoundOrder.builder()
                .section(section)
                .date(LocalDate.now())
                .agent(new Agent())
                .batchStock(BatchUtils.BatchList())
                .build();
    }
}
