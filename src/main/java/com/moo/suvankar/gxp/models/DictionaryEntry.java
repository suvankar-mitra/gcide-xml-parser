package com.moo.suvankar.gxp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DictionaryEntry {
    private String entryWord;
    private String headWord;
    private List<Definition> definitions = new LinkedList<>();
    private List<String> partsOfSpeech = new LinkedList<>();
    private Synonym synonym = new Synonym();

    private List<VerbMorphologyEntry> verbMorphologyEntries = new LinkedList<>();
    private List<Quote> quotes = new LinkedList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryEntry that = (DictionaryEntry) o;
        return Objects.equals(entryWord, that.entryWord) &&
                Objects.equals(headWord, that.headWord) &&
                Objects.equals(definitions, that.definitions) &&
                Objects.equals(partsOfSpeech, that.partsOfSpeech) &&
                Objects.equals(synonym, that.synonym) &&
                Objects.equals(verbMorphologyEntries, that.verbMorphologyEntries) &&
                Objects.equals(quotes, that.quotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryWord, headWord, definitions, partsOfSpeech, synonym, verbMorphologyEntries, quotes);
    }

}
