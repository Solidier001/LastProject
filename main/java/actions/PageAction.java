package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PageAction extends ActionSupport {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session=request.getSession();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    GoodsService service = (GoodsService) wac.getBean("GoodsService");
    public String allGoods(){
        HttpServletRequest request=ServletActionContext.getRequest();
        request.setAttribute("allgoods",service.readAll());
        return SUCCESS;
    }
}
