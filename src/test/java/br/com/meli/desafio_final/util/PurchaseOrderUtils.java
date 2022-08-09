package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.model.enums.Status;

import java.time.LocalDate;

public class PurchaseOrderUtils {

    public static PurchaseOrder newPurchase1ToSave() {
        return PurchaseOrder.builder()
            .id(1L)
            .status(Status.OPEN)
            .date(LocalDate.of(2022, 12, 8))
            .buyer(BuyerUtils.newBuyer1ToSave())
            .itemList(ItemUtils.generatedItemList())
            .build();
    }

}
