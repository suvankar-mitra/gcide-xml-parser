package com.moo.suvankar.gxp.services;

import com.moo.suvankar.gxp.repositories.DictionaryEntryRepository;
import com.moo.suvankar.gxp.utils.DictionaryEntryMapper;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DictionaryEntryService {

    private final DictionaryEntryRepository repository;
    private final DictionaryEntryMapper mapper;

    public DictionaryEntryService(DictionaryEntryRepository repository, DictionaryEntryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public com.moo.suvankar.gxp.models.DictionaryEntry save(final com.moo.suvankar.gxp.models.DictionaryEntry pojo) {

//        // check if same entry is already present, if so skip insert
//        if(repository.existsByEntryWord(pojo.getEntryWord())) {
//            return null;
//        }

        com.moo.suvankar.gxp.data.DictionaryEntry entity = mapper.toEntity(pojo);
        com.moo.suvankar.gxp.data.DictionaryEntry savedEntity = repository.save(entity);
        return mapper.toPojo(savedEntity);
    }

    @Transactional
    public List<com.moo.suvankar.gxp.models.DictionaryEntry> findEntriesByWord(final String word) {
        List<com.moo.suvankar.gxp.data.DictionaryEntry> entityEntries = repository.findByEntryWord(word);

        entityEntries.forEach(entry -> Hibernate.initialize(entry.getDefinitions()));
        entityEntries.forEach(entry -> Hibernate.initialize(entry.getPartsOfSpeech()));
        entityEntries.forEach(entry -> Hibernate.initialize(entry.getSynonym()));
        entityEntries.forEach(entry -> Hibernate.initialize(entry.getVerbMorphologyEntries()));
        entityEntries.forEach(entry -> Hibernate.initialize(entry.getQuotes()));


        List<com.moo.suvankar.gxp.models.DictionaryEntry> pojoEntries = new LinkedList<>();

        entityEntries.forEach(entity -> pojoEntries.add(mapper.toPojo(entity)));

        return pojoEntries;
    }
}
