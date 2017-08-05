package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/5/17.
 */
@Repository
public class HotelDao extends HibernateDaoSupport {
    HotelDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<Hotel> getList(int offfset, int num) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Hotel>>() {
            @Override
            public List<Hotel> doInHibernate(Session session) throws HibernateException {
                return session.createQuery("from Hotel order by score desc ").setFirstResult(offfset).setMaxResults(num).list();
            }
        });
    }

}
