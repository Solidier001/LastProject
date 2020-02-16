package service;

import daomain.Goods;
import daomain.Orders;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.dom4j.*;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class UserService extends HibernateDaoSupport {

    HibernateTemplate template = this.getHibernateTemplate();

    public void buy(Orders orders) {
        template.save(orders);
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
}
