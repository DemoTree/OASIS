package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

import java.util.Map;

@Data
public class TrendForm {
    //存储每一年的热度排名前三的热点
    //前端需要将这个数据按年份制作柱状图
    private String name;

    private int count;
}
