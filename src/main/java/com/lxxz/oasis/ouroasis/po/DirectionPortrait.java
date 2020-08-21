package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class DirectionPortrait {
    private String name;

    private double heat;

    private List<Document> documents;

    private List<AuthorPortrait> authorPortraits;

    private List<AffiliationPortrait> affiliationPortraits;

}
