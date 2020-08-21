package com.lxxz.oasis.ouroasis.blImpl;


import com.lxxz.oasis.ouroasis.bl.SearchService;
import com.lxxz.oasis.ouroasis.data.SearchMapper;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    //通过作者姓名获得论文的列表
    @Override
    public ResponseVO searchDocumentByAuthor(String authorName) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentByAuthor(authorName));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchDocumentByAffiliation(String affiliation) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentByAffiliation(affiliation));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchDocumentByMeeting(String meeting) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentByMeeting(meeting));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchDocumentByKeyword(String keyword) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentByKeyword(keyword));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchDocumentCombined(String authorName, String affiliation, String meeting, String keyword) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentCombined(authorName,affiliation,meeting,keyword));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchDocumentByTitle(String title) {
        try{
            return ResponseVO.buildSuccess(searchMapper.selectDocumentByTitle(title));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
