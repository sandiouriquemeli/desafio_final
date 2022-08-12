package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseBySectionAndDueDateDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdsenseBySectionAndDueDateDtoUtils {

    public static List<Object[]> AdsenseBySectionAndDueDateListObject() {
        List<Object[]> listObj = new ArrayList<>();

        listObj.add(new Object[] {
            new BigInteger("1"), new BigInteger("1"), 200, new Date(2022-12-15) });

        listObj.add(new Object[] {
            new BigInteger("2"), new BigInteger("2"), 200, new Date(2022-11-15) });

        listObj.add(new Object[] {
            new BigInteger("3"), new BigInteger("3"), 200, new Date(2022-10-15) });

        listObj.add(new Object[] {
            new BigInteger("4"), new BigInteger("4"), 200, new Date(2022-9-15) });

        return listObj;
    }

    public static List<AdsenseBySectionAndDueDateDto> AdsenseBySectionAndDueDateDtoList() {
        List<AdsenseBySectionAndDueDateDto> listDto = new ArrayList<>();

        listDto.add(new AdsenseBySectionAndDueDateDto(
            new BigInteger("1"), new BigInteger("1"), 200, new Date(2022-12-15) ));

        listDto.add(new AdsenseBySectionAndDueDateDto(
            new BigInteger("2"), new BigInteger("2"), 200, new Date(2022-11-15) ));

        listDto.add(new AdsenseBySectionAndDueDateDto(
            new BigInteger("3"), new BigInteger("3"), 200, new Date(2022-10-15) ));

        listDto.add(new AdsenseBySectionAndDueDateDto(
            new BigInteger("4"), new BigInteger("4"), 200, new Date(2022-9-15) ));

        return listDto;
    }


}
