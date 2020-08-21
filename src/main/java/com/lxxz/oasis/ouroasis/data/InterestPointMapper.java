package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.Document;
import com.lxxz.oasis.ouroasis.po.Publication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InterestPointMapper {
    List<Publication> getMostPaperPublicationTitle();

    List<String> getMostReferenceDocumentTitle();
}
