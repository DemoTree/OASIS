package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    private InitService initService;

    @RequestMapping("")
    public String sayHello(){
        return "IndexPage";
    }
    
    @RequestMapping("/IndexPage")
    public String goIndex() {
        return "IndexPage";
    }

    @RequestMapping("/InitialPage")
    public String goInitial() {

        try{
            initService.init();
        }catch (IOException e){
            System.out.println("初始化错误");
            e.printStackTrace();
        }

        return "SearchPaperPage";
    }

    @RequestMapping("/SearchPaper")
    public String goSearchPaper(){
        return "SearchPaperPage";
    }
    @RequestMapping("/SearchPortrait")
    public String goSearchPortrait(){
        return "SearchPortraitPage";
    }
    @RequestMapping("/Relation1")
    public String goRelation1(){
        return "RelationPage1";
    }
    @RequestMapping("/Relation2")
    public String goRelation2(){
        return "RelationPage2";
    }
    @RequestMapping("/Relation3")
    public String goRelation3(){
        return "RelationPage3";
    }
    @RequestMapping("/Interest1")
    public String goInterest1(){
        return "InterestPage1";
    }
    @RequestMapping("/Interest2")
    public String goInterest2(){
        return "InterestPage2";
    }
    @RequestMapping("/AdminCheck")
    public String goAdminCheck(){
        return "AdminCheck";
    }
    @RequestMapping("/AdminFunc")
    public String goAdminFunc(){
        return "AdminFunc";
    }
    @RequestMapping("/Trend")
    public String goTrend(){
        return "TrendPage";
    }
    @RequestMapping("/Rank")
    public String goRank(){
        return "RankingPage";
    }
}
