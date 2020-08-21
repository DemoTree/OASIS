package com.lxxz.oasis.ouroasis.data;


import com.lxxz.oasis.ouroasis.po.AffiliationPortrait;
import com.lxxz.oasis.ouroasis.po.AuthorPortrait;
import com.lxxz.oasis.ouroasis.po.DirectionPortrait;
import com.lxxz.oasis.ouroasis.po.MeetingPortrait;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PortraitSearchMapperTest {
    @Autowired
    com.lxxz.oasis.ouroasis.data.PortraitSearchMapper PortraitSearchMapper;

    @Test
    void selectPortraitByAuthor(){
        AuthorPortrait author = PortraitSearchMapper.selectPortraitByAuthorAndAff("Hyunsook Do","North Dakota State University, Computer Science, Fargo, USA");
        List<String> name = new ArrayList();
        name.add("North Dakota State University, Computer Science, Fargo, USA");
        assertEquals(name,author.getAffiliations());
        assertEquals(57.0,author.getHeat());
        assertEquals("Crushinator: A framework towards game-independent testing",author.getDocuments().get(0).getDocumentTitle());
    }

    @Test
    void selectPortraitByAffiliation(){
        AffiliationPortrait aff = PortraitSearchMapper.selectPortraitByAffiliation("Google, USA");
        assertEquals(0.0,aff.getHeat());
        assertEquals("J. Penix",aff.getAuthorPortraits().get(0).getName());
        assertEquals("Big problems in industry (panel)",aff.getDocuments().get(0).getDocumentTitle());
    }

    @Test
    void selectPortraitByDirection(){
        DirectionPortrait dir = PortraitSearchMapper.selectPortraitByDirection("testing");
        assertEquals("A System Identification Based Oracle for Control-CPS Software Fault Localization",dir.getDocuments().get(0).getDocumentTitle());
        assertEquals("Z. He", dir.getAuthorPortraits().get(0).getName());
        assertEquals("The Hong Kong Polytechnic University, Hong Kong SAR of China", dir.getAffiliationPortraits().get(0).getName());
    }

    @Test
    void  selectPortraitByMeeting(){
        MeetingPortrait meet = PortraitSearchMapper.selectPortraitByMeeting("2013");
        assertEquals("Operator-based and random mutant selection: Better together", meet.getDocuments().get(0).getDocumentTitle());
        assertEquals("University of Texas, Austin, 78712, USA", meet.getAffiliationPortraits().get(0).getName());
    }
}
