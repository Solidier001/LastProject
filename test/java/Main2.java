
import daomain.Orders;
import daomain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import service.OrderService;
import java.util.ArrayList;
import java.util.Date;

public class Main2 {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibernateTemplate template= (HibernateTemplate) context.getBean("HibernateTemplate");

       }
}
