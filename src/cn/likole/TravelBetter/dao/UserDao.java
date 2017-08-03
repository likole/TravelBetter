package cn.likole.TravelBetter.dao;

import cn.likole.TravelBetter.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 8/2/17.
 */
@Repository
public class UserDao extends HibernateDaoSupport {

    UserDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public boolean exist(String usernameOrEmail){
        return getHibernateTemplate().find("from User where username=? or email=?",usernameOrEmail,usernameOrEmail).size()>0;
    }

    public void save(User user) {
        getHibernateTemplate().save(user);
    }

    public User getByUid(int uid) {
        List<User> list = (List<User>) getHibernateTemplate().find("from User where uid=?", uid);
        if (list.size() > 0) return list.get(0);
        return null;
    }

    public User getByToken(String token) {
        List<User> list = (List<User>) getHibernateTemplate().find("from User where token=?", token);
        if (list.size() > 0) return list.get(0);
        return null;
    }

    public User getByUsernameOrEmail(String usernameOrEmail) {
        List<User> list = (List<User>) getHibernateTemplate().find("from User where username=? or email=?", usernameOrEmail,usernameOrEmail);
        if (list.size() > 0) return list.get(0);
        return null;
    }
}
