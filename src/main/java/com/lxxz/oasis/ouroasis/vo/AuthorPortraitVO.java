package com.lxxz.oasis.ouroasis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPortraitVO {
    private String name;

    private String aff;

    private double heat;//活跃度

    private int documentCount;

    private int referenceCount;

    private List<String> documents;

    private Map<String,Integer> directions;

    private List<String> relatedAuthors;

    private List<String> meetings;

}
