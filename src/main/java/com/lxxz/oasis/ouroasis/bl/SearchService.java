package com.lxxz.oasis.ouroasis.bl;


import com.lxxz.oasis.ouroasis.vo.ResponseVO;

public interface SearchService {

    ResponseVO searchDocumentByAuthor(String authorName);

    ResponseVO searchDocumentByAffiliation(String affiliation);

    ResponseVO searchDocumentByMeeting(String meeting);

    ResponseVO searchDocumentByKeyword(String keyword);

    ResponseVO searchDocumentCombined(String authorName, String affiliation, String meeting, String keyword);

    ResponseVO searchDocumentByTitle(String title);
}
