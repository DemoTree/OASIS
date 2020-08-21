package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.AuthorTable1Entity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AuthorTable1EntityMapper {

    void insertSelective(AuthorTable1Entity authorTable1Entity);

}
