package com.moo.suvankar.gxp.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(length = 1000)
    private String author;
    private String source;

    @ManyToOne
    @JoinColumn(name = "dictionary_entry_id")
    private DictionaryEntry dictionaryEntry;
}
