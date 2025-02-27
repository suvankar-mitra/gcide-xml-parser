package com.moo.suvankar.gxp.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "dictionary_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entryWord;
    private String headWord;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "dictionary_entry_id")
    private List<Definition> definitions = new LinkedList<>();

    @ElementCollection
    private List<String> partsOfSpeech = new LinkedList<>();

    @Embedded
    private Synonym synonym = new Synonym();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "dictionary_entry_id")
    private List<VerbMorphologyEntry> verbMorphologyEntries = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "dictionary_entry_id")
    private List<Quote> quotes = new LinkedList<>();
}
