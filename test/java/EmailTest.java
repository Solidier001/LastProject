import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailTest {
    public static void main(String[] args) throws EmailException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HtmlEmail email= (HtmlEmail) context.getBean("email");
        email.addTo("192720197@qq.com");
        email.setFrom("asdfghjmn121@163.com","校园二手客服");
        email.setSubject("验证码");//设置发送主题
        email.setMsg("您好，您的验证码为715645");//设置发送内容
        email.send();//进行发送
    }
}
