package service;

import dao.Callback.*;
import daomain.Review;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReviewService extends HibernateDaoSupport {

    public String Save(Review review, User user, String reviewstr, String location, String realpath,String filename){
        HibernateTemplate template=this.getHibernateTemplate();
        if (filename==null)filename=UUID.randomUUID() +".xml";
        File file= new File(realpath+ filename);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("review");
        document.setRootElement(root);
        root.addElement("uid").addText(user.getId());
        root.addElement("date").addText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        root.addElement("text").addText(reviewstr);
        try {
            FileUtils.writeStringToFile(file,document.asXML(),"utf-8");
            review.setLocation(location+ filename);
            review.setUser(user);
            template.save(review);
            return "成功";
        } catch (IOException e) {
            return "失败:"+e.getMessage();
        }
    }

    public Review getReview(String id){
        HibernateTemplate template=this.getHibernateTemplate();
        return template.load(Review.class,id);
    }
}
