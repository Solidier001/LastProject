package util;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import test.testObject;

public class OrmService extends HibernateDaoSupport {
    public void save(Object object){
        this.getHibernateTemplate().save(object);
    }
    public Object read(String className,String id){
        Object object=this.getHibernateTemplate().get(className,id.toString());
        return object;
    }
    public void delate(Object object){
        this.getHibernateTemplate().delete(object);
    }
    public void update(Object object){
        this.getHibernateTemplate().update(object);
    }
}
