package actions;

import ActionExtension.MoreResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vdurmont.emoji.EmojiParser;
import daomain.Orders;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.ObjectNotFoundException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.MessaeforTable;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class OrderAction extends ActionSupport implements ModelDriven<Orders>, MoreResult {
    Orders orders=new Orders();private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpSession session = request.getSession();
    private InputStream inputStream;
    private WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    private OrderService orderService = (OrderService) wac.getBean("OrderService");
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    public InputStream getInputStream() {
        return inputStream;
    }
    @Override
    public Orders getModel() {
        return orders;
    }
    public String getOrderlist() throws UnsupportedEncodingException {
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        int limt=Integer.valueOf(request.getParameter("limt"));
        try{
            ArrayList<Orders> list=orderService.ordersList(((User)session.getAttribute("user")),firstindex,limt);
            MessaeforTable messaeforTable=new MessaeforTable( list,orderService.getcount((User)session.getAttribute("user")),0,"");
            inputStream=new ByteArrayInputStream(gson.toJson(messaeforTable).getBytes("utf-8"));
        }catch (ObjectNotFoundException e){
            e.printStackTrace();
            MessaeforTable messaeforTable=new MessaeforTable( new ArrayList<Orders>(),orderService.getcount((User)session.getAttribute("user")),0,"");
            inputStream=new ByteArrayInputStream(gson.toJson(messaeforTable).getBytes("utf-8"));
        }
        return SUCCESS;
    }
    public String getOrderlistforbuyer() throws UnsupportedEncodingException {
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        int limt=Integer.valueOf(request.getParameter("limt"));
        ArrayList<Orders> list=orderService.ordersListforbuyer(((User)session.getAttribute("user")),firstindex,limt);
        MessaeforTable messaeforTable=new MessaeforTable( list,orderService.getcountforbuyer((User)session.getAttribute("user")),0,"");
        inputStream=new ByteArrayInputStream(gson.toJson(messaeforTable).getBytes("utf-8"));
        return SUCCESS;
    }
    public String chick(){
        String id=request.getParameter("id");
        orderService.check(id);
        return SUCCESS;
    }
    public String getorder() throws UnsupportedEncodingException {
        inputStream=new ByteArrayInputStream(orderService.order(request.getParameter("id")).getBytes("utf-8"));
        return SUCCESS;
    }

    public String writeOrderReview() throws UnsupportedEncodingException {
        User user= (User) session.getAttribute("user");
        String review=request.getParameter("review");
        String realpath=ServletActionContext.getServletContext().getRealPath("/review/ordersreview/");
        if (orderService.writeOrderReview(user,review,realpath,orders).equals("success")){
            String resultstr="评价成功:smiley:!";
            String htmlstr =EmojiParser.parseToHtmlDecimal(EmojiParser.parseToUnicode(resultstr));
            inputStream=new ByteArrayInputStream(htmlstr.getBytes("utf-8"));
        }
        else {
            String resultstr="出现异常:tired_face:!";
            String htmlstr =EmojiParser.parseToHtmlDecimal(EmojiParser.parseToUnicode(resultstr));
            inputStream=new ByteArrayInputStream(htmlstr.getBytes("utf-8"));
        }
        return SUCCESS;
    }

    public String readOrderReview() throws UnsupportedEncodingException{
        String OrderReview=orderService.readOrderReview(orders.getId());
        String uid=((User) session.getAttribute("user")).getId();
        String resultstr=uid+'/'+OrderReview;
        if (OrderReview==null||OrderReview.equals("")||OrderReview.equals("null"))
            inputStream=new ByteArrayInputStream(ERROR.getBytes("utf-8"));
        else
        inputStream=new ByteArrayInputStream(resultstr.getBytes("utf-8"));
        return SUCCESS;
    }
}
