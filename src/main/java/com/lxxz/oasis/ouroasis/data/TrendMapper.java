package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.vo.TrendForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TrendMapper {

    List<TrendForm> selectTopTenDirectionOfEachYear(@Param("year") int year);

    List<Integer> selectYears();
}

