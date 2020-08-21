package com.lxxz.oasis.ouroasis.data;

import com.lxxz.oasis.ouroasis.po.AffiliationPortrait;
import com.lxxz.oasis.ouroasis.po.AuthorPortrait;
import com.lxxz.oasis.ouroasis.po.DirectionPortrait;
import com.lxxz.oasis.ouroasis.po.MeetingPortrait;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PortraitSearchMapper {

    int selectAuthorPotraitCount(@Param("authorName") String authorName);

    List<AuthorPortrait> selectPortraitsByAuthor(@Param("authorName") String authorName);

    List<AffiliationPortrait> selectPortraitsByAffiliation(@Param("affiliation") String affiliation);

    List<DirectionPortrait> selectPortraitsByDirection(@Param("direction") String direction);

    List<MeetingPortrait> selectPortraitsByMeeting(@Param("meeting") String meeting);

    AuthorPortrait selectPortraitByAuthorAndAff(@Param("authorName") String authorName, @Param("aff") String aff);

    AffiliationPortrait selectPortraitByAffiliation(@Param("affiliation") String affiliation);

    DirectionPortrait selectPortraitByDirection(@Param("direction") String direction);

    MeetingPortrait selectPortraitByMeeting(@Param("meeting") String meeting);

    List<String> selectAllMeetings();
}
