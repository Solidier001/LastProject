package util;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent sre)  {
        ((HttpServletRequest) sre.getServletRequest()).getSession();

    }
    public RequestListener() {
    }

    public void requestDestroyed(ServletRequestEvent arg0)  {
    }
}
