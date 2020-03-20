package service.Callback;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UpdateGoodInforAction implements HibernateCallback<String> {
    private String name;
    private String detail;
    private int times;
    private int price;
    private String id;

    public UpdateGoodInforAction(String name, String detail, int times, int price, String id) {
        this.name = name;
        this.detail = detail;
        this.times = times;
        this.price = price;
        this.id = id;
    }

    @Override
    public String doInHibernate(Session session) throws HibernateException {
        String hql="update Goods g set g.name=:name ,g.detail=:detail,g.times=:times,g.price=:price where g.id=:id";
        Query query  = session.createQuery(hql);
        query.setParameter("name",name);
        query.setParameter("detail",detail);
        query.setParameter("times",times);
        query.setParameter("price",price);
        query.setParameter("id",id);
        query.executeUpdate();
        session.close();
        return null;
    }
}
