package actions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import daomain.Message;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;
import service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PageAction extends ActionSupport {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session=request.getSession();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    GoodsService service = (GoodsService) wac.getBean("GoodsService");
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    private MessageService messageService=(MessageService)wac.getBean("MessageService");
    private String uid;
    private String otherid;
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
    public String allGoods() throws Exception{
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        String allgood=gson.toJson(service.readAll(firstindex));
        inputStream= new  ByteArrayInputStream(allgood.getBytes("utf-8"));
        return SUCCESS;
    }
    public String listNumber() throws Exception {
        String list=request.getParameter("ListName");
        long namber=-1;
        switch (list){
            case "all": namber=service.getAllNumber();break;
        }
        inputStream= new  ByteArrayInputStream(String.valueOf(namber).getBytes("utf-8"));
        return SUCCESS;
    }
    public String getMessages() throws UnsupportedEncodingException {
        ArrayList<Message> list=messageService.Getmessages(uid,otherid);
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        inputStream=new  ByteArrayInputStream(gson.toJson(list).getBytes("utf-8"));
        return SUCCESS;
    }
}
