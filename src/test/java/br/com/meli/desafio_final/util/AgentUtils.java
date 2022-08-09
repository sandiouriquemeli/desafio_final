package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Agent;

public class AgentUtils {
    public static Agent newAgent() {
        Agent agent = new Agent();
        agent.setId(1L);
        agent.setName("Raul");
        agent.setWarehouse(WarehouseUtils.newWarehouse());
        return agent;
    }
}
