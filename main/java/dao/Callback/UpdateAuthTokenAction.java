package dao.Callback;

import daomain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import pojo.AlipayToken;

public class UpdateAuthTokenAction implements HibernateCallback<String> {
    private AlipayToken token;
    private User user;

    private static final String hql="update User set authToken=:authToken,refreshToken=:refreshToken where User=:user";

    public UpdateAuthTokenAction(AlipayToken token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public String doInHibernate(Session session) throws HibernateException {
        try {
            Query query=session.createQuery(hql);
            query.setParameter("authToken",token.getAuthToken());
            query.setParameter("refreshToken",token.getRefreshToken());
            query.setParameter("user",user);
            query.executeUpdate();
            return "true";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
