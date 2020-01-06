package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Goods;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import util.OrmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

public class GoodAction extends ActionSupport implements ModelDriven<Goods> {
    private String id;
    private File[] image;
    private String[] imageFileName;
    private String[] imageContentType;
    private Goods good = new Goods();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
    OrmService service = (OrmService) wac.getBean("OrmService");

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                service.save(good);
            }
        } catch (IOException e) {

            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    public String readGood(){
        id=request.getParameter("id");
        Goods sample = (Goods) service.read(Goods.class.getName(), id);

        request.setAttribute("sample", sample);
        return SUCCESS;
    }
}
