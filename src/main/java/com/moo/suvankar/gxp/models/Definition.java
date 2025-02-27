package com.moo.suvankar.gxp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Definition {
    private String definition;
    private String source;
    private String mark;
}
