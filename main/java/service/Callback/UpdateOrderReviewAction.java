package service.Callback;

import daomain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UpdateOrderReviewAction implements HibernateCallback<String> {
    private User buyr;
    private String reviewe;
    private String id;

    public UpdateOrderReviewAction(User buyr, String reviewe,String id) {
        this.buyr = buyr;
        this.reviewe = reviewe;
        this.id=id;
    }

    @Override
    public String doInHibernate(Session session) throws HibernateException {
        String hql ="update Orders o set o.review=:review where o.buyr=:buyr and o.id=:id";
        Query query=session.createQuery(hql);
        query.setParameter("review",reviewe);
        query.setParameter("buyr",buyr);
        query.setParameter("id",id);
        query.executeUpdate();
        session.close();
        return "success";
    }
}
