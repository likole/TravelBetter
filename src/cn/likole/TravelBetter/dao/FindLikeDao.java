package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.FindLike;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by likole on 8/3/17.
 */
@Repository
public class FindLikeDao extends HibernateDaoSupport{
    FindLikeDao(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    public boolean exist(int fid,int uid){
        return getHibernateTemplate().find("from FindLike where fid=? and uid=?",fid,uid).size()>0;
    }

    public void save(FindLike findLike){
        getHibernateTemplate().save(findLike);
    }
}
