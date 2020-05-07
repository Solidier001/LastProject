package actions;

import ActionExtension.MoreResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Goods;
import daomain.Review;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.MessaeforTable;
import pojo.ReviewList;
import service.GoodsService;
import service.OrderService;
import service.ReviewService;
import util.OrmService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class GoodAction extends ActionSupport implements ModelDriven<Goods>, MoreResult {
    private InputStream inputStream;
    private File[] image;
    private String[] imageFileName;
    private String[] imageContentType;
    private Goods good = new Goods();
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpSession session = request.getSession();
    private WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    private OrmService service = (OrmService) wac.getBean("OrmService");
    private GoodsService goodsService = (GoodsService) wac.getBean("GoodsService");
    private OrderService orderService = (OrderService) wac.getBean("OrderService");
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    @Resource
    private ReviewService reviewService;
    public InputStream getInputStream() {
        return inputStream;
    }

    public File[] getImage() {
        return image;
    }

    public void setImage(File[] image) {
        this.image = image;
    }

    public String[] getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String[] imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String[] getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String[] imageContentType) {
        this.imageContentType = imageContentType;
    }

    @Override
    public Goods getModel() {
        return good;
    }

    public String upGood() {
        try {
            ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
            String realpath = ServletActionContext.getServletContext().getRealPath("/img/goods");
            User user=(User) session.getAttribute("user");
            goodsService.Save(user,image,realpath,good);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String readGood(){
        ArrayList<String> imglist=new ArrayList<>();
        Goods sample = (Goods) service.read(Goods.class.getName(), good.getId());
        String realpath = ServletActionContext.getServletContext().getRealPath(sample.getPictures());
        File dir=new File(realpath);
        String imgname;
        for (File img:dir.listFiles()){
            imgname=img.getName();
            if (!imgname.contains("min")){
                imglist.add(sample.getPictures()+'/'+imgname);
            }
        }
        request.setAttribute("sample", sample);
        request.setAttribute("pictures",imglist);
        return SUCCESS;
    }

    public String secrch() throws UnsupportedEncodingException {
        String index=request.getParameter("firstpage");
        String result=gson.toJson(goodsService.findGoodsByname(good.getName(),Integer.valueOf(index)));
        inputStream=new ByteArrayInputStream(result.getBytes("utf-8"));
        return SUCCESS;
    }

    public String makeorder(){
        good=(Goods) service.read(Goods.class.getName(),good.getId());
        User user= good.getUser();
        request.setAttribute("user",user);
        request.setAttribute("good",good);
        return SUCCESS;
    }

    public String delsteagood() throws UnsupportedEncodingException {
        String state;
        try {
            Goods good= (Goods) service.read(Goods.class.getName(),this.good.getId());
            FileUtils.deleteDirectory(new File(ServletActionContext.getServletContext().getRealPath(good.getPictures())));
            service.delate(good);
            state="删除成功";
        }catch (Exception e){
            state="删除失败";
        }
        inputStream=new ByteArrayInputStream(state.getBytes("utf-8"));
        return SUCCESS;
    }

    public String updategood() {
        String result=goodsService.udategood(good);
        request.setAttribute("good",goodsService.OneGoods(good.getId()));
        return result;
    }

    public String review() throws IOException {
        String reviewstr=request.getParameter("review");
        User uer=(User)session.getAttribute("user");
        String id=request.getParameter("id");
        String location=goodsService.getReiew(id);
        Review review=new Review("Good");
        String realpath= ServletActionContext.getServletContext().getRealPath(location);
        String result=reviewService.Save(review,uer,reviewstr,location,realpath,null);
        inputStream=new ByteArrayInputStream(result.getBytes("utf-8"));
        return SUCCESS;
    }

    /*public String appendreview() throws IOException {
        String realpath=ServletActionContext.getServletContext().getRealPath("/");
        goodsService.appendreview(realpath,request.getParameter("review"),request.getParameter("id"));
        inputStream=new ByteArrayInputStream("成功".getBytes("utf-8"));
        return SUCCESS;
    }*/

    public String reviewlist() throws UnsupportedEncodingException {
        String path=goodsService.getReiew(request.getParameter("id"));
        String realpath=ServletActionContext.getServletContext().getRealPath(path);
        ReviewList list= goodsService.reviewlist(realpath,request.getParameter("id"));
        if(list.isEmpty())
            inputStream=new ByteArrayInputStream("null".getBytes("utf-8"));
        else inputStream=new ByteArrayInputStream(gson.toJson(list).getBytes("utf-8"));
        return SUCCESS;
    }

    public String GoodofUser() throws UnsupportedEncodingException {
        int firstindex=Integer.valueOf(request.getParameter("firstindex"));
        int limt=Integer.valueOf(request.getParameter("limt"));
        User user= (User) session.getAttribute("user");
        MessaeforTable result=new MessaeforTable(goodsService.ResdAllGoodsbuyuser(user,firstindex,limt),goodsService.getAllNumber(),0,"");
        String allgood=gson.toJson(result);
        inputStream= new  ByteArrayInputStream(allgood.getBytes("utf-8"));
        return SUCCESS;
    }
}