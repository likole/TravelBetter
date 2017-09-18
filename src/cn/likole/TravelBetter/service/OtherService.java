package cn.likole.TravelBetter.service;

import cn.likole.TravelBetter.dao.OtherDao;
import cn.likole.TravelBetter.entity.Other;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by likole on 9/18/17.
 */
@Service
@Transactional
public class OtherService {

    @Autowired
    OtherDao otherDao;

    public List<Other> getByCategory(int i) {
        return otherDao.getByCategory(i);
    }

}
