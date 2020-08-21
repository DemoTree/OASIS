package com.lxxz.oasis.ouroasis.blImpl;
import com.lxxz.oasis.ouroasis.bl.InterestPointService;
import com.lxxz.oasis.ouroasis.data.InterestPointMapper;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestPointServiceImpl implements InterestPointService {
    @Autowired
    private InterestPointMapper interestPointMapper;

    @Override
    public ResponseVO getMostPaperPublicationTitle() {
        try{
            return ResponseVO.buildSuccess(interestPointMapper.getMostPaperPublicationTitle());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMostReferenceDocumentTitle() {
        try{
            return ResponseVO.buildSuccess(interestPointMapper.getMostReferenceDocumentTitle());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
