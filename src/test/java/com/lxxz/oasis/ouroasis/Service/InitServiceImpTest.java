package com.lxxz.oasis.ouroasis.Service;

import com.lxxz.oasis.ouroasis.bl.InitService;
import com.lxxz.oasis.ouroasis.blImpl.InitServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class InitServiceImpTest {

    @Autowired
    InitServiceImpl initServiceImpl;

    @Test
    public void calAuthorHeatTest(){
        initServiceImpl.calAuthorHeat();
    }

    @Test
    public void calAffHeatTest(){
        initServiceImpl.calAffHeat();
    }

    @Test
    public void calDirHeatTest(){
        initServiceImpl.calDirectionHeat();
    }

    @Test
    public void getAuthorRankTest(){initServiceImpl.getAuthorRankTable();}
}
