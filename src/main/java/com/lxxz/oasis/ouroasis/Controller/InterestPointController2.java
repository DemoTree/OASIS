package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.InterestPointService;
import com.lxxz.oasis.ouroasis.bl.InterestPointService2;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestPointController2 {
    @Autowired
    private InterestPointService2 interestPointService;

    @RequestMapping(value = "/interest/authorHeat",method = RequestMethod.GET)
    public ResponseVO getAuthorHeatRank(){
        return interestPointService.getAuthorHeatRank();
    }

    @RequestMapping(value = "/interest/affiliationHeat",method = RequestMethod.GET)
    public ResponseVO getAffiliationHeatRank(){
        return interestPointService.getAffiliationHeatRank();
    }

    @RequestMapping(value = "/interest/directionHeat",method = RequestMethod.GET)
    public ResponseVO getDirectionHeatRank(){
        return interestPointService.getDirectionHeatRank();
    }
}
