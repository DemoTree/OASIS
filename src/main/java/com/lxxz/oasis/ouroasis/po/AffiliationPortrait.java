package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class AffiliationPortrait {
    private String name;

    private double heat;

    //只用来记录作者的名字和热度
    private List<AuthorPortrait> authorPortraits;

    //用来记录论文、引用数、关键词（方向）、会议
    private List<Document> documents;
}
