package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

import java.util.List;

@Data
public class PubAuthor {

    private String name;

    //论文发表次数
    private int pubNum;

    List<String> documents;

}
