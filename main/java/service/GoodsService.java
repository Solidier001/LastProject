package service;

import dao.Callback.*;
import daomain.Goods;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import pojo.ReviewList;
import util.ReviewKeyGenreater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class GoodsService extends HibernateDaoSupport {

    public void Save(User user,File[] image,String realpath,Goods good) throws IOException {
        HibernateTemplate template=this.getHibernateTemplate();
        if (image != null) {
            File savedir = new File(realpath, UUID.randomUUID().toString().replace("-",""));
            if (!savedir.getParentFile().exists())
                savedir.getParentFile().mkdirs();
            String picturedir1=savedir.getPath();
            picturedir1=picturedir1.substring(picturedir1.indexOf("\\img")).replace("\\","/");
            for (int i = 0; i < image.length; i++) {
                File savefile = new File(savedir, UUID.randomUUID().toString().replace("-","")+".jpg");
                FileUtils.copyFile(image[i], savefile);
            }
            good.setReviewgood("/review/goods/"+ReviewKeyGenreater.gerDirKey()+'/');
            good.setTimes(0);
            good.setPictures(picturedir1);
            good.setUser(user);
            template.save(good);
        }
    }

    public ArrayList<Goods> readAll(int firstindex,int limt){
        int index = (firstindex - 1) * 20;
        HibernateTemplate template=this.getHibernateTemplate();
        SelectGoodsListInforAction callback=new SelectGoodsListInforAction(index,limt);
        ArrayList<Goods> list=template.execute(callback);
        return list;
    }

    public ArrayList<Goods> readAllforTable(int firstindex,int limt){
        int index = (firstindex - 1) * 20;
        HibernateTemplate template=this.getHibernateTemplate();
        GoodsListForTable callback=new GoodsListForTable(index,limt);
        ArrayList<Goods> list=template.execute(callback);
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
        GoodsNameSelectAction callback=new GoodsNameSelectAction(name, firstpage);
        ArrayList<Goods> list=  template.execute(callback);
        return list;
    }
    //callback
    public ArrayList<Goods> ResdAllGoodsbuyuser(User user,int firstindex,int limt){
        HibernateTemplate template=this.getHibernateTemplate();
        SelectUserGood callback=new SelectUserGood(user, firstindex, limt);
        ArrayList<Goods> list= template.execute(callback);
        return list;
    }

    public Goods OneGoods(String id){
        HibernateTemplate template=this.getHibernateTemplate();
        SelectOneGoodsInforAction callback=new SelectOneGoodsInforAction(id);
        Goods good= template.execute(callback);
        return good;
    }

    public String udategood(Goods good){
        try {
            HibernateTemplate template=this.getHibernateTemplate();
            //callback优化
            HibernateCallback callback=new UpdateGoodInforAction(good.getName(),good.getDetail(),good.getTimes(),good.getPrice(),good.getId());
            template.execute(callback);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
    }
//callback
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

    public String getReiew(String id){
        HibernateTemplate template=this.getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(Goods.class);
        criteria.add(Restrictions.eq("id",id));
        criteria.setProjection(Projections.property("reviewgood"));
        ArrayList<String> list= (ArrayList<String>) template.findByCriteria(criteria);
        return list.get(0);
    }

    public ReviewList reviewlist(String realpath, String id){
        File file = new File(realpath);
        String[] list=file.list();
        String root=getReiew(id);
        return new ReviewList(root,list);
    }
}
