PRAGMA journal_mode=WAL;

-----------------------------
-- Cleanup: Drop existing tables
-----------------------------
DROP TABLE IF EXISTS definition;
DROP TABLE IF EXISTS dictionary_entry_parts_of_speech;
DROP TABLE IF EXISTS dictionary_entry;
DROP TABLE IF EXISTS quote;
DROP TABLE IF EXISTS verb_morphology_entry_parts_of_speech;
DROP TABLE IF EXISTS verb_morphology_entry;
DROP TABLE IF EXISTS synonyms;

-----------------------------
-- Table Definitions
-----------------------------
CREATE TABLE dictionary_entry (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    entry_word TEXT,
    head_word TEXT,
    source TEXT
);

CREATE TABLE synonyms (
    dictionary_entry_id INTEGER,
    synonym TEXT,
    FOREIGN KEY (dictionary_entry_id) REFERENCES dictionary_entry(id)
);

CREATE INDEX idx_synonyms_dictionary_entry_id ON synonyms(dictionary_entry_id);

CREATE TABLE dictionary_entry_parts_of_speech (
    dictionary_entry_id INTEGER,
    parts_of_speech TEXT,
    FOREIGN KEY (dictionary_entry_id) REFERENCES dictionary_entry(id)
);

CREATE INDEX idx_dictionary_entry_parts_of_speech_dictionary_entry_id ON dictionary_entry_parts_of_speech(dictionary_entry_id);

CREATE TABLE definition (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dictionary_entry_id INTEGER,
    mark TEXT,
    source TEXT,
    definition TEXT,
    FOREIGN KEY (dictionary_entry_id) REFERENCES dictionary_entry(id)
);

CREATE INDEX idx_definition_dictionary_entry_id ON definition(dictionary_entry_id);

CREATE TABLE quote (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dictionary_entry_id INTEGER,
    author TEXT,
    source TEXT,
    text TEXT,
    FOREIGN KEY (dictionary_entry_id) REFERENCES dictionary_entry(id)
);

CREATE INDEX idx_quote_dictionary_entry_id ON quote(dictionary_entry_id);

CREATE TABLE verb_morphology_entry (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dictionary_entry_id INTEGER,
    conjugated_form TEXT,
    FOREIGN KEY (dictionary_entry_id) REFERENCES dictionary_entry(id)
);

CREATE INDEX idx_verb_morphology_entry_dictionary_entry_id ON verb_morphology_entry(dictionary_entry_id);

CREATE TABLE verb_morphology_entry_parts_of_speech (
    verb_morphology_entry_id INTEGER,
    parts_of_speech TEXT,
    FOREIGN KEY (verb_morphology_entry_id) REFERENCES verb_morphology_entry(id)
);

CREATE INDEX idx_verb_morphology_entry_parts_of_speech_verb_morphology_entry_id ON verb_morphology_entry_parts_of_speech(verb_morphology_entry_id);