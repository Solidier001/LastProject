package actions;

import ActionExtension.MoreResult;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.WriterException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Goods;
import daomain.Orders;
import daomain.User;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.BzContent;
import pojo.GoodsDetails;
import service.AlipayService;
import service.GoodsService;
import service.MessageService;
import service.UserService;
import util.OrderKeyGenreater;
import util.OrmService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/project/account")
public class AccountAction extends ActionSupport implements ModelDriven<User>, MoreResult {
    private File img;

    private String imgFileName;

    private String imgContentType;

    private HttpServletRequest request = ServletActionContext.getRequest();

    private HttpSession session = request.getSession();

    private WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());

    private OrmService service = (OrmService) wac.getBean("OrmService");

    private UserService userService = (UserService) wac.getBean("UserService");

    private GoodsService goodsService = (GoodsService) wac.getBean("GoodsService");

    private InputStream inputStream;

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    private User user = new User();

    @Resource
    private AlipayService alipayService;

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getImgContentType() {
        return imgContentType;
    }

    public void setImgContentType(String imgContentType) {
        this.imgContentType = imgContentType;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }
@Action(value = "login",results = {
        @Result(name = "success", type = "redirect", location = "/fistpage2.html"),
        @Result(name = "false", type = "redirect", location = "/index.html")
})
    public String login() {
//        System.out.println((String) session.getAttribute("request"));
        try {
            User sample = userService.login(user.getId(), request.getParameter("method"));
            if (sample.getPassword().trim().equals(user.getPassword().trim())) {
                session.setAttribute("user", sample);
                return SUCCESS;
            } else
                return FALSE;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return FALSE;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return FALSE;
        }
    }

    public String rigister() throws IOException {
        userService.rigister(user, img, ServletActionContext.getServletContext().getRealPath("/img/portait"));
        return SUCCESS;
    }

    public String off_line() {
        session.removeAttribute("user");
        session.invalidate();
        return SUCCESS;
    }

    public String buy() throws IOException, AlipayApiException, WriterException {
        Orders order = new Orders();
        Goods goods = new Goods();
        goods.setId(request.getParameter("goodid"));
        order.setPaymethod(request.getParameter("paymethod"));
        order.setNunber(1);
        order.setStatu("未完成");
        order.setPrice(Integer.valueOf(request.getParameter("price")));
        user = (User) service.read(User.class.getName(), user.getId());
        goods = (Goods) service.read(Goods.class.getName(), goods.getId());
        order.setBuyr(user);
        order.setGood(goods);
        order.setDate(new Date());
        order.setId(OrderKeyGenreater.getkey());
        if (order.getPaymethod().equals("当面支付")) {
            goods.setTimes(goods.getTimes() - 1);
            service.save(order);
            service.update(goods);
            return "In person";
        } else {
            BzContent content=new BzContent()
                    .setGoods_detail(new GoodsDetails(goods))
                    .setOut_trade_no(OrderKeyGenreater.getkey())
                    .setSubject(goods.getName())
                    .setTotal_amount(order.getPrice())
                    .setTimeout_express("3m");
            String QrCode=alipayService.ForUserQrCode(content,user.getAuthToken());
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            alipayService.QREncode(QrCode,outputStream);
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            goods.setTimes(goods.getTimes() - 1);
            service.save(order);
            service.update(goods);
            return SUCCESS;
        }
    }

    public String test() throws UnsupportedEncodingException {
        String status;
        if (session.getAttribute("user") == null) status = "offline";
        else {
            User user = (User) session.getAttribute("user");
            status = "{\"username\":\"" + user.getNickname() + "\",\"uid\":\"" + user.getId() + "\",\"img\":\"" + user.getImglocation() + "\"}";
        }
        inputStream = new ByteArrayInputStream(status.getBytes("utf-8"));
        return SUCCESS;
    }

    public String review() throws IOException {
        String realpath = ServletActionContext.getServletContext().getRealPath("/review/users");
        userService.review(realpath, request.getParameter("review"), ((User) session.getAttribute("user")).getId());
        return SUCCESS;
    }

    public String appendreview() throws IOException {
        String realpath = ServletActionContext.getServletContext().getRealPath("/review/users");
        userService.appendreview(realpath, request.getParameter("review"), ((User) session.getAttribute("user")).getId());
        return SUCCESS;
    }

    public String reviewlist() throws UnsupportedEncodingException {
        String realpath = ServletActionContext.getServletContext().getRealPath("/review/users");
        ArrayList<String> list = userService.reviewlist(((User) session.getAttribute("user")).getId(), realpath);
        inputStream = new ByteArrayInputStream(gson.toJson(list).getBytes("utf-8"));
        return SUCCESS;
    }

    public String usermessage() throws UnsupportedEncodingException {
        user = userService.usermessage(user.getId());
        inputStream = new ByteArrayInputStream(gson.toJson(user).getBytes("utf-8"));
        return SUCCESS;
    }

    @Override
    public User getModel() {
        return user;
    }
}
