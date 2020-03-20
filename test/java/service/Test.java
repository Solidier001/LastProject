package service;

import daomain.Goods;
import daomain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import util.ReviewKeyGenreater;

import java.util.ArrayList;

public class Test {
    public ArrayList<String> op() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibernateTemplate template = (HibernateTemplate) context.getBean("HibernateTemplate");
        DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
        criteria.setProjection(Projections.property("id"));
        ArrayList<String> list = (ArrayList<String>) template.findByCriteria(criteria);
        return list;
    }
}
