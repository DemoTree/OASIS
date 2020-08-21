package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.RankService;
import com.lxxz.oasis.ouroasis.po.AuthorRank;
import com.lxxz.oasis.ouroasis.vo.PageDto;
import com.lxxz.oasis.ouroasis.vo.PubAuthorRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RankServiceTest {

    @Autowired
    RankService rankService;

    @Test
    public void getPubAuthorTest(){
//        PubAuthorRequest pubAuthorRequest = new PubAuthorRequest();
//        pubAuthorRequest.setPageNum(1);
//        pubAuthorRequest.setPageSize(10);
//        PageDto<AuthorRank> answer = rankService.getPubAuthor(pubAuthorRequest);
//        System.out.println(answer.getCount());

    }

}
