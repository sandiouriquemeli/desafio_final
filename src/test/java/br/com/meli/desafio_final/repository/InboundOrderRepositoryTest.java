// TODO: REMOVER ESTE ARQUIVO - NÃO FAREMOS TESTES NO REPOSITORY

package br.com.meli.desafio_final.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class InboundOrderRepositoryTest {


    @Autowired
    private InboundOrderRepository repository;

    public void saveOrUpdate_WhenValidInbourdOrder(){

    }
}
