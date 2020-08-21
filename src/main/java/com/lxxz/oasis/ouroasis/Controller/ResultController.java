package com.lxxz.oasis.ouroasis.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResultController {
    @RequestMapping("/PaperResultPage")
    public String goResult(){
        return "PaperResult";
    }
    @RequestMapping("/PaperDetailPage")
    public String goDetail(){
        return "PaperDetail";
    }

    @RequestMapping("/AuthorPortrait")
    public String goAuthorResult(){
        return "AuthorPortraitPage";
    }
    @RequestMapping("/AffiliationPortrait")
    public String goAffiliationResult(){
        return "AffiliationPortraitPage";
    }
    @RequestMapping("/MeetingPortrait")
    public String goMeetingResult(){
        return "MeetingPortraitPage";
    }
    @RequestMapping("/KeywordPortrait")
    public String goKeywordResult(){
        return "KeywordPortraitPage";
    }
    @RequestMapping("/AuthorPortraitResult")
    public String goAuthorPortraitResult(){
        return "AuthorPortraitResult";
    }
    @RequestMapping("/AffiliationPortraitResult")
    public String goAffiliationPortraitResult(){
        return "AffiliationPortraitResult";
    }
    @RequestMapping("/KeywordPortraitResult")
    public String goKeywordPortraitResult(){
        return "KeywordPortraitResult";
    }
    @RequestMapping("/MeetingPortraitResult")
    public String goMeetingPortraitResult(){
        return "MeetingPortraitResult";
    }

}
