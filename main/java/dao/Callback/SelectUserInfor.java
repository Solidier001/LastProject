package dao.Callback;

import daomain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class SelectUserInfor implements HibernateCallback<User> {
    private static final String hql="select new User (id, imglocation, sex, nickname, fscstr, stuid, QQ, phone) from User where id=:uid";
    private String uid;

    public SelectUserInfor(String uid) {
        this.uid = uid;
    }

    @Override
    public User doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setParameter("uid",uid);
        ArrayList<User> list= (ArrayList<User>) query.list();
        return list.get(0);
    }
}
