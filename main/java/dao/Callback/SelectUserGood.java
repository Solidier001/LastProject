package dao.Callback;

import daomain.Goods;
import daomain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class SelectUserGood implements HibernateCallback<ArrayList<Goods>> {
    private static final String hql="select new Goods(pictures, detail, name, price, id, times)from Goods where user=:user";
    private User user;
    private int firstindex,limt;

    public SelectUserGood(User user, int firstindex, int limt) {
        this.user = user;
        this.firstindex = firstindex;
        this.limt = limt;
    }

    @Override
    public ArrayList<Goods> doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setParameter("user",user);
        query.setMaxResults(limt);
        query.setFirstResult(firstindex);
        return (ArrayList<Goods>) query.list();
    }
}
