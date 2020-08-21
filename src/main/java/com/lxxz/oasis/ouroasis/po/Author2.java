package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class Author2 {
    private String name;

    private List<String> affiliations;

    private double heat;

    private List<Document> documents;

    private List<String> directions;
}
