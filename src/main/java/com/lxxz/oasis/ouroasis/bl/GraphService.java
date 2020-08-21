package com.lxxz.oasis.ouroasis.bl;

import com.lxxz.oasis.ouroasis.vo.AffiliationPortraitVO;
import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;

import java.util.List;

/**
 * @author xyy
 * @date 2020/4/6 2:23 PM
 */
public interface GraphService {

    List<AuthorPortraitVO> getAuthorRelations();

    List<AffiliationPortraitVO> getAffRelations();

    List<DirectionPortraitVO> getDirRelations();
}
