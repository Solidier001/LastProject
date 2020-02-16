package actions;

import ActionExtension.MoreResult;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Goods;
import daomain.User;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;
import util.OrmService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class GoodAction extends ActionSupport implements ModelDriven<Goods>, MoreResult {
    private File[] image;
    private String[] imageFileName;
    private String[] imageContentType;
    private Goods good = new Goods();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    OrmService service = (OrmService) wac.getBean("OrmService");
    GoodsService goodsService = (GoodsService) wac.getBean("GoodsService");
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
            if (image != null) {
                File savedir = new File(realpath, UUID.randomUUID().toString().replace("-",""));
                if (!savedir.getParentFile().exists())
                    savedir.getParentFile().mkdirs();
                String picturedir1=savedir.getPath();
                picturedir1=picturedir1.substring(picturedir1.indexOf("\\img")).replace("\\","/");
                for (int i = 0; i < image.length; i++) {
                    File savefile = new File(savedir, UUID.randomUUID().toString().replace("-","")+".jpg");
                    FileUtils.copyFile(image[i], savefile);
                }
                good.setTimes(0);
                good.setPictures(picturedir1);
                User user=(User) session.getAttribute("user");
                good.setUser(user);
                service.save(good);
            }
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
    public String secrch(){
        ArrayList<Goods> list=goodsService.findGoodsByname(good.getName());
        request.setAttribute("allgoods",list);
        return SUCCESS;
    }
    public String makeorder(){
        good=(Goods) service.read(Goods.class.getName(),good.getId());
        User user= good.getUser();
        request.setAttribute("user",user);
        request.setAttribute("good",good);
        return SUCCESS;
    }
}