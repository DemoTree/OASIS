package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class AuthorPortrait {

    private String name;

    private String affiliations;

    private double heat;

    private List<Document> documents;

}
