package cn.likole.TravelBetter.service;

import cn.likole.TravelBetter.dao.HotelDao;
import cn.likole.TravelBetter.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by likole on 8/5/17.
 */
@Service
@Transactional
public class HotelService {

    @Autowired
    HotelDao hotelDao;

    public List<Hotel> getList(int offset, int num) {
        return hotelDao.getList(offset, num);
    }
}
