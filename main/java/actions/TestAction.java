package actions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import util.OrmService;
import test.testObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

public class TestAction extends ActionSupport implements ModelDriven<testObject> {
    private testObject test=new testObject();
    private testObject test1;
    WebApplicationContext wac= WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    OrmService service= (OrmService) wac.getBean("OrmService");
    @Override
    public testObject getModel() {
        return test;
    }
    public String save(){
        service.save(test);
        return SUCCESS;
    }
    public String read(){
        test1= (testObject) service.read(testObject.class.getName(),"s");
        HttpServletRequest request= ServletActionContext.getRequest();
        request.setAttribute("id",test1.getId());
        request.setAttribute("name",test1.getName());
        request.setAttribute("age",test1.getAge());
        return SUCCESS;
    }
}
