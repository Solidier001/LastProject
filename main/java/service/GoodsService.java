package service;

import daomain.Goods;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import java.util.ArrayList;

public class GoodsService extends HibernateDaoSupport {
    public ArrayList<Goods> readAll(int firstindex){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria,firstindex,20);
        return list;
    }
    public long getAllNumber(){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.setProjection(Projections.rowCount());
        long count= (long) criteria.getExecutableCriteria(template.getSessionFactory().getCurrentSession()).uniqueResult();
        return count;
    }
    public ArrayList<Goods> findGoodsByname(String name){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.like("name","%"+name+"%"));
        criteria.setProjection(Projections.rowCount());
        Long count= (Long) criteria.getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().getCurrentSession()).uniqueResult();
        System.out.println(count);
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria,0,10);
        return list;
    }
}
