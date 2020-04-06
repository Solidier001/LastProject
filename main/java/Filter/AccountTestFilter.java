package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "AccountTestFilter")
public class AccountTestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        String url = request.getRequestURI();
        url = url.substring(url.lastIndexOf('/') + 1).replaceAll("\\?.*","");
        switch (url) {
            case "pay.jsp":
                if (session.getAttribute("user") == null) {
                    session.setAttribute("request", request.getHeader("Referer"));
                    response.sendRedirect("/index.html");
                } else {
                    chain.doFilter(req, resp);
                }
                break;
            case "fistpage2.html":
                String nav = (String) session.getAttribute("request");
                if (nav != null&&session.getAttribute("user") != null) {
                    response.sendRedirect(nav);
                    session.removeAttribute("request");
                    nav = null;
                } else {
                    chain.doFilter(req, resp);
                }
                break;
            case "upgoods.html":
                if (session.getAttribute("user") == null) {
                    session.setAttribute("request",'/'+url);
                    response.sendRedirect("/index.html");
                } else {
                    chain.doFilter(req, resp);
                }
                break;
            case "essayEditor.html":
                if (session.getAttribute("user") == null) {
                    session.setAttribute("request",'/'+"essayEditor.html");
                    response.sendRedirect("/index.html");
                } else {
                    chain.doFilter(req, resp);
                }
                break;
            default:
                    chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}