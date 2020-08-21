package com.lxxz.oasis.ouroasis.bl;

import com.lxxz.oasis.ouroasis.po.AuthorRank;

import java.util.List;

public interface RankService {

    List<AuthorRank> getPubAuthor();

    List<AuthorRank> getCiteAuthor();

}
