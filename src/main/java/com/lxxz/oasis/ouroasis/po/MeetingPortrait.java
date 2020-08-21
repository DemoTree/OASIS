package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class MeetingPortrait {
    private String name;

    private List<AuthorPortrait> authorPortraits;

    private List<AffiliationPortrait> affiliationPortraits;

    private List<Document> documents;

}
