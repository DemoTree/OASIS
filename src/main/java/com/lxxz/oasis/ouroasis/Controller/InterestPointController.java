package com.lxxz.oasis.ouroasis.Controller;


import com.lxxz.oasis.ouroasis.bl.InterestPointService;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestPointController {
    @Autowired
    private InterestPointService interestPointService;

    @RequestMapping(value = "/interest/publication",method = RequestMethod.GET)
    public ResponseVO getMostPaperPublicationTitle(){
        return interestPointService.getMostPaperPublicationTitle();
    }

    @RequestMapping(value = "/interest/document",method = RequestMethod.GET)
    public ResponseVO getMostReferenceDocumentTitle(){
        return interestPointService.getMostReferenceDocumentTitle();
    }
}
