package com.lxxz.oasis.ouroasis.bl;

import java.io.IOException;

/**
 * @author xyy
 * @date 2020/3/20 2:01 PM
 */
public interface InitService {

    /**
     *指定读取相应的xsl或者其他
     * @throws IOException
     */
    void init() throws IOException;

    //初始化表格
    void initTables(String goal) throws IOException;

    //计算活跃度，并存储
    void figureOutHeat();

    void calAuthorHeat();

    void getAuthorRankTable();
}
