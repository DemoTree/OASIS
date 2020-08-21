package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.InterestPointService;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class InterestPointServiceTest {

    @Autowired
    InterestPointService interestPointService;

    @Test
    public void test(){
        ResponseVO responseVO = interestPointService.getMostPaperPublicationTitle();
        assertTrue(responseVO.getSuccess());
        //System.out.println(responseVO.getContent().toString());
       // assertEquals("2015 IEEE/ACM 37th IEEE ",responseVO.getContent()[0]);
    }

}
