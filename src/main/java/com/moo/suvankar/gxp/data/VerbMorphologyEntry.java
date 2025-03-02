package com.moo.suvankar.gxp.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerbMorphologyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> partsOfSpeech = new LinkedList<>();
    private String conjugatedForm;

    @ManyToOne
    @JoinColumn(name = "dictionary_entry_id")
    private DictionaryEntry dictionaryEntry;
}
