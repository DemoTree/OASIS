package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class Affiliation {
    //机构需要机构名、作者总数、文献总数、引用总数、研究方向
    private String name;

    private double heat;

    private List<Author2> authors;

    private List<Document> documents;

    private List<String> directions;
}
