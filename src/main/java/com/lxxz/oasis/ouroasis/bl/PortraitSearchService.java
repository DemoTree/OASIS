package com.lxxz.oasis.ouroasis.bl;

import com.lxxz.oasis.ouroasis.vo.ResponseVO;

public interface PortraitSearchService {

    ResponseVO searchAuthorPortraits(String name);

    ResponseVO searchAffiliationPortraits(String aff);

    ResponseVO searchDirectionPortraits(String direction);

    ResponseVO searchMeetingPortraits(String meeting);

    ResponseVO searchPortraitByAuthor(String authorName, String aff);

    ResponseVO searchPortraitByAffiliation(String affiliation);

    ResponseVO searchPortraitByDirection(String direction);

    ResponseVO searchPortraitByMeeting(String meeting);
}
