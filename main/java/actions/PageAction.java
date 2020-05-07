package actions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import daomain.Goods;
import daomain.Message;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.MessaeforTable;
import service.GoodsService;
import service.MessageService;
import service.UserService;
import util.OrmService;

import javax.annotation.Resource;
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
    @Resource(name = "GoodsService")
    GoodsService service;
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    @Resource(name="MessageService")
    private MessageService messageService;
    @Resource(name = "UserService")
    private UserService userService;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public String allGoods() throws Exception{
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        int limt=Integer.valueOf(request.getParameter("limt"));
        String allgood=gson.toJson(service.readAll(firstindex,limt));
        inputStream= new  ByteArrayInputStream(allgood.getBytes("utf-8"));
        return SUCCESS;
    }

    public String getOneGood() throws UnsupportedEncodingException {
        Goods good= service.OneGoods(request.getParameter("id"));
        String goodstring=gson.toJson(good);
        inputStream=new ByteArrayInputStream(goodstring.getBytes("utf-8"));
        return SUCCESS;
    }

    public String allGoodsforTable() throws Exception{
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        int limt=Integer.valueOf(request.getParameter("limt"));
        MessaeforTable result=new MessaeforTable(service.readAllforTable(firstindex,limt),service.getAllNumber(),0,"");
        String allgood=gson.toJson(result);
        inputStream= new  ByteArrayInputStream(allgood.getBytes("utf-8"));
        return SUCCESS;
    }

    public String listNumber() throws Exception {
        String list=request.getParameter("ListName");
        String name=request.getParameter("name");
        long number=-1;
        switch (list){
            case "all": number=service.getAllNumber();break;
            case "search": number=service.getNumberByNmae(name);break;
        }
        inputStream= new ByteArrayInputStream(String.valueOf(number).getBytes("utf-8"));
        return SUCCESS;
    }

    public String getMessages() throws UnsupportedEncodingException {
        ArrayList<Message> list=messageService.Getmessages(request.getParameter("uid"),request.getParameter("otherid"));
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        inputStream=new  ByteArrayInputStream(gson.toJson(list).getBytes("utf-8"));
        return SUCCESS;
    }

    public String MessageUserlist() throws UnsupportedEncodingException {
        inputStream=new ByteArrayInputStream(gson.toJson(messageService.senders(request.getParameter("id"))).getBytes("utf-8"));
        return SUCCESS;
    }

    public String newmessage() throws UnsupportedEncodingException {
        inputStream=new ByteArrayInputStream(messageService.getnewmessage(request.getParameter("id")).getBytes("utf-8"));
        return SUCCESS;
    }

    public String getSpecialty() throws UnsupportedEncodingException {
        String faculty=request.getParameter("faculty");
        inputStream=new ByteArrayInputStream(gson.toJson( userService.getSbyF(faculty)).getBytes("utf-8"));
        return SUCCESS;
    }
}
