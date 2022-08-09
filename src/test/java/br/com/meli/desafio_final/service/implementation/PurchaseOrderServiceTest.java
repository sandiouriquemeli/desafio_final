package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PurchaseOrderServiceTest {

    @InjectMocks
    private PurchaseOrderService service;

    private PurchaseOrderRepository repository;

    @Test
    void findById() {
    }
}