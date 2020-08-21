package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.AuthorHeatEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthorHeatEntityMapper {

    void insertIgnoreSelective(AuthorHeatEntity authorHeatEntity);

    List<AuthorHeatEntity> selectAll();

    void updateByPrimaryKeySelective(AuthorHeatEntity authorHeatEntity);

}
