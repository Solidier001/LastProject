package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import util.OrmService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountAction extends ActionSupport implements ModelDriven<User> {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session=request.getSession();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    OrmService service = (OrmService) wac.getBean("OrmService");
    private User user = new User();
    private String id;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String login() {
        System.out.println(id);
        User sample = (User) service.read(User.class.getName(), id);
        if (sample.getPassword().equals(password)){
            session.setAttribute("user",sample);
            return SUCCESS;
        }
        else
            return "false";
    }

    public String rigister() {
        service.save(user);
        return SUCCESS;
    }
    public String off_line(){
        session.removeAttribute("user");
        session.invalidate();
        return SUCCESS;
    }
    public String buy(){
        return SUCCESS;
    }
    @Override
    public User getModel() {
        return user;
    }
}
