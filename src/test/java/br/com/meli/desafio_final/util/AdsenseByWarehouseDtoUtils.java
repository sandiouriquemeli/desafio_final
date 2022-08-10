package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AdsenseByWarehouseDtoUtils {

    public static List<Object[]> AdsenseByWarehouseDtoList() {
        List<Object[]> objects = new ArrayList<>();
        objects.add(new Object[] { new BigDecimal(1), new BigInteger("30")});
        objects.add(new Object[] { new BigDecimal(2), new BigInteger("30")});
        objects.add(new Object[] { new BigDecimal(3), new BigInteger("30")});
        objects.add(new Object[] { new BigDecimal(4), new BigInteger("30")});
        return objects;
    }

}
