package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Goods;
import daomain.Orders;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;
import service.MessageService;
import service.UserService;
import util.OrmService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class AccountAction extends ActionSupport implements ModelDriven<User> {
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpSession session=request.getSession();
    private WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    private OrmService service = (OrmService) wac.getBean("OrmService");
    private UserService userService=(UserService) wac.getBean("UserService");
    private GoodsService goodsService=(GoodsService)wac.getBean("GoodsService");
    private User user = new User();
    public String login() {
        System.out.println((String) session.getAttribute("request"));
       User sample = (User) service.read(User.class.getName(), user.getId());
        if (sample.getPassword().trim().equals(user.getPassword().trim())){
            session.setAttribute("user",sample);
            return SUCCESS;
        }
        else
            return "false";
    }

    public String rigister() {
        user.setLocations(userService.initLocations());
        service.save(user);
        return SUCCESS;
    }
    public String off_line(){
        session.removeAttribute("user");
        session.invalidate();
        return SUCCESS;
    }
    public String buy(){
        Orders order=new Orders();
        Goods goods=new Goods();
        goods.setId(request.getParameter("goodid"));
        order.setPaymethod(request.getParameter("paymethod"));
        order.setNunber(1);
        order.setUser(user);
        order.setGood(goods);
        order.setDate(new Date());
        service.save(order);
        return SUCCESS;
    }
    @Override
    public User getModel() {
        return user;
    }
}
