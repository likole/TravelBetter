package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.FeatureSpot;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/6/17.
 */
@Repository
public class FeatureSpotDao extends HibernateDaoSupport {
    FeatureSpotDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<FeatureSpot> getList(int offset, int num) {
        return getHibernateTemplate().execute(new HibernateCallback<List<FeatureSpot>>() {
            @Override
            public List<FeatureSpot> doInHibernate(Session session) throws HibernateException {
                return session.createQuery("from FeatureSpot").setFirstResult(offset).setMaxResults(num).list();
            }
        });
    }
}
