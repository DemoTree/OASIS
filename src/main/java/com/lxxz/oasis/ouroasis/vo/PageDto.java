package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    private Integer count;

    private List<T> rows;

    private String computeTime;

}
