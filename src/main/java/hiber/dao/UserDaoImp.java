package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUser(String carModel, int carSeries) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User as user where user.car.model = :model and user.car.series = :series");
      query.setParameter("model", carModel);
      query.setParameter("series", carSeries);
      List<User> usersList = query.getResultList();
      return CollectionUtils.isEmpty(usersList) ? null : usersList.get(0);
   }

}
