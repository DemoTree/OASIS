package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.SearchService;
import com.lxxz.oasis.ouroasis.po.Document;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class searchServiceTest {

    @Autowired
    SearchService searchService;

    @Test
    void test(){
        ResponseVO responseVO=searchService.searchDocumentByAffiliation("qwert123");
        assertTrue(responseVO.getSuccess());
    }

}
