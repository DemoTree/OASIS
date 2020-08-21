package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.AuthorRank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthorRankDao {
    int deleteByPrimaryKey(String author);

    int insert(AuthorRank record);

    int insertSelective(AuthorRank record);

    AuthorRank selectByPrimaryKey(String author);

    int updateByPrimaryKeySelective(AuthorRank record);

    int updateByPrimaryKey(AuthorRank record);

    Integer selectAuthorCount();

    List<String> selectAllAuthors();

    Integer selectAuthorPubCount(@Param("author")String author);

    List<String> selectDocsByAuthor(@Param("author")String author);

    Integer selectCiteCountByDoc(@Param("docTitle")String docTitle);

    List<AuthorRank> selectAuthorRanks();

    List<AuthorRank> selectAuthorCiteRanks();

}