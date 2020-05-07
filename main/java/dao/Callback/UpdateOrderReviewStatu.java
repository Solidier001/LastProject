package dao.Callback;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UpdateOrderReviewStatu implements HibernateCallback<String> {
    private static final String hql="update Orders set hasReview = 'not null' where id=:id";
    private String id;

    public UpdateOrderReviewStatu(String id) {
        this.id = id;
    }

    @Override
    public String doInHibernate(Session session) throws HibernateException {
        Query query=session.createQuery(hql);
        query.setParameter("id",id);
        query.executeUpdate();
        return "success";
    }
}
