package cn.likole.TravelBetter.service;

import cn.likole.TravelBetter.dao.FeatureSpotDao;
import cn.likole.TravelBetter.entity.FeatureSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by likole on 8/6/17.
 */
@Service
@Transactional
public class FeatureSpotService {

    @Autowired
    FeatureSpotDao featureSpotDao;

    public List<FeatureSpot> getList(int offset, int num) {
        return featureSpotDao.getList(offset, num);
    }
}
