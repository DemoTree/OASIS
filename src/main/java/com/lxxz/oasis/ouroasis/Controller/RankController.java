package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.RankService;
import com.lxxz.oasis.ouroasis.po.AuthorRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {

    @Autowired
    RankService rankService;

    @RequestMapping(value = "/pub-rank",method = RequestMethod.GET)
    public List<AuthorRank> getAuthorPubRank(){
        return rankService.getPubAuthor();
    }

    @RequestMapping(value = "/cite-rank",method = RequestMethod.GET)
    public List<AuthorRank> getAuthorCiteRank(){
        return rankService.getCiteAuthor();
    }
}

