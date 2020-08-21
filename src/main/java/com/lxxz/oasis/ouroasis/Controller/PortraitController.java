package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.PortraitSearchService;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortraitController {
    @Autowired
    PortraitSearchService portraitSearchService;

    @RequestMapping(value="/searchPortrait/authorList",method = RequestMethod.GET)
   // @ResponseJson(encode = ResponseJson.ResponseEncode.TEXT)
    public ResponseVO searchAuthorPortraits(@RequestParam String name){
        return portraitSearchService.searchAuthorPortraits(name);
    }

    @RequestMapping(value="/searchPortrait/affiliationList",method = RequestMethod.GET)
    // @ResponseJson(encode = ResponseJson.ResponseEncode.TEXT)
    public ResponseVO searchAffiliationPortraits(@RequestParam String affiliation){
        return portraitSearchService.searchAffiliationPortraits(affiliation);
    }

    @RequestMapping(value="/searchPortrait/keywordList",method = RequestMethod.GET)
    // @ResponseJson(encode = ResponseJson.ResponseEncode.TEXT)
    public ResponseVO searchDirectionPortraits(@RequestParam String keyword){
        return portraitSearchService.searchDirectionPortraits(keyword);
    }

    @RequestMapping(value="/searchPortrait/meetingList",method = RequestMethod.GET)
    // @ResponseJson(encode = ResponseJson.ResponseEncode.TEXT)
    public ResponseVO searchMeetingPortraits(@RequestParam String meeting){
        return portraitSearchService.searchMeetingPortraits(meeting);
    }

    @RequestMapping(value="/searchPortrait/author",method = RequestMethod.GET)
    public ResponseVO searchPortraitByAuthor(@RequestParam String authorName, @RequestParam String aff){
        return portraitSearchService.searchPortraitByAuthor(authorName,aff);
    }
    @RequestMapping(value="/searchPortrait/affiliation",method = RequestMethod.GET)
    public ResponseVO searchPortraitByAffiliation(@RequestParam String affiliation){
        return portraitSearchService.searchPortraitByAffiliation(affiliation);
    }
    @RequestMapping(value="/searchPortrait/keyword",method = RequestMethod.GET)
    public ResponseVO searchPortraitByKeyword(@RequestParam String keyword){
        return portraitSearchService.searchPortraitByDirection(keyword);
    }
    @RequestMapping(value="/searchPortrait/meeting",method = RequestMethod.GET)
    public ResponseVO searchPortraitByMeeting(@RequestParam String meeting){
        return portraitSearchService.searchPortraitByMeeting(meeting);
    }
}
