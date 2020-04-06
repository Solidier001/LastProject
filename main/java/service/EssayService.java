package service;

import daomain.Essay;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import util.Essaykey;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class EssayService extends HibernateDaoSupport {
    public String Save(Essay essay, String content, String realpath) {
        try {
            HibernateTemplate template = this.getHibernateTemplate();
            essay.setContent(Essaykey.Generator(essay.getUser()));
            File file = new File(realpath + essay.getContent());
            FileUtils.writeStringToFile(file, content, "utf-8");
            essay.setDate(new Date());
            template.save(essay);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ArrayList<Essay> getEssays(String uid, int firstResult) {
        HibernateTemplate template = this.getHibernateTemplate();
        User user = template.get(User.class, uid);
        DetachedCriteria criteria = DetachedCriteria.forClass(Essay.class);
        criteria.addOrder(Order.desc("user"));
        criteria.add(Restrictions.eq("user", user));
        ArrayList<Essay> list = (ArrayList<Essay>) template.findByCriteria(criteria, firstResult, 50);
        return list;
    }

    public ArrayList<Essay> ShowEssays(int firstResult) {
        HibernateTemplate template = this.getHibernateTemplate();
        DetachedCriteria criteria = DetachedCriteria.forClass(Essay.class);
        criteria.addOrder(Order.desc("user"));
        ArrayList<Essay> list = (ArrayList<Essay>) template.findByCriteria(criteria, firstResult, 50);
        return list;
    }

    public String DeleteEssay(Essay sample, String realpath) {
        HibernateTemplate template = this.getHibernateTemplate();
        try {
            Essay essay = template.get(Essay.class, sample.getId());
            FileUtils.forceDelete(new File(realpath + essay.getContent()));
            template.delete(essay);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
