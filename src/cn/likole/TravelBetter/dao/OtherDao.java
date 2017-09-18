package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.Other;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 9/18/17.
 */
@Repository
public class OtherDao extends HibernateDaoSupport {

    @Autowired
    public OtherDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<Other> getByCategory(int i) {
        return (List<Other>) getHibernateTemplate().find("from Other where c=?", i);
    }

}
