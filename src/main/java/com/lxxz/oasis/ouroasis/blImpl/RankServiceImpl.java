package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.Util.CommonUtil;
import com.lxxz.oasis.ouroasis.bl.RankService;
import com.lxxz.oasis.ouroasis.data.AuthorRankDao;
import com.lxxz.oasis.ouroasis.po.AuthorPortrait;
import com.lxxz.oasis.ouroasis.po.AuthorRank;
import com.lxxz.oasis.ouroasis.vo.PageDto;
import com.lxxz.oasis.ouroasis.vo.PubAuthor;
import com.lxxz.oasis.ouroasis.vo.PubAuthorRequest;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private AuthorRankDao authorRankDao;

    public List<AuthorRank> getPubAuthor(){

        List<AuthorRank> result = authorRankDao.selectAuthorRanks();
//        RowBounds rowBounds = CommonUtil.convertRowBounds(pubAuthorRequest.getPageNum(),pubAuthorRequest.getPageSize());
//
//        result.setCount(authorRankDao.selectAuthorCount());
//        result.setRows(authorRankDao.selectAuthorRanks(rowBounds));

        return result;
    }

    public List<AuthorRank> getCiteAuthor(){

        List<AuthorRank> result = authorRankDao.selectAuthorCiteRanks();
        return result;
    }

}
