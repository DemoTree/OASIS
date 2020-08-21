package com.lxxz.oasis.ouroasis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectionPortraitVO {
    private String name;

    private double heat;//活跃度

    private int authorCount;

    private int documentCount;

    private int referenceCount;

    private List<String> authors;

    private List<String> documents;

    private List<String> affiliations;

    private List<String> meetings;

    /*
     * @Author xyy 4.10
     * 该方向的伙伴方向
     * */
    private List<String> coDir;
}
