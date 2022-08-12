package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.SectionRepository;
import br.com.meli.desafio_final.util.SectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
// TODO: RENOMEAR ARQUIVO E CLASSE (ÚLTIMA PALAVRA), PARA MANTER O PADRÃO - REMOVER O "E" DA PALAVRA "TEST"
public class SectionServiceTeste {

    @InjectMocks
    SectionService sectionService;

    @Mock
    SectionRepository sectionRepository;

    // TODO: REMOVER A PALAVRA "TEST" DOS NOMES DOS MÉTODOS, POIS A MAIORIA NÃO POSSUI
    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testSetAndUpdateCapacity() {
        Section section = SectionUtils.newSectionFresh();

        BDDMockito.when(sectionRepository.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(section);
        sectionService.setAndUpdateCapacity(10D, section);

        verify(sectionRepository, only()).save(section);
    }

    @Test
    public void testIfSetAndUpdateCapacityThrowsException() {
        Section section = SectionUtils.newSectionFresh();
        Exception exceptionResponse = null;
        try {
            sectionService.setAndUpdateCapacity(10000D, section);
        } catch (Exception exception) {
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("Setor sem espaço para armazer o lote");
    }

    @Test
    public void testUpdateSection() {
        Section section = SectionUtils.newSectionFresh();

        BDDMockito.when(sectionRepository.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(section);
        Section sectionResponse = sectionService.update(section);

        assertThat(sectionResponse).isNotNull();
        assertThat(sectionResponse).isEqualTo(section);
    }

    @Test
    public void testFindByCategory() {
        Section section = SectionUtils.newSectionRefrigerated();
        BDDMockito.when(sectionRepository.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(List.of(section));

        List<Section> sectionResponse = sectionService.findByCategory(Category.REFRIGERATED);

        assertThat(sectionResponse).isNotNull();
        Assertions.assertEquals(sectionResponse.get(0).getId(), section.getId());
    }

    @Test
    public void testFindByCategoryThrowsException() {
        BDDMockito.when(sectionRepository.findByCategory(ArgumentMatchers.any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    throw new NotFound("Categoria não localizada.");
                });

        Exception exceptionResponse = null;
        try {
            sectionService.findByCategory(Category.REFRIGERATED);
        } catch (NotFound exception) {
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("Categoria não localizada.");
    }
}
