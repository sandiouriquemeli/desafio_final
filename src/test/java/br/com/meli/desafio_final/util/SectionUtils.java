package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;

import static br.com.meli.desafio_final.util.WarehouseUtils.newWarehouse;

public class SectionUtils {
    public static Section newSectionFresh() {
        Section section = new Section();
        section.setId(1L);
        section.setTotalCapacity(5000D);
        section.setUsedCapacity(5000D);
        section.setWarehouse(newWarehouse());
        section.setCategory(Category.FRESH);
        return section;
    }

    public static Section newSectionRefrigerated() {
        Section section = new Section();
        section.setId(2L);
        section.setTotalCapacity(5000D);
        section.setUsedCapacity(5000D);
        section.setWarehouse(newWarehouse());
        section.setCategory(Category.REFRIGERATED);
        return section;
    }

    public static Section newSectionFrozen() {
        Section section = new Section();
        section.setId(3L);
        section.setTotalCapacity(5000D);
        section.setUsedCapacity(5000D);
        section.setWarehouse(newWarehouse());
        section.setCategory(Category.FROZEN);
        return section;
    }
}