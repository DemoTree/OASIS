package com.lxxz.oasis.ouroasis.po;

import com.lxxz.oasis.ouroasis.vo.TrendForm;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Trend {
    //存储每一年的热度排名前三的热点
    //前端需要将这个数据按年份制作柱状图
    private Map<Integer, List<TrendForm>> trendForms;
}
