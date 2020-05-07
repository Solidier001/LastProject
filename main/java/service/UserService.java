package service;

import dao.Callback.SelectUserInfor;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.struts2.ServletActionContext;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.web.context.WebApplicationContext;
import pojo.AlipayToken;
import dao.Callback.UpdateAuthTokenAction;
import pojo.FS;
import pojo.ReviewList;
import util.ReviewKeyGenreater;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UserService extends HibernateDaoSupport {

    @Autowired
    WebApplicationContext context;

    public void rigister(User user,File img,String realpath,String reviewrealpath) throws IOException {
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddss");
        String datestr= format.format(new Date());
        String filename=datestr+".png";
        user.setImglocation(filename);
        File file=new File(realpath+"/"+filename);
        FileUtils.copyFile(img,file);
        HibernateTemplate template = this.getHibernateTemplate();
        user.setFscstr(user.getFaculty()+' '+user.getSpecialty()+' '+user.getTeam());
        System.out.println(user.getFscstr());
        user.setReviewToUser(realpath+ new ReviewKeyGenreater().getKey());
        user.setLocations(initLocations());
        template.save(User.class.getName(),user);
    }

    public String initLocations() {
        String realpath = ServletActionContext.getServletContext().getRealPath("/locations");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("locations");
        document.setRootElement(root);
        root.addElement("location");
        String XmlName=null;
        try {
            XmlName=UUID.randomUUID().toString()+".xml";
            FileWriter out = new FileWriter(realpath + '\\'+XmlName);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            writer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return XmlName;
    }

    public void AlterLocations(String Xml,String[] locations) throws FileNotFoundException, DocumentException {
        try {
            SAXReader reader = new SAXReader();
            FileInputStream inputStream=new FileInputStream(Xml);
            Document document = reader.read(inputStream);
            Element root=document.getRootElement();
            ArrayList<Element> list=(ArrayList)root.elements();
            Element location;
            for (int i=0;i<list.size();i++){
                location=list.get(i);
                location.setText(locations[i]);
            }
            FileWriter out = new FileWriter(Xml);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            writer.close();
            out.close();
            inputStream.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void review(String realpath,String reviewstr,String id) throws IOException {
        HibernateTemplate template=this.getHibernateTemplate();
        User user = template.get(User.class,id);
        FileUtils.writeStringToFile(new File(realpath+user.getReviewToUser()),reviewstr,"utf-8");
    }*/

    public void appendreview(String realpath,String reviewstr,String id) throws IOException {
        HibernateTemplate template=this.getHibernateTemplate();
        User user = template.get(User.class,id);
        FileUtils.writeStringToFile(new File(realpath+user.getReviewToUser()),"追评:\n"+reviewstr,"utf-8",true);
    }

    /* public ArrayList<String> reviewlist(String id, String realpath){
        HibernateTemplate template=this.getHibernateTemplate();
        User user = template.get(User.class,id);
        File file = new File(realpath+user.getReviewuser());
        File[] tempList = file.listFiles();
        ArrayList<String> list=new ArrayList<>();
        for (File file1: tempList){
            list.add(file1.getName());
        }
        return list;
    }*/

    public User usermessage(String id){
        HibernateTemplate template=getHibernateTemplate();
        User user=template.execute(new SelectUserInfor(id));
        return user;
    }

    public User login(String string,String method) throws IndexOutOfBoundsException{
        HibernateTemplate template=getHibernateTemplate();
        User user;
        DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
        switch (method){
            case "id":user=template.get(User.class,string);break;
            case "stuid":
                criteria.add(Restrictions.eq("stuid",string));
                user=((ArrayList<User>)template.findByCriteria(criteria)).get(0);
                break;
            default:
                criteria.add(Restrictions.eq("email",string));
                user=((ArrayList<User>)template.findByCriteria(criteria)).get(0);
                break;
        }
        return user;
    }

    public void UpdateAuthToken(AlipayToken token,User user){
        HibernateTemplate template=this.getHibernateTemplate();
        template.execute(new UpdateAuthTokenAction(token,user));
    }

    public ReviewList reviewlist(String realpath, String root){
        File file = new File(realpath);
        String[] list=file.list();
        return new ReviewList(root,list);
    }

    public ArrayList<String> getSbyF(String faculty){
        HibernateTemplate template=getHibernateTemplate();
        DetachedCriteria criteria=DetachedCriteria.forClass(FS.class);
        criteria.add(Restrictions.eq("faculty",faculty));
        criteria.setProjection(Projections.property("specialty"));
        return (ArrayList<String>) template.findByCriteria(criteria);
    }

    public String  OAthcode(String emailnumber) throws EmailException {
        String code=String.valueOf(new Random().nextInt(1000000));
        SimpleEmail email= (SimpleEmail) context.getBean("email");
        email.addTo(emailnumber);
        email.setMsg("您好，您的验证码为"+code);
        try {
            email.send();
        }catch (IllegalStateException e){
            email.sendMimeMessage();
        }
        return code;
    }
}
