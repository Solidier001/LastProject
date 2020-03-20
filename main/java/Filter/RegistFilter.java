package Filter;

import daomain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RegistFilter")
public class RegistFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpSession session=request.getSession();
        String OAuthCode=request.getParameter("OAuthCode");
        try {
            if (OAuthCode.equals((String) session.getAttribute("OAuthCode"))){
                session.removeAttribute("OAuthCode");
                chain.doFilter(req, resp);
            }
            else
                response.sendRedirect("/register");
        }catch (NullPointerException e){
            response.sendRedirect("/register");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
