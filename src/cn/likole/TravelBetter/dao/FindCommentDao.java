package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.FindComment;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/4/17.
 */
@Repository
public class FindCommentDao extends HibernateDaoSupport {
    FindCommentDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public void save(FindComment findComment) {
        getHibernateTemplate().save(findComment);
    }

    public List<FindComment> getByFid(int fid) {
        return (List<FindComment>) getHibernateTemplate().find("from FindComment where fid=?", fid);
    }

    public FindComment getByCid(int cid) {
        List<FindComment> list = (List<FindComment>) getHibernateTemplate().find("from FindComment where cid=?", cid);
        if (list.size() > 0) return list.get(0);
        return null;
    }

    public void deleteByCid(int cid) {
        getHibernateTemplate().bulkUpdate("delete from FindComment where cid=?", cid);
    }

}
