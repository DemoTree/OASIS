package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.TrendService;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrendController {
    @Autowired
    TrendService trendService;
    @RequestMapping(value="/trend",method = RequestMethod.GET)
    public ResponseVO getTrend(){
        System.out.println("controller");
        return trendService.getTrend();
    }
}
