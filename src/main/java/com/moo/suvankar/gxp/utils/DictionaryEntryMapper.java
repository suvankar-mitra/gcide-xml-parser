/*
 * Copyright (C) 2025 Suvankar Mitra
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.moo.suvankar.gxp.utils;

import org.springframework.stereotype.Component;

@Component
public class DictionaryEntryMapper {
    public com.moo.suvankar.gxp.data.DictionaryEntry toEntity(com.moo.suvankar.gxp.models.DictionaryEntry pojo) {
        com.moo.suvankar.gxp.data.DictionaryEntry entity = new com.moo.suvankar.gxp.data.DictionaryEntry();
        entity.setEntryWord(pojo.getEntryWord());
        entity.setHeadWord(pojo.getHeadWord());

        for(com.moo.suvankar.gxp.models.Definition pojoDef : pojo.getDefinitions()) {
            com.moo.suvankar.gxp.data.Definition entityDef = new com.moo.suvankar.gxp.data.Definition();
            entityDef.setDefinition(pojoDef.getDefinition());
            entityDef.setMark(pojoDef.getMark());
            entityDef.setSource(pojoDef.getSource());
            entity.getDefinitions().add(entityDef);
        }

        entity.setPartsOfSpeech(pojo.getPartsOfSpeech());

        com.moo.suvankar.gxp.data.Synonym entitySyn = new com.moo.suvankar.gxp.data.Synonym();
        entitySyn.setSource(pojo.getSynonym().getSource());
        entitySyn.setSynonymList(pojo.getSynonym().getSynonymList());
        entity.setSynonym(entitySyn);

        for(com.moo.suvankar.gxp.models.VerbMorphologyEntry pojoVerb: pojo.getVerbMorphologyEntries()) {
            com.moo.suvankar.gxp.data.VerbMorphologyEntry entityVerb = new com.moo.suvankar.gxp.data.VerbMorphologyEntry();
            entityVerb.setConjugatedForm(pojoVerb.getConjugatedForm());
            entityVerb.getPartsOfSpeech().addAll(pojoVerb.getPartsOfSpeech());
            entity.getVerbMorphologyEntries().add(entityVerb);
        }

        for(com.moo.suvankar.gxp.models.Quote pojoQuote : pojo.getQuotes()) {
            com.moo.suvankar.gxp.data.Quote entityQuote = new com.moo.suvankar.gxp.data.Quote();
            entityQuote.setSource(pojoQuote.getSource());
            entityQuote.setAuthor(pojoQuote.getAuthor());
            entityQuote.setText(pojoQuote.getText());
            entity.getQuotes().add(entityQuote);
        }

        return entity;
    }

    public com.moo.suvankar.gxp.models.DictionaryEntry toPojo(com.moo.suvankar.gxp.data.DictionaryEntry pojo) {
        com.moo.suvankar.gxp.models.DictionaryEntry entity = new com.moo.suvankar.gxp.models.DictionaryEntry();
        entity.setEntryWord(pojo.getEntryWord());
        entity.setHeadWord(pojo.getHeadWord());

        for(com.moo.suvankar.gxp.data.Definition pojoDef : pojo.getDefinitions()) {
            com.moo.suvankar.gxp.models.Definition entityDef = new com.moo.suvankar.gxp.models.Definition();
            entityDef.setDefinition(pojoDef.getDefinition());
            entityDef.setMark(pojoDef.getMark());
            entityDef.setSource(pojoDef.getSource());
            entity.getDefinitions().add(entityDef);
        }

        entity.setPartsOfSpeech(pojo.getPartsOfSpeech());

        com.moo.suvankar.gxp.models.Synonym entitySyn = new com.moo.suvankar.gxp.models.Synonym();
        entitySyn.setSource(pojo.getSynonym().getSource());
        entitySyn.setSynonymList(pojo.getSynonym().getSynonymList());
        entity.setSynonym(entitySyn);

        for(com.moo.suvankar.gxp.data.VerbMorphologyEntry pojoVerb: pojo.getVerbMorphologyEntries()) {
            com.moo.suvankar.gxp.models.VerbMorphologyEntry entityVerb = new com.moo.suvankar.gxp.models.VerbMorphologyEntry();
            entityVerb.setConjugatedForm(pojoVerb.getConjugatedForm());
            entityVerb.getPartsOfSpeech().addAll(pojoVerb.getPartsOfSpeech());
            entity.getVerbMorphologyEntries().add(entityVerb);
        }

        for(com.moo.suvankar.gxp.data.Quote pojoQuote : pojo.getQuotes()) {
            com.moo.suvankar.gxp.models.Quote entityQuote = new com.moo.suvankar.gxp.models.Quote();
            entityQuote.setSource(pojoQuote.getSource());
            entityQuote.setAuthor(pojoQuote.getAuthor());
            entityQuote.setText(pojoQuote.getText());
            entity.getQuotes().add(entityQuote);
        }

        return entity;
    }
}
