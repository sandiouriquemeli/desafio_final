package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseByDueDateAndCategoryDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
class AdsenseByDueDateAndCategoryDtoFake implements AdsenseByDueDateAndCategoryDto {
    private Long batch_number;
    private Long adsense_id;
    private Integer quantity;
    private Date due_date;
    private String category;
    private Long section_id;

    @Override
    public Long getBatch_number() {
        return null;
    }

    @Override
    public Long getAdsense_id() {
        return null;
    }

    @Override
    public Integer getQuantity() {
        return null;
    }

    @Override
    public Date getDue_date() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public Long getSection_id() {
        return null;
    }
}

public class AdsenseByDueDateAndCategoryDtoUtils {

    // RETORNO DO REPOSITORY - DESC
    public static List<AdsenseByDueDateAndCategoryDto> AdsensByDueDateAndCategoryListObjectDesc() {
        List<AdsenseByDueDateAndCategoryDto> listObj = new ArrayList<>();

        listObj.add(new AdsenseByDueDateAndCategoryDtoFake(1L,1L, 100, new Date(2022-1-1), "FROZEN", 3L));
        listObj.add(new AdsenseByDueDateAndCategoryDtoFake(2L,2L, 50, new Date(2022-2-1), "FROZEN", 3L));

        return listObj;
    }

    // RETORNO DO REPOSITORY - ASC
    public static List<AdsenseByDueDateAndCategoryDto> AdsensByDueDateAndCategoryListObjectAsc() {
        List<AdsenseByDueDateAndCategoryDto> listObj = new ArrayList<>();
        listObj.add(new AdsenseByDueDateAndCategoryDtoFake(2L,2L, 50, new Date(2022-2-1), "FROZEN", 3L));
        listObj.add(new AdsenseByDueDateAndCategoryDtoFake(1L,1L, 100, new Date(2022-1-1), "FROZEN", 3L));

        return listObj;
    }

}
