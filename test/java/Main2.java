
import com.google.gson.Gson;
import daomain.Orders;
import daomain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import service.Callback.SelectOrderListInforAction;
import service.OrderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;

public class Main2 {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HibernateTemplate template= (HibernateTemplate) context.getBean("HibernateTemplate");
        /*User user= (User) template.get(User.class.getName(),"25304739406");
        ArrayList<Orders>list= template.execute(new SelectOrderListInforAction(0, 20,null,user));
        String str=new Gson().toJson(list);
        System.out.println(str);*/
    }
}
