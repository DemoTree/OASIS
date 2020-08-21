package com.lxxz.oasis.ouroasis.data;


import com.lxxz.oasis.ouroasis.po.Document;
import com.lxxz.oasis.ouroasis.vo.DocumentForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SearchMapper {
    List<Document> selectDocumentByAuthor(@Param("authorName") String authorName);

    List<Document> selectDocumentByAffiliation(@Param("affiliation") String affiliation);

    List<Document> selectDocumentByMeeting(@Param("meeting") String meeting);

    List<Document> selectDocumentByKeyword(@Param("keyword") String keyword);

    List<Document> selectDocumentCombined(@Param("authorName") String authorName, @Param("affiliation") String affiliation, @Param("meeting") String meeting, @Param("keyword") String keyword);

    DocumentForm selectDocumentByTitle(@Param("title") String title);


}
