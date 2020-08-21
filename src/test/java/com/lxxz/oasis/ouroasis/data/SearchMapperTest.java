package com.lxxz.oasis.ouroasis.data;


import com.lxxz.oasis.ouroasis.blImpl.InitServiceImpl;
import com.lxxz.oasis.ouroasis.po.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SearchMapperTest {
    @Autowired
    SearchMapper searchMapper;

    @Test
    void selectDocumentByAuthor(){
        List<Document> docs = searchMapper.selectDocumentByAuthor("J. Penix");
        assertEquals("Big problems in industry (panel)",docs.get(0).getDocumentTitle());
    }

    @Test
    void selectDocumentByAffiliation(){
        List<Document> docs = searchMapper.selectDocumentByAffiliation("Google");
        assertEquals(14,docs.size());
    }

    @Test
    void selectDocumentByMeeting(){
        List<Document> docs = searchMapper.selectDocumentByMeeting("2013");
        assertEquals(95,docs.size());
    }

    @Test
    void selectDocumentByKeyword(){
        List<Document> docs = searchMapper.selectDocumentByKeyword("cloud");
        assertEquals(6,docs.size());
    }

    @Test
    void selectDocumentCombined(){
        List<Document> docs = searchMapper.selectDocumentCombined("M. Zhou","Tsinghua","2019","Error Specification");
        assertEquals("Ares: Inferring Error Specifications through Static Analysis",docs.get(0).getDocumentTitle());
        assertEquals(12,docs.get(0).getReferenceCount());
    }
}
