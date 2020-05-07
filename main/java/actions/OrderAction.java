package actions;

import ActionExtension.MoreResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vdurmont.emoji.EmojiParser;
import daomain.Orders;
import daomain.Review;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.ObjectNotFoundException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.MessaeforTable;
import pojo.ReviewList;
import service.OrderService;
import service.ReviewService;

import javax.annotation.Resource;
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
    @Resource(name = "OrderService")
    private OrderService orderService;
    @Resource
    private ReviewService reviewService;
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
            gson.toJson(list);
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
        String filename;
        User user= (User) session.getAttribute("user");
        String reviewstr=request.getParameter("review");
        String id=request.getParameter("id");
        String path=orderService.getReiew(id);
        Review review=new Review("Order");
        String realpath=ServletActionContext.getServletContext().getRealPath(path);
        ReviewList list=orderService.reviewlist(realpath,id);
        if (list.isEmpty())filename=null;
        else filename=list.getList()[0];
        if (reviewService.Save(review,user,reviewstr,path,realpath,filename).equals("成功")){
            orderService.updateReviewStatu(id);
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
        String path=orderService.getReiew(orders.getId());
        String realpath=ServletActionContext.getServletContext().getRealPath(path);
        ReviewList list=orderService.reviewlist(realpath,orders.getId());
        if (list.isEmpty())
            inputStream=new ByteArrayInputStream(ERROR.getBytes("utf-8"));
        else
        inputStream=new ByteArrayInputStream(gson.toJson(list).getBytes("utf-8"));
        return SUCCESS;
    }

}
