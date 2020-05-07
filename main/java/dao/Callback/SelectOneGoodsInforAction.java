package dao.Callback;

import daomain.Goods;
import daomain.Orders;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;


public class SelectOneGoodsInforAction implements HibernateCallback<Goods> {
    private String id;
    private static final String hql="select new Goods (pictures, detail, name, price, times) from Goods where id=:id";

    public SelectOneGoodsInforAction(String id) {
        this.id = id;
    }

    @Override
    public Goods doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setParameter("id",id);
        ArrayList<Goods>list= (ArrayList<Goods>) query.list();
        Goods good=list.get(0);
        good.setId(id);
        return good;
    }
}
