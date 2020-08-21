package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.bl.GraphService;
import com.lxxz.oasis.ouroasis.data.GraphMapper;
import com.lxxz.oasis.ouroasis.vo.AffiliationPortraitVO;
import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xyy
 * @date 2020/4/6 2:25 PM
 */

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    GraphMapper graphMapper;

    public List<AuthorPortraitVO> getAuthorRelations(){

        //获得作者列表，再将与之有关系的作者都填进去
        //一定不能获取相同的作者名称，不然echarts会因为无法识别而不显示
            //即authors里不能存在相同的node.name
        List<AuthorPortraitVO> authors = graphMapper.getAuthorHeat();

        //for(AuthorPortraitVO ao:authors){System.out.println(ao.getName()); }
        List<String> titles;//当前作者所作的文章
        List<String> relatedAuthors,temp,temp1;//有关的作者

        //用的最暴力，最愚蠢的方法，优化到后面再说吧
        for(int i=0;i<authors.size();i++){
            titles = graphMapper.getTitlesOfAuthor(authors.get(i).getName());
            temp1 = new ArrayList<>();
            //！！！似乎此步非常关键，在affGraph中省略了此步，导致每次的索引全部指向了同一个ArrayList
            relatedAuthors = new ArrayList<>();
            for(int m=0;m<titles.size();m++){
                temp=graphMapper.getAuthorsByTitle(titles.get(m));
                for(int n=0;n<temp.size();n++){
                    temp1.add(temp.get(n));
                }
            }
            //去重
            for(String str:temp1){
                relatedAuthors.add(str);
            }
            authors.get(i).setRelatedAuthors(relatedAuthors);
        }
        return authors;
    }

    public List<AffiliationPortraitVO> getAffRelations(){

        List<AffiliationPortraitVO> affs = graphMapper.getAffHeat();
        //获取该机构的合作机构
            //通过该机构 获得文章名称，再获得该文章的所有机构
        List<String> titles,aff1,aff2;
        for(AffiliationPortraitVO affVO:affs){
            titles=graphMapper.getTitlesByAff(affVO.getName());
            aff2 = new ArrayList<>();
            for(String title:titles){
                aff1=graphMapper.getAffsByTitle(title);
                for(String aff11:aff1){
                    if(aff11!=affVO.getName()&&aff11!="NA"){
                        aff2.add(aff11);
                    }
                }
            }
            affVO.setCoAff(aff2);
        }

        return affs;
    }

    public List<DirectionPortraitVO> getDirRelations(){

        List<DirectionPortraitVO> dirs = graphMapper.getDirHeat();

        List<String> titles,dirs1,dirs2;
        for(DirectionPortraitVO dirVO:dirs){
            titles=graphMapper.getTitlesByDir(dirVO.getName());
            dirs2 = new ArrayList<>();
            for(String title:titles){
                dirs1=graphMapper.getDirsByTitle(title);
                for(String dir1:dirs1){
                    if(dir1!=dirVO.getName()){
                        dirs2.add(dir1);
                    }
                }
            }
            dirVO.setCoDir(dirs2);

        }

        return dirs;

    }

}
