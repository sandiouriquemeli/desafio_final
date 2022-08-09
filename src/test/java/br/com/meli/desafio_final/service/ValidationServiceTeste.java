package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.repository.*;
import br.com.meli.desafio_final.service.implementation.ValidationService;
import br.com.meli.desafio_final.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTeste {

        @InjectMocks
        private ValidationService validationService;

    @Mock
    SectionRepository sectionRepository;

    @Mock
    SellerRepository sellerRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    BatchRepository batchRepository;

    @Mock
    AgentRepository agentRepository;

    @Test
    public void testValidateSection() {
    Section section = SectionUtils.newSectionFresh();

        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(section));

        validationService.validateSection(section);
        verify(sectionRepository, only()).findById(1L);
    }


    @Test
    public void testValidateSectionThrowsException() {
        Section section = SectionUtils.newSectionFresh();

        Exception testException = null;
        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateSection(section);
        } catch (Exception exception) {
            testException = exception;
        }
       assertThat(testException.getMessage()).isEqualTo("Section não encontrada");
    }

    @Test
    public void testValidateSeller() {
        Seller seller = SellerUtils.newSeller1ToSave();

        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(seller));

        validationService.validateSeller(seller);
        verify(sellerRepository, only()).findById(1L);
    }

    @Test
    public void testValidateSellerThrowsException() {
        Seller seller = SellerUtils.newSeller1ToSave();

        Exception testException = null;
        BDDMockito.when(sellerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateSeller(seller);
        } catch (Exception exception) {
            testException = exception;
        }
        assertThat(testException.getMessage()).isEqualTo("Seller não encontrada");
    }

    @Test
    public void testValidateProduct() {
        Product product = ProductUtils.newProduct1ToSave();

        BDDMockito.when(productRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(product));

        validationService.validateProduct(product);
        verify(productRepository, only()).findById(1L);
    }

    @Test
    public void testValidateProductThrowsException() {
        Product product = ProductUtils.newProduct1ToSave();

        Exception testException = null;
        BDDMockito.when(productRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateProduct(product);
        } catch (Exception exception) {
            testException = exception;
        }
        assertThat(testException.getMessage()).isEqualTo("Product não encontrada");
    }

    @Test
    public void testValidateAgent() {
        Agent agent = AgentUtils.newAgent();

        BDDMockito.when(agentRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(agent));

        validationService.validateAgent(agent.getId());
        verify(agentRepository, only()).findById(1L);
    }

    @Test
    public void testValidateAgentThrowsException() {
        Agent agent = AgentUtils.newAgent();

        Exception testException = null;
        BDDMockito.when(agentRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenAnswer(invocationOnMock -> Optional.empty());
        try {
            validationService.validateAgent(agent.getId());
        } catch (Exception exception) {
            testException = exception;
        }
        assertThat(testException.getMessage()).isEqualTo("Representante não encontrado");
    }

}
