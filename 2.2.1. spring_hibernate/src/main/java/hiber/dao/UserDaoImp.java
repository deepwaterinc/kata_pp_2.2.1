package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
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
   public void clearTable() {
      Query query=sessionFactory.getCurrentSession().createQuery("delete User");
      query.executeUpdate();
   }
   @Override
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("FROM User where car.model = :model and car.series = :series");
      query.setParameter("model", model).setParameter("series", series);
      return (User) query.getSingleResult();
   }
}
