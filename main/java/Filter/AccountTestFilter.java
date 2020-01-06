package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        String url=request.getRequestURI();
        System.out.println(url);
        String rexg="\\.css$|\\.js$|.*image\\.action\\.*|/index.jsp";
        Pattern pattern = Pattern.compile(rexg);
        Matcher matcher=pattern.matcher(url);
        if (!matcher.matches()) {
            if (session.getAttribute("user") == null)
                response.sendRedirect("/index.jsp");
            else
                chain.doFilter(req, resp);
        }
        else
            chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
