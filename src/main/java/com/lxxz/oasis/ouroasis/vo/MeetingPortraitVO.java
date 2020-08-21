package com.lxxz.oasis.ouroasis.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class MeetingPortraitVO {
    private String name;

    private int authorCount;

    private int documentCount;

    private int referenceCount;

    private List<String> authors;

    private List<String> documents;

    private List<String> affiliations;

    private Map<String,Integer> directions;

    private List<String> preMeetings;
}
