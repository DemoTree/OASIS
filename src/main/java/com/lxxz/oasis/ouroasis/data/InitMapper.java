package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.AuthorHeatEntity;
import com.lxxz.oasis.ouroasis.po.DirectionPortrait;
import com.lxxz.oasis.ouroasis.vo.DirectionPortraitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyy
 * @date 2020/3/5 14:14 PM
 */
@Repository
@Mapper
public interface InitMapper {
    /**
     * 查询所有影厅信息
     * @return
     */


    /**
     * 根据id查询影厅
     * @return
     */

    //插入t表

    void insertAuthor(@Param("docTitle") String docTitle, @Param("authors") String authors, @Param("aff") String aff, @Param("key") String key);

    void insertCount(@Param("art") int art, @Param("ref") int ref, @Param("docTitle") String docTitle);

    void insertData(@Param("iss") String iss, @Param("mee") String mee, @Param("onl") String onl, @Param("docTitle") String docTitle);

    void insertPaper(@Param("abs") String abs, @Param("docTitle") String docTitle, @Param("end") int end, @Param("iss") String iss, @Param("vol") String vol, @Param("start") int start);

    void insertPer(@Param("docTitle") String docTitle, @Param("license") String license, @Param("ISBNs") String ISBNs, @Param("ISSN") String ISSN);

    void insertPub(@Param("ident") String ident, @Param("docTitle") String docTitle, @Param("title") String title, @Param("year") int year, @Param("publisher") String publisher);

    void insertSearch(@Param("docTitle") String docTitle, @Param("INSP") String INSP, @Param("INSPNON") String INSPNON, @Param("date") String date, @Param("dol") String dol, @Param("fund") String fund, @Param("pdf") String pdf);

    void insertTerm(@Param("IEEE") String IEEE, @Param("docTitle") String docTitle, @Param("mesh") String mesh);


    //插入d表
    void insertDAuthor(@Param("author") String author, @Param("aff") String aff, @Param("docTitle") String docTitle, @Param("place") int place);

    void insertDInsp(@Param("docTitle") String docTitle, @Param("INSP") String INSP);

    void insertDInspec(@Param("docTitle") String docTitle, @Param("INSPNON") String INSPNON);

    void insertDKey(@Param("key") String key, @Param("docTitle") String docTitle);

    void insertDTerm(@Param("term") String term, @Param("docTitle") String docTitle);

    //插入h表
    void insertHAuthor(@Param("author") String author, @Param("heat") double heat);

    void insertHAff(@Param("aff") String aff, @Param("heat") double heat);

    void insertHKey(@Param("keyword") String key, @Param("heat") double heat);

    List<AuthorHeatEntity> selectAllAuthors();

    List<String> getTitlesByAuthor(@Param("aut") String aut,@Param("aff") String aff);

    String getAuthorsByTitle(String title);

    int getCiteNumByTitle(String title);

    List<String> getAffNames();

    List<String> getAuthorsByAff(String aff);

    int getAuthorHeat(@Param("author") String author,@Param("aff")String aff);

    List<String> getDirectionsHeat();

    List<String> getTitlesByKey(String key);
}












