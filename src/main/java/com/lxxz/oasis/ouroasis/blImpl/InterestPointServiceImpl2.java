package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.bl.InterestPointService2;
import com.lxxz.oasis.ouroasis.data.InterestPointMapper;
import com.lxxz.oasis.ouroasis.data.InterestPointMapper2;
import com.lxxz.oasis.ouroasis.po.Affiliation;
import com.lxxz.oasis.ouroasis.po.Author2;
import com.lxxz.oasis.ouroasis.po.Direction;
import com.lxxz.oasis.ouroasis.vo.AffiliationVO;
import com.lxxz.oasis.ouroasis.vo.AuthorVO;
import com.lxxz.oasis.ouroasis.vo.DirectionVO;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class InterestPointServiceImpl2 implements InterestPointService2 {
    @Autowired
    private InterestPointMapper2 interestPointMapper;

    @Override
    public ResponseVO getAuthorHeatRank() {
        try{
            List<Author2> authors=interestPointMapper.getAuthorHeatRank();
            List<AuthorVO> authorVOS=new ArrayList<AuthorVO>();
            for(int i=0;i<authors.size();i++){
                Author2 author=authors.get(i);
                AuthorVO authorVO=new AuthorVO();
                authorVO.setName(author.getName());
                authorVO.setHeat(author.getHeat());
                int documentCount=author.getDocuments().size();
                authorVO.setDocumentCount(documentCount);
                int referenceCount=0;
                for(int j=0;j<documentCount;j++){
                    referenceCount+=author.getDocuments().get(j).getReferenceCount();
                }
                authorVO.setReferenceCount(referenceCount);
                //取引用数最多的论文的研究方向，作为作者的研究方向
                List<String> directions=author.getDocuments().get(0).getKeywords();
                int currentRef=author.getDocuments().get(0).getReferenceCount();
                for(int j=1;j<documentCount;j++){
                    if(author.getDocuments().get(j).getReferenceCount()>currentRef){
                        currentRef=author.getDocuments().get(j).getReferenceCount();
                        directions=author.getDocuments().get(j).getKeywords();
                    }
                }
                authorVO.setDirections(directions);
                authorVOS.add(authorVO);
            }

            return ResponseVO.buildSuccess(authorVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAffiliationHeatRank() {
        try{
            List<Affiliation> affiliations=interestPointMapper.getAffiliationHeatRank();
            List<AffiliationVO> affiliationVOS=new ArrayList<AffiliationVO>();
            for(int i=0;i<affiliations.size();i++){
                Affiliation affiliation=affiliations.get(i);
                AffiliationVO affiliationVO=new AffiliationVO();
                affiliationVO.setName(affiliation.getName());
                affiliationVO.setHeat(affiliation.getHeat());
                int authorCount=affiliation.getAuthors().size();
                affiliationVO.setAuthorCount(authorCount);
                int documentCount=affiliation.getDocuments().size();
                affiliationVO.setDocumentCount(documentCount);
                int referenceCount=0;
                for(int j=0;j<documentCount;j++){
                    referenceCount+=affiliation.getDocuments().get(j).getReferenceCount();
                }
                affiliationVO.setReferenceCount(referenceCount);
                //取引用数最多的论文的研究方向，作为机构的研究方向
                List<String> directions=affiliation.getDocuments().get(0).getKeywords();
                int currentRef=affiliation.getDocuments().get(0).getReferenceCount();
                for(int j=1;j<documentCount;j++){
                    if(affiliation.getDocuments().get(j).getReferenceCount()>currentRef){
                        currentRef=affiliation.getDocuments().get(j).getReferenceCount();
                        directions=affiliation.getDocuments().get(j).getKeywords();
                    }
                }
                affiliationVO.setDirections(directions);
                affiliationVOS.add(affiliationVO);

            }

            return ResponseVO.buildSuccess(affiliationVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getDirectionHeatRank() {
        try{
            List<Direction> directions=interestPointMapper.getDirectionHeatRank();
            List<DirectionVO> directionVOS=new ArrayList<DirectionVO>();
            for(int i=0;i<directions.size();i++){
                Direction direction=directions.get(i);
                DirectionVO directionVO=new DirectionVO();
                directionVO.setName(direction.getName());
                directionVO.setHeat(direction.getHeat());
                directionVOS.add(directionVO);
            }
            return ResponseVO.buildSuccess(directionVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

}
