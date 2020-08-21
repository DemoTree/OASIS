package com.lxxz.oasis.ouroasis.Util;

import org.apache.ibatis.session.RowBounds;

public class CommonUtil {

    public static RowBounds convertRowBounds(Integer pageNum, Integer pageSize) {
        return convertRowBounds(pageNum, pageSize, 10);
    }

    public static RowBounds convertRowBounds(Integer pageNum, Integer pageSize, Integer defaultSize) {
        Integer limit = (null == pageSize || 0 >= pageSize) ? defaultSize : pageSize;
        Integer start = (null == pageNum || 0 >= pageNum) ? 0 : (pageNum - 1) * limit;
        return new RowBounds(start, limit);
    }
}
