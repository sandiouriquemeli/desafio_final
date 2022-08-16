package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseBySectionAndDueDateDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
class AdsenseBySectionAndDueDateDtoFake implements AdsenseBySectionAndDueDateDto {

    private Long batch_number;
    private Long adsense_id;
    private  Integer quantity;
    private Date due_date;

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
}

public class AdsenseBySectionAndDueDateDtoUtils {

    public static List<AdsenseBySectionAndDueDateDto> AdsenseBySectionAndDueDateListObject() {
        List<AdsenseBySectionAndDueDateDto> listObj = new ArrayList<>();

        listObj.add(new AdsenseBySectionAndDueDateDtoFake(1L, 1L, 200, new Date(2022-12-15)));
        listObj.add(new AdsenseBySectionAndDueDateDtoFake(2L, 2L, 200, new Date(2022-11-15)));
        listObj.add(new AdsenseBySectionAndDueDateDtoFake(3L, 3L, 200, new Date(2022-10-15)));
        listObj.add(new AdsenseBySectionAndDueDateDtoFake(4L, 4L, 200, new Date(2022-9-15)));
        return listObj;
    }
}
