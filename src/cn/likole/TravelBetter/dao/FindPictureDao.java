package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.FindPicture;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/4/17.
 */
@Repository
public class FindPictureDao extends HibernateDaoSupport {
    FindPictureDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public void save(FindPicture findPicture) {
        getHibernateTemplate().save(findPicture);
    }


    public FindPicture getByPid(int pid) {
        List<FindPicture> list = (List<FindPicture>) getHibernateTemplate().find("from FindPicture where pid =?", pid);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }


    public void deleteByPid(int pid) {
        getHibernateTemplate().bulkUpdate("delete from FindPicture where pid=?", pid);
    }
}
