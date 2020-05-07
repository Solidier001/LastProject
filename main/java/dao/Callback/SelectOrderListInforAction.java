package dao.Callback;

import daomain.Orders;
import daomain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.ArrayList;

public class SelectOrderListInforAction implements HibernateCallback<ArrayList<Orders>> {
    private int firstpage, limt;
    private User buyer, owner;
    private String hqlappend;

    public SelectOrderListInforAction(int firstpage, int limt) {
        this.firstpage = firstpage;
        this.limt = limt;
        buyer = owner = null;
        hqlappend = "";
    }

    public SelectOrderListInforAction() {
        this.firstpage = 0;
        this.limt = 20;
        buyer = owner = null;
        hqlappend = "";
    }

    public SelectOrderListInforAction(int firstpage) {
        this.limt = 20;
        this.firstpage = firstpage;
        buyer = owner = null;
        hqlappend = "";
    }

    public SelectOrderListInforAction(int firstpage, int limt, User buyer, User owner) {
        this.firstpage = firstpage;
        this.limt = limt;
        this.buyer = buyer;
        this.owner = owner;
        if (buyer == null) {
            if (owner != null) hqlappend = " where owner=:owner";
            else {
                hqlappend = "";
            }
        } else if (owner == null) {
            hqlappend = " where buyr=:buyer";
        } else {
            hqlappend = " where buyr=:buyer and owner=:owner";
        }
    }

    @Override
    public ArrayList<Orders> doInHibernate(Session session) throws HibernateException {
        StringBuffer hql = new StringBuffer("select new Orders(id, buyr.nickname ,owner.nickname,statu,paymethod,good.name,price,date,nunber,hasReview) from Orders");
        hql.append(hqlappend);
        Query query = session.createQuery(hql.toString());
        if (owner != null) {
            query.setParameter("owner", owner);
        }
        if (buyer != null) {
            query.setParameter("buyer", buyer);
        }
        query.setFirstResult(firstpage);
        query.setMaxResults(limt);
        return (ArrayList<Orders>) query.list();
    }
}
