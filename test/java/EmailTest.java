import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailTest {
    public static void main(String[] args) throws EmailException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Email email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setSmtpPort(994);
        email.setAuthenticator(new DefaultAuthenticator("asdfghjmn121@163.com", "IPEOFWQCFQDCZROI"));
        email.setSSLOnConnect(true);
        email.setFrom("asdfghjmn121@163.com");
        email.setSubject("验证码");
        email.setMsg("您好本次验证码为：123456请勿泄露他人");
        email.addTo("2532592455@qq.com");
        email.send();
    }
}
