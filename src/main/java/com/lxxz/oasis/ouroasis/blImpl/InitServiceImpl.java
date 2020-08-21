package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.bl.InitService;
import com.lxxz.oasis.ouroasis.data.AuthorHeatEntityMapper;
import com.lxxz.oasis.ouroasis.data.AuthorRankDao;
import com.lxxz.oasis.ouroasis.data.AuthorTable1EntityMapper;
import com.lxxz.oasis.ouroasis.data.InitMapper;
import com.lxxz.oasis.ouroasis.po.Author;
import com.lxxz.oasis.ouroasis.po.AuthorHeatEntity;
import com.lxxz.oasis.ouroasis.po.AuthorRank;
import com.lxxz.oasis.ouroasis.po.AuthorTable1Entity;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
//import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * @author xyy
 */
@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private InitMapper initMapper;

    @Autowired
    private AuthorHeatEntityMapper authorHeatEntityMapper;

    @Autowired
    private AuthorTable1EntityMapper authorTable1EntityMapper;

    @Autowired
    private AuthorRankDao authorRankDao;

    @Override
    public void init() throws IOException {

        for (int i = 1; i < 3; i++) {
            initTables(String.valueOf(i));
        }
        //初始化之后，虽然在IDEA插件中只显示200行或者400行，但在mysql workbench中是全的，所以只是插件不想显示所有

        //计算活跃度
        try{
            figureOutHeat();
            getAuthorRankTable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initTables(String goal) throws IOException {

        //起先默认数据是规范的，我错了，本应输入数字也可能是str，可能还有其他更多的不规范错误

        String[] sqlStr = new String[8];
        //author,count,date,paper,per,pub,search,term;
        Object[] info = new Object[29];
        Object[][] args = new Object[8][];

        //解析excel 并存入数据库
        ClassPathResource classPathResource = new ClassPathResource("templates/xls/" + goal + ".xls");
        //InputStream file = classPathResource.getInputStream();
        //String filePath = "src\\main\\resources\\templates\\xls\\"+goal+".xls";
        //File file = new File(filePath);

        //总共29row
        try {
            //InputStream inputStream = new FileInputStream(filePath);
            InputStream inputStream = classPathResource.getInputStream();

            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);

            Vector<String> authors = new Vector<String>();
            Vector<String> affs = new Vector<String>();
            Vector<String> keyWords = new Vector<String>();
            Vector<String> terms = new Vector<String>();
            Vector<String> INSP = new Vector<String>();
            Vector<String> INSPNON = new Vector<String>();
            String[] temp1 = {"1"};
            String[] temp2 = {"2"};

            for (int i = 1; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    info[j] = "";
                    info[j] = cell.getContents();
                }
                /*  //原本jdbc的一部分，先留着
                //title authors aff key
                Object[] args0 = {info[0],info[1],info[2],info[16]};

                //art ref   title
                Object[] args1 = {info[22],info[23],info[0]};

                //title onl iss mee     iss mee onl  title
                Object[] args2 = {info[26],info[27],info[25],info[0]};26 27 25 0

                //title vol issue start end abstract        abs title end iss vol start
                Object[] args3 = {info[10],info[0],info[9],info[7],info[6],info[8]}; 10 0 9 7 6 8

                //title  ISSN   ISBNs license       title   license ISBNs   ISSN
                Object[] args4 = {info[0],info[25],info[23],info[22]};

                //title pub_title year publisher    doc_ident        ident doctitle title   year publier
                Object[] args5 = {info[29],info[0],info[3],info[5],info[28]};29 0 3 5 28

                //title date dol fund pdf INSP INSPNON          doct    INSP INSPNON    date    dol fund  pdf
                Object[] args6 = {info[0],info[18],info[19],info[4],info[13],info[14],info[15]};    0  18 19 4 13 14 15

                //title terms,mesh,IEEE     IEEE    doct    mesh    terms
                Object[] args7 = {info[17],info[0],info[21],info[20]};17 0 21 20
*/
                info[21] = info[21] == "" ? "0" : info[21];
                info[22] = info[22] == "" ? "0" : info[22];
                info[9] = info[9] == "" ? "0" : info[9];
                info[8] = info[8] == "" ? "0" : info[8];
                info[5] = info[5] == "" ? "0" : info[5];

                for (int m = 0; m < 29; m++) {
                    if (info[m] == null) {
                        info[m] = "";
                    }
                }
                if(info[0]==""||info[1]==""||info[2]==""){
                    continue;
                }
                //检测是否有不规范输入，有则抛弃整条记录
                //检测1：是否有本该是数字的，表格内是str

                if (!Pattern.matches("\\d+", info[21].toString())) {
                    continue;
                }
                if (!Pattern.matches("\\d+", info[22].toString())) {
                    continue;
                }
                if (!Pattern.matches("\\d+", info[9].toString())) {
                    continue;
                }
                if (!Pattern.matches("\\d+", info[8].toString())) {
                    continue;
                }
                if (!Pattern.matches("\\d+", info[5].toString())) {
                    continue;
                }

                //将作者姓名除去空格（所有空格）
                if (info[1].toString().length() != 0) {
                    info[1] = info[1].toString().replaceAll(" ","");
                }

                //插入d表
                authors.clear();
                affs.clear();
                keyWords.clear();
                terms.clear();
                INSP.clear();
                INSPNON.clear();
                //1 author  aff  docTitle
                //   1      2       0
                if (info[1].toString().indexOf(';') != -1) {
                    //此文章有多个作者
                    temp1 = info[1].toString().split(";");
                    temp2 = info[2].toString().split(";");
                    if (temp1.length != temp2.length) {
                        continue;
                    }

                }

                //插入t表,本应该先插入t再插t，但是可能会出现作者和aff数量不匹配的情况，所以把d的一部分移到上面
//                if(info[1].toString()==""){continue;}
//                AuthorTable1Entity authorTable1Entity = AuthorTable1Entity.builder()
//                        .docTitle(info[0].toString())
//                        .authors(info[1].toString())
//                        .authorsAff(info[2].toString())
//                        .key(info[16].toString())
//                        .build();
//                authorTable1EntityMapper.insertSelective(authorTable1Entity);
                initMapper.insertAuthor(info[0].toString(), info[1].toString(), info[2].toString(), info[16].toString());

//                TCount tCount = TCount.builder()
//                        .countArt(Integer.valueOf(info[21].toString()))
//                        .countRef(Integer.valueOf(info[22].toString()))
//                        .docTitle( info[0].toString())
//                        .build();
//                tCountDao.insertSelective(tCount);
                initMapper.insertCount(Integer.valueOf(info[21].toString()), Integer.valueOf(info[22].toString()), info[0].toString());

                initMapper.insertData(info[25].toString(), info[26].toString(), info[24].toString(), info[0].toString());
                initMapper.insertPaper(info[10].toString(), info[0].toString(), Integer.valueOf(info[9].toString()), info[7].toString(), info[6].toString(), Integer.valueOf(info[8].toString()));

                initMapper.insertPer(info[0].toString(), info[23].toString(), info[12].toString(), info[11].toString());

                initMapper.insertPub(info[28].toString(), info[0].toString(), info[3].toString(), Integer.valueOf(info[5].toString()), info[28].toString());
                initMapper.insertSearch(info[0].toString(), info[18].toString(), info[19].toString(), info[4].toString(), info[13].toString(), info[14].toString(), info[15].toString());
                initMapper.insertTerm(info[17].toString(), info[0].toString(), info[20].toString());

                //续，插入d表
                if (info[1].toString().indexOf(';') != -1) {
                    for (int temp1count = 0; temp1count < temp1.length; temp1count++) {
                        //           System.out.println(temp1[temp1count]+temp2[temp1count]+info[0].toString());
                        if(temp2[temp1count].trim()==null||temp2[temp1count].trim()==""){
                            initMapper.insertDAuthor(temp1[temp1count].trim(), "NA", info[0].toString(), temp1count + 1);
                        }else{
                            initMapper.insertDAuthor(temp1[temp1count].trim(), temp2[temp1count].trim(), info[0].toString(), temp1count + 1);
                        }
                    }
                } else {
                    //只有一个 或者没有，即使是null 之前也已经修改为“”，因此可以放心插入
                    initMapper.insertDAuthor(info[1].toString(), info[2].toString(), info[0].toString(), 1);

                }
                //2 插入docTitle INSP     0       18
                if (info[18].toString().indexOf(';') != -1) {
                    //有多个INSP
                    temp1 = info[18].toString().split(";");
                    for (int x = 0; x < temp1.length; x++) {
                        initMapper.insertDInsp(info[0].toString(), temp1[x]);
                    }
                } else {
                    initMapper.insertDInsp(info[0].toString(), info[18].toString());
                }
                //3 插入docTitle INSPNON     0       19
                if (info[19].toString().indexOf(';') != -1) {
                    //有多个INSPNON
                    temp1 = info[19].toString().split(";");
                    for (int x = 0; x < temp1.length; x++) {
                        initMapper.insertDInspec(info[0].toString(), temp1[x]);
                    }
                } else {
                    initMapper.insertDInspec(info[0].toString(), info[19].toString());
                }
                //4 插入key  docTitle    16    0
                if (info[16].toString().indexOf(';') != -1) {
                    //有多个keyWords
                    temp1 = info[16].toString().split(";");
                    for (int x = 0; x < temp1.length; x++) {
                        initMapper.insertDKey(temp1[x].toLowerCase(), info[0].toString());
                    }
                } else {
                    initMapper.insertDKey(info[16].toString(), info[0].toString());
                }

                //5 插入term  docTitle    17    0
                if (info[17].toString().indexOf(';') != -1) {
                    //有多个term
                    temp1 = info[17].toString().split(";");
                    for (int x = 0; x < temp1.length; x++) {
                        initMapper.insertDTerm(temp1[x], info[0].toString());
                    }
                } else {
                    initMapper.insertDTerm(info[17].toString(), info[0].toString());
                }

            }
            workbook.close();

        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void figureOutHeat() {
        calAuthorHeat();
        calAffHeat();
        calDirectionHeat();
    }

    @Override
    public void getAuthorRankTable(){

        List<String> authors = authorRankDao.selectAllAuthors();
        authors.stream().forEach(
                author->{
                    int pubNum = authorRankDao.selectAuthorPubCount(author);
                    List<String> docs = authorRankDao.selectDocsByAuthor(author);
                    int citeNum = 0;
                    for(String doc:docs){
                        citeNum+=authorRankDao.selectCiteCountByDoc(doc);
                    }
                    AuthorRank authorRank = new AuthorRank();
                    authorRank.setAuthor(author);
                    authorRank.setPubCount(pubNum);
                    authorRank.setCiteCount(citeNum);
                    authorRankDao.insert(authorRank);
                }
        );
    }

    public void calAuthorHeat(){
        List<AuthorHeatEntity> authorHeatEntityList = initMapper.selectAllAuthors();
        authorHeatEntityList.stream().forEach(
                authorHeatEntity -> {
                    if(authorHeatEntity.getAff()==null||authorHeatEntity.getAff()==""){
                        authorHeatEntity.setAff("NA");
                    }
                    //去重
                    authorHeatEntityMapper.insertIgnoreSelective(authorHeatEntity);
                }
        );
        authorHeatEntityList = authorHeatEntityMapper.selectAll();
        authorHeatEntityList.stream().forEach(
                authorHeatEntity -> {
                    authorHeatEntity.setHeat(calAuthorHotDegree(authorHeatEntity));
                    authorHeatEntityMapper.updateByPrimaryKeySelective(authorHeatEntity);
                }
        );
    }
    public void calAffHeat(){
        //2. 计算机构活跃度
        List<String> affs, affs1;
        affs = initMapper.getAffNames();
        affs1 = new ArrayList<>();
        for (String str : affs) {
            if (str.length() != 0 && str != "NA") {
                affs1.add(str.trim());
            }
        }
        for (String str : affs1) {
            initMapper.insertHAff(str, calAffHeat(str));
        }
    }

    public void calDirectionHeat(){
        List<String> dirs, dirs1;
        dirs = initMapper.getDirectionsHeat();
        dirs1 = new ArrayList<>();
        for (String str : dirs) {
            if (str.length() != 0) {
                dirs1.add(str.trim());
            }
        }
        for (String str : dirs1) {
            initMapper.insertHKey(str, calDirHeat(str));
        }
    }

    private double calDirHeat(String direction) {

        List<String> titles = initMapper.getTitlesByKey(direction);
        double heat = 0;
        for (String title : titles) {
            heat += initMapper.getCiteNumByTitle(title);
        }

        return heat;
    }

    private double calAuthorHotDegree(AuthorHeatEntity authorHeatEntity) {
        String authorName = authorHeatEntity.getAuthor();
        String aff = authorHeatEntity.getAff();

        List docs;
        docs = initMapper.getTitlesByAuthor(authorName,aff);//存储根据作者名+国籍得到的文章题目
        String docAuthors;//一篇文章的作者（们）
        int authorPlace, docAuthorAmount, temp1, citeNum;
        double rate, hotDegree = 0;
        //该作者是第几作   文章的作者数

        for (int m = 0; m < docs.size(); m++) {
            authorPlace = 1;//一开始就是第一个作者，每遇到一个“；”就++
            docAuthors = initMapper.getAuthorsByTitle(docs.get(m).toString());
            temp1 = docAuthors.indexOf(authorName);
            for (int x = 0; x < temp1; x++) {
                if (docAuthors.charAt(x) == ';') {
                    authorPlace++;
                }
            }
            docAuthorAmount = getAuthorAmounts(docs.get(m).toString());
            rate = getRate(authorPlace, docAuthorAmount);
            citeNum = initMapper.getCiteNumByTitle(docs.get(m).toString());
            //返回的是Article Citation Count+Reference Count，暂且这样，好像一个是被引用数，一个是自身引用外部的数量，有疑问
            hotDegree += calHotNow(citeNum, rate);
        }
        return hotDegree;
    }

    //计算该文章有几个作者
    private int getAuthorAmounts(String docName) {
        int amount = 1;
        for (int i = 0; i < docName.length(); i++) {
            if (docName.charAt(i) == ';') {
                amount++;
            }
        }
        return amount;
    }

    //计算该作者在此文章的占比
    private double getRate(int authorPlace, int docAuthorAmount) {
        int ans;
        switch (docAuthorAmount) {
            case 1:
                return 1;
            case 2:
                if (authorPlace == 1) {
                    return 0.7;
                } else {
                    return 0.3;
                }
            case 3:
                if (authorPlace == 1) {
                    return 0.5;
                } else if (authorPlace == 2) {
                    return 0.3;
                } else {
                    return 0.2;
                }
            case 4:
                if (authorPlace == 1) {
                    return 0.5;
                } else if (authorPlace == 2) {
                    return 0.25;
                } else if (authorPlace == 3) {
                    return 0.15;
                } else {
                    return 0.1;
                }
            default:
                if (authorPlace == 1) {
                    return 0.5;
                } else if (authorPlace == 2) {
                    return 0.25;
                } else if (authorPlace == 3) {
                    return 0.15;
                } else {
                    return 0.1 / (docAuthorAmount - 3);
                }
        }

    }

    //根据文章引用数和作者几作计算活跃度
    private double calHotNow(int citeNum, double rate) {
        return rate * (citeNum + 38);
    }

    //计算该研究机构的活跃度
    //机构活跃度即作者 活跃度之和，具体比例可以之后再调
    private double calAffHeat(String aff) {

        List<String> affAtuhors = initMapper.getAuthorsByAff(aff);
        double heat = 0;
        for (String author : affAtuhors) {
            heat += initMapper.getAuthorHeat(author,aff);
        }

        return heat;
    }
}



/*
 * 占比系数
 * 1     100
 * 2     70  30
 * 3     50  30  20
 * 4     50  25  15  10
 * 5     50  25  15  均分
 * */

/*
0	Document Title
1	Authors
2	Author Affiliations
3	Publication Title
4	Date Added To Xplore
5	Publication Year
6	Volume
7	Issue
8	Start Page
9	End Page
10	Abstract
11	ISSN
12	ISBNs
13	DOI
14	Funding Information
15	PDF Link
16	Author Keywords
17	IEEE Terms
18	INSPEC Controlled Terms
19	INSPEC Non-Controlled
20	Mesh_Terms

21	Article Citation Count
22	Reference Count

23	License
24	Online Date
25	Issue Date
26	Meeting Date
27	Publisher
28	Document Identifier


作者	Authors		Author Affiliations	Author Keywords
发表	Publication Title	Publication Year	Publisher		Document Identifier
论文	Volume	Issue	Start Page		End Page		Abstract
期刊	ISSN	ISBNs
检索	Date Added To Xplore	DOI	Funding Information		PDF Link	   INSPEC Controlled Terms	INSPEC Non-Controlled

术语	Terms	Mesh_Terms	IEEE Terms
引用计数	Article Citation Count		Reference Count
数据	Online Date	Issue Date		Meeting Date

 */