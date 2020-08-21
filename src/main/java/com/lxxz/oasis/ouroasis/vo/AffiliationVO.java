package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

import java.util.List;

@Data
public class AffiliationVO {
    //机构需要机构名、作者总数、文献总数、引用总数、研究方向
    private String name;

    private double heat;

    private int authorCount;

    private int documentCount;

    private int referenceCount;

    private List<String> directions;
}
