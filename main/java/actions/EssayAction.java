package actions;

import ActionExtension.MoreResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import daomain.Essay;
import daomain.User;
import org.apache.http.HttpRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.WithRealpath;
import service.EssayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class EssayAction extends ActionSupport implements ModelDriven<Essay>, MoreResult {
    private String realpath=ServletActionContext.getServletContext().getRealPath("/essay/");

    private Essay essay=new Essay();

    private WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());

    private EssayService essayService= (EssayService) wac.getBean("EssayService");

    HttpServletRequest request=ServletActionContext.getRequest();

    HttpSession session=request.getSession();

    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    private InputStream inputStream;

    @Override
    public Essay getModel() {
        return essay;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String writeessay() throws UnsupportedEncodingException {
        String content=request.getParameter("content");
        System.out.println(content);
        essay.setUser((User)session.getAttribute("user"));
        String result=essayService.Save(essay,content,realpath);
        if (result.equals("success"))
            inputStream=new ByteArrayInputStream("上传成功".getBytes("utf-8"));
        else
            inputStream=new ByteArrayInputStream("上传失败".getBytes("utf-8"));
        return SUCCESS;
    }

    public String ShowEssays() throws UnsupportedEncodingException {
        int firstResult=Integer.valueOf(request.getParameter("firstResult"));
        inputStream=new ByteArrayInputStream(gson.toJson(essayService.ShowEssays(firstResult)).getBytes("utf-8"));
        return SUCCESS;
    }

    public String DeleteEssay(){
        if(essayService.DeleteEssay(essay,realpath).equals("success"))
            return SUCCESS;
        else
            return FAILURE;
    }

    public String SelfEssays() throws UnsupportedEncodingException {
        String id=request.getParameter("id");
        int firstResult=Integer.valueOf(request.getParameter("firstResult"));
        inputStream=new ByteArrayInputStream(gson.toJson(new WithRealpath(essayService.getEssays(id,firstResult),realpath)).getBytes("utf-8"));
        return SUCCESS;
    }
}
