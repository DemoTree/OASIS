package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.PortraitSearchService;
import com.lxxz.oasis.ouroasis.vo.AffiliationPortraitVO;
import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PortraitSearchTest {

    @Autowired
    PortraitSearchService portraitSearchService;

    @Test
    public void testSearchAuthor(){
        ResponseVO responseVO = portraitSearchService.searchPortraitByAuthor("Y. Wang","Northeastern University");
        AuthorPortraitVO authorPortraitVO = (AuthorPortraitVO) responseVO.getContent();
        assertEquals(879,authorPortraitVO.getHeat());
    }

    @Test
    public void testSearchAffiliation(){
        ResponseVO responseVO = portraitSearchService.searchPortraitByAffiliation("Adobe, USA");
        AffiliationPortraitVO affiliationPortraitVO = (AffiliationPortraitVO)responseVO.getContent();
        assertEquals(65,affiliationPortraitVO.getHeat());
    }

    @Test
    public void testSearchDirection(){
        ResponseVO responseVO = portraitSearchService.searchPortraitByDirection("formal method");
        DirectionPortraitVO directionPortraitVO = (DirectionPortraitVO)responseVO.getContent();
        assertEquals(45,directionPortraitVO.getHeat());
    }

}
