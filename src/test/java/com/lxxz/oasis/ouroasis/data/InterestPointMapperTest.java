package com.lxxz.oasis.ouroasis.data;
import com.lxxz.oasis.ouroasis.blImpl.InitServiceImpl;
import com.lxxz.oasis.ouroasis.po.Affiliation;
import com.lxxz.oasis.ouroasis.po.Author2;
import com.lxxz.oasis.ouroasis.po.Direction;
import com.lxxz.oasis.ouroasis.po.Publication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class InterestPointMapperTest {
    @Autowired
    InterestPointMapper interestPointMapper;
    @Autowired
    InterestPointMapper2 interestPointMapper2;

    @Test
    void getMostPaperPublicationTitle(){
        List<Publication> docs = interestPointMapper.getMostPaperPublicationTitle();
        assertEquals("2015 IEEE/ACM 37th IEEE International Conference on Software Engineering",docs.get(0).getPublicationTitle());
        assertEquals("2018 IEEE/ACM 40th International Conference on Software Engineering (ICSE)",docs.get(1).getPublicationTitle());
    }

//    @Test
//    void getMostReferenceDocumentTitle(){
//        List<String> docs = interestPointMapper.getMostReferenceDocumentTitle();
//        assertEquals("Software Documentation Issues Unveiled",docs.get(0));
//    }

    @Test
    void getAuthorHeatRank(){
        List<Author2> author = interestPointMapper2.getAuthorHeatRank();
        assertEquals("Y. Liu",author.get(0).getName());
        assertEquals(2696,author.get(0).getHeat());
    }

    @Test
   void getAffiliationHeatRank(){
        List<Affiliation> aff = interestPointMapper2.getAffiliationHeatRank();
        assertEquals("Peking University",aff.get(0).getName());
        assertEquals(12978,aff.get(0).getHeat());
    }

    @Test
    void getDirectionHeatRank(){
        List<Direction> direction =  interestPointMapper2.getDirectionHeatRank();
        assertEquals("static analysis",direction.get(0).getName());
        assertEquals(1794,direction.get(0).getHeat());


    }


}
