package dao.Callback;

import daomain.Goods;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class GoodsListForTable implements HibernateCallback<ArrayList<Goods>> {
    public static final String hql="select new Goods(pictures, detail, name, price, id, times)from Goods ";
    private int index,limt;

    public GoodsListForTable(int index, int limt) {
        this.index = index;
        this.limt = limt;
    }

    @Override
    public ArrayList<Goods> doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setFirstResult(index);
        query.setMaxResults(limt);
        ArrayList<Goods> list= (ArrayList<Goods>) query.list();
        return list;
    }
}
