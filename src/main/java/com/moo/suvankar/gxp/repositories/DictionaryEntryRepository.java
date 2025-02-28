package com.moo.suvankar.gxp.repositories;

import com.moo.suvankar.gxp.data.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {

    List<DictionaryEntry> findByEntryWord(String entryWord);

    boolean existsByEntryWord(String entryWord);
}
