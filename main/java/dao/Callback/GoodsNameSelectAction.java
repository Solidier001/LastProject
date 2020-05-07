package dao.Callback;

import daomain.Goods;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class GoodsNameSelectAction implements HibernateCallback<ArrayList<Goods>> {
    private String name;
    private int firstpage;
    private static final String hql="select new Goods(pictures, detail, name, price, id) from Goods where statu='未售完'and name like :name";

    public GoodsNameSelectAction(String name, int firstpage) {
        this.name = name;
        this.firstpage = firstpage;
    }

    @Override
    public ArrayList<Goods> doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setMaxResults(20);
        query.setFirstResult(firstpage);
        query.setParameter("name",'%'+name+'%');
        return (ArrayList<Goods>) query.list();
    }
}
