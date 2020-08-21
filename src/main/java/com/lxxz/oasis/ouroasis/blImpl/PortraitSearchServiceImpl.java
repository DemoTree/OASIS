package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.bl.PortraitSearchService;
import com.lxxz.oasis.ouroasis.data.PortraitSearchMapper;
import com.lxxz.oasis.ouroasis.po.AffiliationPortrait;
import com.lxxz.oasis.ouroasis.po.AuthorPortrait;
import com.lxxz.oasis.ouroasis.po.DirectionPortrait;
import com.lxxz.oasis.ouroasis.po.MeetingPortrait;
import com.lxxz.oasis.ouroasis.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortraitSearchServiceImpl implements PortraitSearchService {
    @Autowired
    private PortraitSearchMapper portraitSearchMapper;

    @Override
    public ResponseVO searchAuthorPortraits(String name){
        List<AuthorPortrait> result = portraitSearchMapper.selectPortraitsByAuthor(name);

//        RowBounds rowBounds = CommonUtil.convertRowBounds(authorPortraitRequest.getPageNum(),authorPortraitRequest.getPageSize());
//        result.setCount(portraitSearchMapper.selectAuthorPotraitCount(authorPortraitRequest.getName()));
//        result.setRows(portraitSearchMapper.selectPortraitsByAuthor(authorPortraitRequest.getName(),rowBounds));
//        result.setComputeTime(LocalDateTimeUtils.formatLocalDateTime(LocalDateTime.now()));

        return ResponseVO.buildSuccess(result);
    }
    @Override
    public ResponseVO searchAffiliationPortraits(String aff){
        List<AffiliationPortrait> result = portraitSearchMapper.selectPortraitsByAffiliation(aff);

//        RowBounds rowBounds = CommonUtil.convertRowBounds(authorPortraitRequest.getPageNum(),authorPortraitRequest.getPageSize());
//        result.setCount(portraitSearchMapper.selectAuthorPotraitCount(authorPortraitRequest.getName()));
//        result.setRows(portraitSearchMapper.selectPortraitsByAuthor(authorPortraitRequest.getName(),rowBounds));
//        result.setComputeTime(LocalDateTimeUtils.formatLocalDateTime(LocalDateTime.now()));

        return ResponseVO.buildSuccess(result);
    }
    @Override
    public ResponseVO searchDirectionPortraits(String direction){
        List<DirectionPortrait> result = portraitSearchMapper.selectPortraitsByDirection(direction);

//        RowBounds rowBounds = CommonUtil.convertRowBounds(authorPortraitRequest.getPageNum(),authorPortraitRequest.getPageSize());
//        result.setCount(portraitSearchMapper.selectAuthorPotraitCount(authorPortraitRequest.getName()));
//        result.setRows(portraitSearchMapper.selectPortraitsByAuthor(authorPortraitRequest.getName(),rowBounds));
//        result.setComputeTime(LocalDateTimeUtils.formatLocalDateTime(LocalDateTime.now()));

        return ResponseVO.buildSuccess(result);
    }
    @Override
    public ResponseVO searchMeetingPortraits(String meeting){
        List<MeetingPortrait> result = portraitSearchMapper.selectPortraitsByMeeting(meeting);
        System.out.println(result.size());

//        RowBounds rowBounds = CommonUtil.convertRowBounds(authorPortraitRequest.getPageNum(),authorPortraitRequest.getPageSize());
//        result.setCount(portraitSearchMapper.selectAuthorPotraitCount(authorPortraitRequest.getName()));
//        result.setRows(portraitSearchMapper.selectPortraitsByAuthor(authorPortraitRequest.getName(),rowBounds));
//        result.setComputeTime(LocalDateTimeUtils.formatLocalDateTime(LocalDateTime.now()));

        return ResponseVO.buildSuccess(result);
    }

    //通过作者名得到作者画像
    @Override
    public ResponseVO searchPortraitByAuthor(String authorName, String aff) {
        try {
            AuthorPortrait authorPortrait = portraitSearchMapper.selectPortraitByAuthorAndAff(authorName,aff);

            int documentCount = authorPortrait.getDocuments().size();
            //计算所有论文的引用数
            int referenceCount = 0;
            for (int i = 0; i < documentCount; i++) {
                referenceCount += authorPortrait.getDocuments().get(i).getReferenceCount();
            }

            List<String> documents = new ArrayList<String>();
            Map<String, Integer> directions = new HashMap();
            Map<String, Integer> relatedAuthors = new HashMap<>();
            List<String> meetings = new ArrayList<String>();
            //如果论文数大于5，就把引用数前五的作为代表作，否则就全部列出;把这些论文的合作作者作为相关作者；代表性研究方向同理；
            int numDoc = documentCount;
//            if(documentCount<5) {
//                numDoc=documentCount;
//            }
            for (int i = 0; i < numDoc; i++) {
                documents.add(authorPortrait.getDocuments().get(i).getDocumentTitle());
            }
            //计算作者所有文章中每个keyword出现的次数，然后取前五作为研究方向
            //相关作者同理
            for (int i = 0; i < documentCount; i++) {
                int keywordCount = authorPortrait.getDocuments().get(i).getKeywords().size();
                for (int j = 0; j < keywordCount; j++) {
                    String keyword = authorPortrait.getDocuments().get(i).getKeywords().get(j);
                    if (!keyword.equals("")) {
                        if (!directions.containsKey(keyword))
                            directions.put(keyword, 1);
                        else {
                            int weight = directions.get(keyword);
                            weight += 1;
                            directions.put(keyword, weight);
                        }
                    }
                }
                int authorCount = authorPortrait.getDocuments().get(i).getAuthors().size();
                for (int j = 0; j < authorCount; j++) {
                    String relatedAuthorName = authorPortrait.getDocuments().get(i).getAuthors().get(j).getName();
                    if (!relatedAuthorName.equals(authorName)) {
                        if (!relatedAuthors.containsKey(relatedAuthorName))
                            relatedAuthors.put(relatedAuthorName, 1);
                        else {
                            int weight = relatedAuthors.get(relatedAuthorName);
                            weight += 1;
                            relatedAuthors.put(relatedAuthorName, weight);
                        }
                    }
                }
            }
            directions = sortByValueDescending(directions);

            //把relatedAuthor排序后取前五（或者小于五），存为list
            relatedAuthors = sortByValueDescending(relatedAuthors);
            List<String> relatedAuthorList = new ArrayList<String>();
            int numRelatedAuthor = relatedAuthors.size();
//            if(relatedAuthors.size()<5)
//                numRelatedAuthor=relatedAuthors.size();
            int index = 0;
            for (Map.Entry<String, Integer> entry : relatedAuthors.entrySet()) {
                relatedAuthorList.add(entry.getKey());
                index++;
                if (index >= numRelatedAuthor)
                    break;
            }

            //录入所有论文对应的会议名，去重后导入
            for (int i = 0; i < documentCount; i++) {
                String pubTitle = authorPortrait.getDocuments().get(i).getPublicationTitle();
                if (!meetings.contains(pubTitle)) {
                    meetings.add(pubTitle);
                }
            }

            AuthorPortraitVO authorPortraitVO = AuthorPortraitVO.builder()
                    .name(authorPortrait.getName())
                    .aff(authorPortrait.getAffiliations())
                    .heat(authorPortrait.getHeat())
                    .documentCount(documentCount)
                    .referenceCount(referenceCount)
                    .documents(documents)
                    .relatedAuthors(relatedAuthorList)
                    .directions(directions)
                    .meetings(meetings)
                    .build();



            return ResponseVO.buildSuccess(authorPortraitVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchPortraitByAffiliation(String affiliation) {
        try {
            AffiliationPortrait affiliationPortrait = portraitSearchMapper.selectPortraitByAffiliation(affiliation);

            int authorCount = affiliationPortrait.getAuthorPortraits().size();
            //计算热度和
            double heat = 0;
            for (int i = 0; i < authorCount; i++) {
                heat = heat + affiliationPortrait.getAuthorPortraits().get(i).getHeat();
            }

            //选出热度前5的author，如果总数小于5，则全部选取
            List<String> authors = new ArrayList<String>();
            //对author按热度排序
            for (int i = 0; i < authorCount; i++) {
                for (int j = 0; j < authorCount - i - 1; j++) {
                    double heat1 = affiliationPortrait.getAuthorPortraits().get(j).getHeat();
                    double heat2 = affiliationPortrait.getAuthorPortraits().get(j + 1).getHeat();
                    if (heat1 < heat2) {
                        AuthorPortrait temp = affiliationPortrait.getAuthorPortraits().get(j);
                        affiliationPortrait.getAuthorPortraits().set(j, affiliationPortrait.getAuthorPortraits().get(j + 1));
                        affiliationPortrait.getAuthorPortraits().set(j + 1, temp);
                    }
                }
            }
            int numAut = authorCount;//代表作者数，5或者作者数量
//            if(authorCount<5){
//                numAut=authorCount;
//            }
            for (int i = 0; i < numAut; i++) {
                authors.add(affiliationPortrait.getAuthorPortraits().get(i).getName());
            }

            //获取引用数前5的论文
            int documentCount = affiliationPortrait.getDocuments().size();
            List<String> documents = new ArrayList<String>();
            int numDoc = documentCount;//代表论文数，5或者论文数
//            if(documentCount<5){
//                numDoc=documentCount;
//            }
            for (int i = 0; i < numDoc; i++) {
                documents.add(affiliationPortrait.getDocuments().get(i).getDocumentTitle());
            }


            //计算会议所有文章中每个keyword出现的次数，然后取前五作为研究方向
            Map<String, Integer> directions = new HashMap<>();
            for (int i = 0; i < documentCount; i++) {
                int keywordCount = affiliationPortrait.getDocuments().get(i).getKeywords().size();
                for (int j = 0; j < keywordCount; j++) {
                    String keyword = affiliationPortrait.getDocuments().get(i).getKeywords().get(j);
                    if (!keyword.equals("")) {
                        if (!directions.containsKey(keyword))
                            directions.put(keyword, 1);
                        else {
                            int weight = directions.get(keyword);
                            weight += 1;
                            directions.put(keyword, weight);
                        }
                    }
                }
            }
            directions = sortByValueDescending(directions);

            //录入所有论文对应的会议名，去重后导入;计算引用数之和
            int referenceCount = 0;
            List<String> meetings = new ArrayList<String>();
            for (int i = 0; i < documentCount; i++) {
                String pubTitle = affiliationPortrait.getDocuments().get(i).getPublicationTitle();
                if (!meetings.contains(pubTitle)) {
                    meetings.add(pubTitle);
                }
                referenceCount = referenceCount + affiliationPortrait.getDocuments().get(i).getReferenceCount();
            }

            AffiliationPortraitVO affiliationPortraitVO = AffiliationPortraitVO.builder()
                    .name(affiliationPortrait.getName())
                    .heat(heat)
                    .authorCount(authorCount)
                    .authors(authors)
                    .documentCount(documentCount)
                    .referenceCount(referenceCount)
                    .documents(documents)
                    .directions(directions)
                    .meetings(meetings)
                    .build();

            return ResponseVO.buildSuccess(affiliationPortraitVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO searchPortraitByDirection(String direction) {
        try {
            DirectionPortrait directionPortrait = portraitSearchMapper.selectPortraitByDirection(direction);

            //论文部分计算
            int documentCount = directionPortrait.getDocuments().size();
            int referenceCount = 0;
            double heat = 0;
            List<String> meetings = new ArrayList<String>();
            for (int i = 0; i < documentCount; i++) {
                referenceCount = referenceCount + directionPortrait.getDocuments().get(i).getReferenceCount();
                heat = heat + (directionPortrait.getDocuments().get(i).getReferenceCount() + 38);
                String meeting = directionPortrait.getDocuments().get(i).getPublicationTitle();
                if (!meetings.contains(meeting))
                    meetings.add(meeting);
            }
            List<String> documents = new ArrayList<String>();
            int numDoc = documentCount;//代表论文数量，5或论文数量
//            if(documentCount<5)
//                numDoc=documentCount;
            for (int i = 0; i < numDoc; i++) {
                documents.add(directionPortrait.getDocuments().get(i).getDocumentTitle());
            }

            //作者部分计算
            int authorCount = directionPortrait.getAuthorPortraits().size();
            List<String> authors = new ArrayList<String>();
            //对作者按热度排序
            for (int i = 0; i < authorCount; i++) {
                for (int j = 0; j < authorCount - i - 1; j++) {
                    double heat1 = directionPortrait.getAuthorPortraits().get(j).getHeat();
                    double heat2 = directionPortrait.getAuthorPortraits().get(j + 1).getHeat();
                    if (heat1 < heat2) {
                        AuthorPortrait temp = directionPortrait.getAuthorPortraits().get(j);
                        directionPortrait.getAuthorPortraits().set(j, directionPortrait.getAuthorPortraits().get(j + 1));
                        directionPortrait.getAuthorPortraits().set(j + 1, temp);
                    }
                }
            }
            int numAut = authorCount;
//            if(authorCount<5)
//                numAut=authorCount;
            for (int i = 0; i < numAut; i++) {
                authors.add(directionPortrait.getAuthorPortraits().get(i).getName());
            }

            List<String> affiliations = new ArrayList<String>();
            int affiliationCount = directionPortrait.getAffiliationPortraits().size();
            for (int i = 0; i < affiliationCount; i++) {
                double affHeat = 0;
                for (int j = 0; j < directionPortrait.getAffiliationPortraits().get(i).getAuthorPortraits().size(); j++) {
                    affHeat = affHeat + directionPortrait.getAffiliationPortraits().get(i).getAuthorPortraits().get(j).getHeat();
                }
                directionPortrait.getAffiliationPortraits().get(i).setHeat(affHeat);
            }
            //对affiliation按热度排序
            for (int i = 0; i < affiliationCount; i++) {
                for (int j = 0; j < affiliationCount - i - 1; j++) {
                    double heat1 = directionPortrait.getAffiliationPortraits().get(j).getHeat();
                    double heat2 = directionPortrait.getAffiliationPortraits().get(j + 1).getHeat();
                    if (heat1 < heat2) {
                        AffiliationPortrait temp = directionPortrait.getAffiliationPortraits().get(j);
                        directionPortrait.getAffiliationPortraits().set(j, directionPortrait.getAffiliationPortraits().get(j + 1));
                        directionPortrait.getAffiliationPortraits().set(j + 1, temp);
                    }
                }
            }
            int numAff = affiliationCount;
//            if(affiliationCount<5)
//                numAff=affiliationCount;
            for (int i = 0; i < numAff; i++) {
                affiliations.add(directionPortrait.getAffiliationPortraits().get(i).getName());
            }

            DirectionPortraitVO directionPortraitVO = DirectionPortraitVO.builder()
                    .name(directionPortrait.getName())
                    .heat(heat)
                    .documentCount(documentCount)
                    .referenceCount(referenceCount)
                    .documents(documents)
                    .authorCount(authorCount)
                    .authors(authors)
                    .affiliations(affiliations)
                    .meetings(meetings)
                    .build();
            return ResponseVO.buildSuccess(directionPortraitVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchPortraitByMeeting(String meeting) {
        try {
            MeetingPortrait meetingPortrait = portraitSearchMapper.selectPortraitByMeeting(meeting);

            int authorCount = meetingPortrait.getAuthorPortraits().size();
            List<String> authors = new ArrayList<String>();
            //将authorPortrait按热度排序
            for (int i = 0; i < authorCount; i++) {
                for (int j = 0; j < authorCount - i - 1; j++) {
                    double heat1 = meetingPortrait.getAuthorPortraits().get(j).getHeat();
                    double heat2 = meetingPortrait.getAuthorPortraits().get(j + 1).getHeat();
                    if (heat1 < heat2) {
                        AuthorPortrait temp = meetingPortrait.getAuthorPortraits().get(j);
                        meetingPortrait.getAuthorPortraits().set(j, meetingPortrait.getAuthorPortraits().get(j + 1));
                        meetingPortrait.getAuthorPortraits().set(j + 1, temp);
                    }
                }
            }
            int numAut = authorCount;//输出的作者个数，5个或者更少
//            if(authorCount<5)
//                numAut=authorCount;
            for (int i = 0; i < numAut; i++) {
                authors.add(meetingPortrait.getAuthorPortraits().get(i).getName());
            }


            int documentCount = meetingPortrait.getDocuments().size();
            int referenceCount = 0;
            for (int i = 0; i < documentCount; i++) {
                referenceCount = referenceCount + meetingPortrait.getDocuments().get(i).getReferenceCount();
            }
            List<String> documents = new ArrayList<String>();
            int numDoc = documentCount;//输出的论文数，5个或者更少
//            if(documentCount<5)
//                numDoc=documentCount;
            for (int i = 0; i < numDoc; i++) {
                documents.add(meetingPortrait.getDocuments().get(i).getDocumentTitle());
            }


            //计算会议所有文章中每个keyword出现的次数，然后取前五作为研究方向
            Map<String, Integer> directions = new HashMap<>();
            for (int i = 0; i < documentCount; i++) {
                int keywordCount = meetingPortrait.getDocuments().get(i).getKeywords().size();
                for (int j = 0; j < keywordCount; j++) {
                    String keyword = meetingPortrait.getDocuments().get(i).getKeywords().get(j);
                    if (!keyword.equals("")) {
                        if (!directions.containsKey(keyword))
                            directions.put(keyword, 1);
                        else {
                            int weight = directions.get(keyword);
                            weight += 1;
                            directions.put(keyword, weight);
                        }
                    }
                }
            }
            directions = sortByValueDescending(directions);


            int affiliationCount = meetingPortrait.getAffiliationPortraits().size();
            List<String> affiliations = new ArrayList<String>();
            //统计各个机构在该会议中的热度
            for (int i = 0; i < affiliationCount; i++) {
                int authorCountOfEachAffiliation = meetingPortrait.getAffiliationPortraits().get(i).getAuthorPortraits().size();
                double affHeat = 0;
                for (int j = 0; j < authorCountOfEachAffiliation; j++) {
                    affHeat = affHeat + meetingPortrait.getAffiliationPortraits().get(i).getAuthorPortraits().get(j).getHeat();
                }
                meetingPortrait.getAffiliationPortraits().get(i).setHeat(affHeat);
            }
            //对机构按热度排序
            for (int i = 0; i < affiliationCount; i++) {
                for (int j = 0; j < affiliationCount - i - 1; j++) {
                    double heat1 = meetingPortrait.getAffiliationPortraits().get(j).getHeat();
                    double heat2 = meetingPortrait.getAffiliationPortraits().get(j + 1).getHeat();
                    if (heat1 < heat2) {
                        AffiliationPortrait temp = meetingPortrait.getAffiliationPortraits().get(j);
                        meetingPortrait.getAffiliationPortraits().set(j, meetingPortrait.getAffiliationPortraits().get(j + 1));
                        meetingPortrait.getAffiliationPortraits().set(j + 1, temp);
                    }
                }
            }
            int numAff = affiliationCount;
//            if(affiliationCount<5)
//                numAff=affiliationCount;
            for (int i = 0; i < numAff; i++) {
                affiliations.add(meetingPortrait.getAffiliationPortraits().get(i).getName());
            }

            //往届会议
            String publicationTitle = meetingPortrait.getName();
            int publicationYear = Integer.parseInt(publicationTitle.substring(0, 4));
            int meetingPlace = 0;
            if (publicationTitle.contains("ICSE"))
                meetingPlace = Integer.parseInt(publicationTitle.substring(14, 16));
            else
                meetingPlace = Integer.parseInt(publicationTitle.substring(5, 7));
            List<String> meetings = new ArrayList<String>();
            List<String> meetingList = portraitSearchMapper.selectAllMeetings();
            int minYear = Integer.parseInt(meetingList.get(0));//最小的会议年份
            int year = publicationYear;
            int place = meetingPlace;
            for (int i = 0; i < numAff; i++) {
//            for(int i=0;i<5;i++){
                year = year - 1;
                place = place - 1;
                if (year <= minYear)
                    break;
                String suffix = "th";
                if (place % 10 == 2)
                    suffix = "nd";
                else if (place % 10 == 1)
                    suffix = "st";
                String preMeeting = "";
                if (publicationTitle.contains("ICSE"))
                    preMeeting = year + publicationTitle.substring(4, 14) + place + suffix + publicationTitle.substring(18);
                else
                    preMeeting = year + " " + place + suffix + publicationTitle.substring(9);
                meetings.add(preMeeting);
            }

            MeetingPortraitVO meetingPortraitVO = MeetingPortraitVO.builder()
                    .name(meetingPortrait.getName())
                    .authorCount(authorCount)
                    .documentCount(documentCount)
                    .referenceCount(referenceCount)
                    .authors(authors)
                    .documents(documents)
                    .directions(directions)
                    .affiliations(affiliations)
                    .preMeetings(meetings)
                    .build();
            return ResponseVO.buildSuccess(meetingPortraitVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    //对map按value降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
