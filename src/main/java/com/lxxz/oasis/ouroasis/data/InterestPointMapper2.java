package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.Affiliation;
import com.lxxz.oasis.ouroasis.po.Author2;
import com.lxxz.oasis.ouroasis.po.Direction;
import com.lxxz.oasis.ouroasis.po.Publication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InterestPointMapper2 {

    List<Author2> getAuthorHeatRank();

    List<Affiliation> getAffiliationHeatRank();

    List<Direction> getDirectionHeatRank();
}
