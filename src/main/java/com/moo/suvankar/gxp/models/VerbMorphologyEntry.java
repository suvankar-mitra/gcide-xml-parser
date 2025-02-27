package com.moo.suvankar.gxp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerbMorphologyEntry {
    private List<String> partsOfSpeech = new LinkedList<>();
    private String conjugatedForm;
}
