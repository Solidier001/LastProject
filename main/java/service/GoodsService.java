package service;

import daomain.Goods;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import service.Callback.UpdateGoodInforAction;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class GoodsService extends HibernateDaoSupport {
    public ArrayList<Goods> readAll(int firstindex,int limt){
        int index = (firstindex - 1) * 20;
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.eq("statu","未售完"));
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("pictures").as("pictures"));
        pList.add(Projections.property("price").as("price"));
        pList.add(Projections.property("detail").as("detail"));
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        criteria.setProjection(pList);
        criteria.setResultTransformer(Transformers.aliasToBean(Goods.class ));
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria,index,limt);
        return list;
    }
    public ArrayList<Goods> readAllforTable(int firstindex,int limt){
        int index = (firstindex - 1) * 20;
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("pictures").as("pictures"));
        pList.add(Projections.property("price").as("price"));
        pList.add(Projections.property("detail").as("detail"));
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        pList.add(Projections.property("times").as("times"));
        criteria.setProjection(pList);
        criteria.setResultTransformer(Transformers.aliasToBean(Goods.class ));
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
        criteria.add(Restrictions.eq("statu","未售完"));
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("pictures").as("pictures"));
        pList.add(Projections.property("price").as("price"));
        pList.add(Projections.property("detail").as("detail"));
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        criteria.setProjection(pList);
        criteria.setResultTransformer(Transformers.aliasToBean(Goods.class ));
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
    public Goods OneGoods(String id){
        Goods good=new Goods();
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.eq("id",id));
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("pictures").as("pictures"));
        pList.add(Projections.property("price").as("price"));
        pList.add(Projections.property("detail").as("detail"));
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("times").as("times"));
        pList.add(Projections.property("name").as("name"));
        criteria.setProjection(pList);
        criteria.setResultTransformer(Transformers.aliasToBean(Goods.class ));
        ArrayList<Goods> list= (ArrayList<Goods>) template.findByCriteria(criteria);
        return list.get(0);
    }
    public String udategood(Goods good){
        try {
            HibernateTemplate template=this.getHibernateTemplate();
            HibernateCallback callback=new UpdateGoodInforAction(good.getName(),good.getDetail(),good.getTimes(),good.getPrice(),good.getId());
            template.execute(callback);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
    }
    public Object[] getcannochangdata(Goods good){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.eq("id",good.getId()));
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("pictures"));
        pList.add(Projections.property("id"));
        pList.add(Projections.property("user"));
        criteria.setProjection(pList);
        ArrayList<Object[]> list= (ArrayList<Object[]>) template.findByCriteria(criteria);
        return list.get(0);
    }
    public void review(String realpath,String reviewstr,String id,String uid) throws IOException {
        HibernateTemplate template=this.getHibernateTemplate();
        Goods goods = template.get(Goods.class,id);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("review");
        document.setRootElement(root);
        root.addElement("uid").addText(uid);
        root.addElement("date").addText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        root.addElement("text").addText(reviewstr);
        File file= new File(realpath+goods.getReviewgood()+ UUID.randomUUID() +".xml");
        FileUtils.writeStringToFile(file,document.asXML(),"utf-8");
    }
    public void appendreview(String realpath,String reviewstr,String id) throws IOException {
        HibernateTemplate template=this.getHibernateTemplate();
        Goods goods = template.get(Goods.class,id);
        FileUtils.writeStringToFile(new File(realpath+ goods.getReviewgood()),"追评\n"+reviewstr,"utf-8",true);
    }
    public ArrayList<String> reviewlist(String id, String realpath){
        HibernateTemplate template=this.getHibernateTemplate();
        Goods goods = template.get(Goods.class,id);
        File file = new File(realpath+goods.getReviewgood());
        File[] tempList = file.listFiles();
        ArrayList<String> list=new ArrayList<>();
        System.out.println(file.getAbsolutePath());
        System.out.println(realpath+goods.getReviewgood());
        try{
            for (File file1: tempList){
                list.add(goods.getReviewgood()+file1.getName());
            }
        }catch (NullPointerException e){
            list=null;
        }
        return list;
    }
}
