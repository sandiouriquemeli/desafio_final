package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsensByDueDateAndCategoryDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdsenseByDueDateAndCategoryDtoUtils {

    // RETORNO DO REPOSITORY - DESC
    public static List<Object[]> AdsensByDueDateAndCategoryListObjectDesc() {
        List<Object[]> listObj = new ArrayList<>();

        listObj.add(new Object[] {
            new BigInteger("1"), new BigInteger("1"), 100, new Date(2022-1-1), "FROZEN", new BigInteger("3") });

        listObj.add(new Object[] {
            new BigInteger("2"), new BigInteger("2"), 50, new Date(2022-2-1), "FROZEN", new BigInteger("3") });

        return listObj;
    }

    // RETORNO DO REPOSITORY - ASC
    public static List<Object[]> AdsensByDueDateAndCategoryListObjectAsc() {
        List<Object[]> listObj = new ArrayList<>();

        listObj.add(new Object[] {
            new BigInteger("2"), new BigInteger("2"), 50, new Date(2022-2-1), "FROZEN", new BigInteger("3") });

        listObj.add(new Object[] {
            new BigInteger("1"), new BigInteger("1"), 100, new Date(2022-1-1), "FROZEN", new BigInteger("3") });

        return listObj;
    }

    // RETORNO DO SERVICE - DESC
    public static List<AdsensByDueDateAndCategoryDto> AdsensByDueDateAndCategoryDtoListDesc() {
        List<AdsensByDueDateAndCategoryDto> listDto = new ArrayList<>();

        listDto.add(new AdsensByDueDateAndCategoryDto (
            new BigInteger("1"), new BigInteger("1"), 100, new Date(2022-1-1), "FROZEN", new BigInteger("3") ));

        listDto.add(new AdsensByDueDateAndCategoryDto (
            new BigInteger("2"), new BigInteger("2"), 50, new Date(2022-2-1), "FROZEN", new BigInteger("3") ));

        return listDto;
    }

    // RETORNO DO SERVICE - ASC
    public static List<AdsensByDueDateAndCategoryDto> AdsensByDueDateAndCategoryDtoListAsc() {
        List<AdsensByDueDateAndCategoryDto> listDto = new ArrayList<>();

        listDto.add(new AdsensByDueDateAndCategoryDto (
            new BigInteger("2"), new BigInteger("2"), 50, new Date(2022-2-1), "FROZEN", new BigInteger("3") ));

        listDto.add(new AdsensByDueDateAndCategoryDto (
            new BigInteger("1"), new BigInteger("1"), 100, new Date(2022-1-1), "FROZEN", new BigInteger("3") ));

        return listDto;
    }
}
