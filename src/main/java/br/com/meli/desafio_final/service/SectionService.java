package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.NotAcceptable;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService implements ISectionService{

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public Section update(Section section) {
        return sectionRepository.save(section);
    }

    public void setAndUpdateCapacity(double batchVolumen, Section section){
        if (section.getUsedCapacity() >= batchVolumen){
            section.setUsedCapacity(section.getUsedCapacity() - batchVolumen);
            update(section);
        }else{
            throw new NotAcceptable("Setor sem espaço para armazer o lote");
        }
    }



}