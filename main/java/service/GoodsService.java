package service;

import daomain.Goods;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import pojo.TowParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsService extends HibernateDaoSupport {
    public ArrayList<Goods> readAll(){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria);
        return list;
    }
}
