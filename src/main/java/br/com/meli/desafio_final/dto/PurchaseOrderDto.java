package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto {
    private Long id;
    private Status status;

    public PurchaseOrderDto(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.status = purchaseOrder.getStatus();
    }
}
