package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.Find;
import cn.likole.TravelBetter.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/3/17.
 */
@Repository
public class FindDao extends HibernateDaoSupport {

    FindDao(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    public List<Find> getList(int offset,int num){
        return getHibernateTemplate().execute(new HibernateCallback<List<Find>>() {
            @Override
            public List<Find> doInHibernate(Session session) throws HibernateException {
                return session.createQuery("from Find order by time desc ").setFirstResult(offset).setMaxResults(num).list();
            }
        });
    }

    public Find getByFid(int fid){
        List<Find> list= (List<Find>) getHibernateTemplate().find("from Find where fid=?",fid);
        if(list.size()>0) return list.get(0);
        return null;
    }

    public List<Find> getByUser(User user){
        return (List<Find>) getHibernateTemplate().find("from Find where user=?",user);
    }

    public void save(Find find){
        getHibernateTemplate().save(find);
    }

    public void update(Find find) {
        getHibernateTemplate().update(find);
    }
}
