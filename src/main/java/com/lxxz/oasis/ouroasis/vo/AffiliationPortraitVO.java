package com.lxxz.oasis.ouroasis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliationPortraitVO {
    private String name;

    private double heat;//活跃度

    private int authorCount;

    private List<String> authors;

    private int documentCount;

    private int referenceCount;

    private List<String> documents;

    private Map<String,Integer> directions;

    private List<String> meetings;

    /*
    * @Author xyy
    * 2020.4.8
    * graph要用的，是该机构的合作机构
    * */
    private List<String> coAff;
}
