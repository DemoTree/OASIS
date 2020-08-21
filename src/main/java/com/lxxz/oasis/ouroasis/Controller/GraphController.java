package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.bl.GraphService;
import com.lxxz.oasis.ouroasis.data.GraphMapper;
import com.lxxz.oasis.ouroasis.vo.AffiliationPortraitVO;
import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphController {

    @Autowired
    GraphService graphService;

    @GetMapping("/authorGraph")
    public List<AuthorPortraitVO> getAuthorGraph(){
        return graphService.getAuthorRelations();
    }

    @GetMapping("/affGraph")
    public List<AffiliationPortraitVO> getAffGraph(){
        return graphService.getAffRelations();
    }

    @GetMapping("/dirGraph")
    public List<DirectionPortraitVO> getDirGraph(){
        return graphService.getDirRelations();
    }
}
