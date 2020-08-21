package com.lxxz.oasis.ouroasis.Controller;


import com.lxxz.oasis.ouroasis.bl.SearchService;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;


    @RequestMapping(value="/search/author",method = RequestMethod.GET)
    public ResponseVO searchDocumentByAuthor(@RequestParam String authorName){
        return searchService.searchDocumentByAuthor(authorName);

    }

    @RequestMapping(value="/search/affiliation",method = RequestMethod.GET)
    public ResponseVO searchDocumentByAffiliation(@RequestParam String affiliation){
        return searchService.searchDocumentByAffiliation(affiliation);

    }

    @RequestMapping(value="/search/meeting",method = RequestMethod.GET)
    public ResponseVO searchDocumentByMeeting(@RequestParam String meeting){
        return searchService.searchDocumentByMeeting(meeting);

    }

    @RequestMapping(value="/search/keyword",method = RequestMethod.GET)
    public ResponseVO searchDocumentByKeyword(@RequestParam String keyword){
        return searchService.searchDocumentByKeyword(keyword);

    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResponseVO searchDocumentCombined(@RequestParam String authorName, @RequestParam String affiliation, @RequestParam String meeting, @RequestParam String keyword){
        System.out.println(1);
        return  searchService.searchDocumentCombined(authorName,affiliation,meeting,keyword);
    }

    @RequestMapping(value="/search/title",method = RequestMethod.GET)
    public ResponseVO searchDocumentByTitle(@RequestParam String title){
        return searchService.searchDocumentByTitle(title);

    }

}
