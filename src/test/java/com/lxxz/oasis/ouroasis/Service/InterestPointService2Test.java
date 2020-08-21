package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.InterestPointService2;
import com.lxxz.oasis.ouroasis.po.Author;
import com.lxxz.oasis.ouroasis.po.Direction;
import com.lxxz.oasis.ouroasis.vo.AffiliationVO;
import com.lxxz.oasis.ouroasis.vo.AuthorVO;
import com.lxxz.oasis.ouroasis.vo.DirectionVO;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class InterestPointService2Test {

    @Autowired
    InterestPointService2 interestPointService2;

    @Test
    public void getAuthorHeatRankTest(){
        ResponseVO responseVO = interestPointService2.getAuthorHeatRank();
        List<AuthorVO> authorVO = (List<AuthorVO>)responseVO.getContent();
        assertEquals("Y. Liu",authorVO.get(0).getName());
    }

    @Test
    public void getAffiliationHeatRankTest(){
        ResponseVO responseVO = interestPointService2.getAffiliationHeatRank();
        List<AffiliationVO> affVO = (List<AffiliationVO>)responseVO.getContent();
        assertEquals("Peking University",affVO.get(0).getName());
    }

    @Test
    public void getDirectionHeatRankTest(){
        ResponseVO responseVO = interestPointService2.getDirectionHeatRank();
        List<DirectionVO>dirVO = (List<DirectionVO>)responseVO.getContent();
        assertEquals("static analysis",dirVO.get(0).getName());
    }
}
