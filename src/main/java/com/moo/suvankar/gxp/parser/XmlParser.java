package com.moo.suvankar.gxp.parser;

import com.moo.suvankar.gxp.models.DictionaryEntry;

public interface XmlParser {
    /**
     * Parse each &lt;p&gt; ... &lt;/p&gt; section from the xml which has &lt;ent&gt; tag.
     * @param xmlData pass the text &lt;p&gt; ... &lt;/p&gt;
     * @return Returns a {@link DictionaryEntry}
     */
    DictionaryEntry parseDefinition(String xmlData);

    /**
     * If a quotation is available for the entry word, parse it.
     * @param xmlData Expects &lt;p&gt;&lt;q&gt; ... &lt;/q&gt;&lt;/p&gt; text
     */
    void parseQuote(String xmlData, DictionaryEntry dictionaryEntry);

    /**
     * If another definition is available for the entry word, parse it.
     * @param xmlData Expects &lt;p&gt;&lt;def&gt; ... &lt;/def&gt;&lt;/p&gt; text
     */
    void parseMoreDefinition(String xmlData, DictionaryEntry entry);

    /**
     * If another synonym is available for the entry word, parse it.
     * @param xmlData Expects &lt;p&gt;&lt;syn&gt; ... &lt;/syn&gt;&lt;/p&gt; text
     */
    void parseMoreSynonyms(final String xmlData, final DictionaryEntry entry);
}
