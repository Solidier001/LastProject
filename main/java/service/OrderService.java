package service;

import daomain.Goods;
import daomain.Orders;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import service.Callback.SelectOrderListInforAction;
import service.Callback.UpdateOrderReviewAction;
import util.ReviewKeyGenreater;

import java.io.File;
import java.util.ArrayList;

public class OrderService extends HibernateDaoSupport {
    public ArrayList<Object> review(User owner) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
        criteria.add(Restrictions.eq("owner", owner));
        criteria.setProjection(Projections.property("reviewid"));
        ArrayList<Object> list = (ArrayList<Object>) this.getHibernateTemplate().findByCriteria(criteria);
        return list;
    }

    public ArrayList<Orders> ordersListforbuyer(User user, int firstpage, int limt) {
        int index = (firstpage - 1) * 20;
        HibernateTemplate template = this.getHibernateTemplate();
        ArrayList<Orders> list=template.execute(new SelectOrderListInforAction(index,limt,user,null));
        return list;
    }

    public ArrayList<Orders> ordersList(User user, int firstpage, int limt) {
        int index = (firstpage - 1) * 20;
        HibernateTemplate template = this.getHibernateTemplate();
        ArrayList<Orders> list= template.execute(new SelectOrderListInforAction(index, limt,null,user));
        return list;
    }

    public String order(String id) {
        HibernateTemplate template = this.getHibernateTemplate();
        DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
        criteria.add(Restrictions.eq("id", id));
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("statu"));
        criteria.setProjection(pList);
        ArrayList<Object> resultlist = (ArrayList<Object>) template.findByCriteria(criteria);
        Object a = resultlist.get(0);
        return (String) a;
    }

    public long getcount(User owner) {
        HibernateTemplate template = this.getHibernateTemplate();
        DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("owner", owner));
        long count = (long) criteria.getExecutableCriteria(template.getSessionFactory().getCurrentSession()).uniqueResult();
        return count;
    }

    public long getcountforbuyer(User buyr) {
        HibernateTemplate template = this.getHibernateTemplate();
        DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("buyr", buyr));
        long count = (long) criteria.getExecutableCriteria(template.getSessionFactory().getCurrentSession()).uniqueResult();
        return count;
    }

    public void check(String id) {
        HibernateTemplate template = this.getHibernateTemplate();
        Orders orders = (Orders) template.get(Orders.class.getName(),id);
        orders.setStatu("已完成");
        template.update(orders);
    }

    public String readOrderReview(String id) {
        HibernateTemplate template = this.getHibernateTemplate();
        DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
        criteria.setProjection(Projections.property("review"));
        criteria.add(Restrictions.eq("id", id));
        ArrayList<String> list = (ArrayList<String>) template.findByCriteria(criteria);
        return list.get(0);
    }

    public String writeOrderReview(User user, String review, String rootpath, Orders order) {
        HibernateTemplate template = this.getHibernateTemplate();
        try {
            String filename = readOrderReview(order.getId());
            if (filename == null || filename.equals("")) {
                filename = new ReviewKeyGenreater().getKey() + ".txt";
                System.out.println(rootpath + user.getId());
                FileUtils.writeStringToFile(new File(rootpath + user.getId() + '/' + filename), review, "utf-8");
                HibernateCallback callback = new UpdateOrderReviewAction(user, filename, order.getId());
                template.execute(callback);
            }
            else FileUtils.writeStringToFile(new File(rootpath + user.getId() + '/' + filename), review, "utf-8");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
