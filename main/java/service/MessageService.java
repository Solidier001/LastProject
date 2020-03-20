package service;

import daomain.Goods;
import daomain.Message;
import daomain.User;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;

public class MessageService extends HibernateDaoSupport {
    public void save(Message message){
        this.getHibernateTemplate().save(message);
    }
    public ArrayList<Message> Getmessages(String uid,String otherid){
        DetachedCriteria criteria= DetachedCriteria.forClass(Message.class);
        criteria.add(Restrictions.or(
                Restrictions.and(
                        Restrictions.eq("form",uid),
                        Restrictions.eq("to",otherid)
                ),
                Restrictions.and(
                        Restrictions.eq("form",otherid),
                        Restrictions.eq("to",uid))
                )
        );
        criteria.addOrder(Order.asc("date"));
        return (ArrayList<Message>)getHibernateTemplate().findByCriteria(criteria);
    }
    public ArrayList<String> senders(String id){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Message.class);
        criteria.add(Restrictions.eq("to",id));
        criteria.setProjection(Projections.distinct(Projections.property("form")));
        ArrayList<String> resultlist= (ArrayList<String>) template.findByCriteria(criteria);
        return resultlist;
    }
    public String getnewmessage(String id){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("form",id));
        detachedCriteria.addOrder(Order.desc("date"));
        detachedCriteria.setProjection(Projections.property("message"));
        ArrayList<String> list= (ArrayList<String>) template.findByCriteria(detachedCriteria,0,1);
        System.out.println(list.get(0));
        return list.get(0);
    }
}
