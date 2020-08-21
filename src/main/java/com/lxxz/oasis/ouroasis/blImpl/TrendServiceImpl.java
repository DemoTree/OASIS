package com.lxxz.oasis.ouroasis.blImpl;

import com.lxxz.oasis.ouroasis.bl.TrendService;
import com.lxxz.oasis.ouroasis.data.TrendMapper;
import com.lxxz.oasis.ouroasis.po.Trend;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import com.lxxz.oasis.ouroasis.vo.TrendForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrendServiceImpl implements TrendService {

    @Autowired
    TrendMapper trendMapper;

    @Override
    public ResponseVO getTrend() {
        try{
            List<Integer> years=trendMapper.selectYears();
            Trend trend=new Trend();
            trend.setTrendForms(new HashMap<Integer, List<TrendForm>>());
            for(int i=0;i<years.size();i++){
                trend.getTrendForms().put(years.get(i),trendMapper.selectTopTenDirectionOfEachYear(years.get(i)));
            }
            return ResponseVO.buildSuccess(trend);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
