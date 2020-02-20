package service;

import daomain.Goods;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import java.util.ArrayList;

public class GoodsService extends HibernateDaoSupport {
    public ArrayList<Goods> readAll(int firstindex,int limt){
        int index = (firstindex - 1) * 20;
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria,index,limt);
        return list;
    }
    public long getAllNumber(){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.setProjection(Projections.rowCount());
        long count= (long) criteria.getExecutableCriteria(template.getSessionFactory().getCurrentSession()).uniqueResult();
        return count;
    }
    public long getNumberByNmae(String name){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.like("name","%"+name+"%"));
        criteria.setProjection(Projections.rowCount());
        long count= (long) criteria.getExecutableCriteria(template.getSessionFactory().getCurrentSession()).uniqueResult();
        return count;
    }
    public ArrayList<Goods> findGoodsByname(String name,int firstpage){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.like("name","%"+name+"%"));
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria,firstpage,20);
        return list;
    }
    public ArrayList<Goods> justResdAllGoods(/*String username*/){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
//        criteria.add(Restrictions.eq("user",username))
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria);
        return list;
    }
}
