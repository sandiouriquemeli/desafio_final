package br.com.meli.desafio_final.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AdsenseServiceTeste {

    @Autowired
    private AdsenseService service;

    @Test
    public void findById_returnadsenseRepository_wheValidid(){



    }
}
