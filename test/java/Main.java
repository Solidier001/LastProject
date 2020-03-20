
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import pojo.FS;
import util.OrmService;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrmService ormService = ((OrmService) context.getBean("OrmService"));
        Document document = Jsoup.parse(new File("E:\\LastProject\\src\\test\\java\\test.txt"), "utf-8");
        Elements Ps=document.getElementsByTag("p");
        String faculty=null;
        Pattern p=Pattern.compile("<.*");
        Pattern p2=Pattern.compile(".*学院");
        for (Element P:Ps){
            Matcher matcher=p.matcher(P.html());
            if (!matcher.matches()){
                Matcher matcher2=p2.matcher(P.html());
                if (matcher2.matches())faculty=P.html();
                else {
                    FS fs=new FS();
                    fs.setFaculty(faculty);
                    fs.setSpecialty(P.html());
                    ormService.save(fs);
                }
            }
        }
    }
}
