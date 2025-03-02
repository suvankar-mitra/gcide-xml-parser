package com.moo.suvankar.gxp.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Synonym {
    @ElementCollection 
    @CollectionTable(name = "synonyms", joinColumns = @JoinColumn(name = "dictionary_entry_id"))
    @Column(name = "synonym", length = 5000)
    private List<String> synonymList = new LinkedList<>();
    private String source;
}
