package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class GrapheMapperTest {

    @Autowired
    GraphMapper graphMapper;

    @Test
    void getAuthorHeatTest(){
        List<AuthorPortraitVO> authorPortraitVOList = graphMapper.getAuthorHeat();
        System.out.println(authorPortraitVOList.get(0));
    }

}
