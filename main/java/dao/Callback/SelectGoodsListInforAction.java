package dao.Callback;

import daomain.Goods;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class SelectGoodsListInforAction implements HibernateCallback<ArrayList<Goods>> {
    public static final String hql="select new Goods (pictures, detail, name, price, id) from Goods where statu='未售完'";
    private int index,limt;

    public SelectGoodsListInforAction(int index, int limt) {
        this.index = index;
        this.limt = limt;
    }

    @Override
    public ArrayList<Goods> doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setMaxResults(limt);
        query.setFirstResult(index);
        ArrayList<Goods> list= (ArrayList<Goods>) query.list();
        return list;
    }
}
