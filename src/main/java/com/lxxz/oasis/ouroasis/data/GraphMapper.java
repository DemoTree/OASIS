package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.vo.AffiliationPortraitVO;
import com.lxxz.oasis.ouroasis.vo.AuthorPortraitVO;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GraphMapper {

    List<AuthorPortraitVO> getAuthorHeat();

    List<String> getTitlesOfAuthor(@Param("author")String author);

    List<String> getAuthorsByTitle(@Param("title")String title);

    List<AffiliationPortraitVO> getAffHeat();

    int getHeatOfAuthor(String author);

    List<String> getTitlesByAff(String aff);

    List<String> getAffsByTitle(String title);

    List<DirectionPortraitVO> getDirHeat();

    List<String> getTitlesByDir(String dir);

    List<String> getDirsByTitle(String title);
}
