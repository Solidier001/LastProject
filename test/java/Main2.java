
import com.google.gson.Gson;
import daomain.Goods;
import daomain.Orders;
import daomain.User;
import org.apache.commons.mail.EmailException;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import pojo.FS;
import service.GoodsService;
import service.OrderService;
import service.UserService;

import java.io.*;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws IOException, EmailException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibernateTemplate template = (HibernateTemplate) context.getBean("HibernateTemplate");
        UserService service= (UserService) context.getBean("UserService");
        service.OAthcode("2532592455@qq.com");
    }
}
