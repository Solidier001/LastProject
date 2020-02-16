package service;

import daomain.Message;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
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
                        Restrictions.eq("form",otherid)),
                        Restrictions.eq("to",uid)
                )
        );
        return (ArrayList<Message>)getHibernateTemplate().findByCriteria(criteria,0,20);
    }
}
