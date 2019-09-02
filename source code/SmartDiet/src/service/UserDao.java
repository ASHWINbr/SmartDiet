package service;

import java.util.List;
import hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import model.UserDietPojo;
import model.UserPojo;

public class UserDao implements MethodInterface {

	@Override
	public boolean saveUser(UserPojo userPojo) {
		boolean result = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			Criteria cr = session.createCriteria(UserPojo.class);
			cr.add(Restrictions.eq("email", userPojo.getEmail()));
			List<UserPojo> list = cr.list();
			if (list.isEmpty()) {
				session.save(userPojo);
				result = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public String loginUser(String email, String password) {
		String result = "no";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			Criteria cr = session.createCriteria(UserPojo.class);
			cr.add(Restrictions.eq("email", email));
			cr.add(Restrictions.eq("password", password));
			List<UserPojo> list = cr.list();
			if (!list.isEmpty()) {
				result = "yes";
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean saveDietPlan(UserDietPojo userDietPojo) {
		boolean result = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			Criteria cr = session.createCriteria(UserDietPojo.class);
			cr.add(Restrictions.eq("email", userDietPojo.getEmail()));
			List<UserDietPojo> list = cr.list();
			if (list.isEmpty()) {
				session.save(userDietPojo);
				result=true;
			} else {
				UserDietPojo diet = list.get(0);
				session.delete(diet);
				session.save(userDietPojo);
				result = true;
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<UserPojo> getUserDetails(String email) {
		List<UserPojo> list = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			Criteria cr = session.createCriteria(UserPojo.class);
			cr.add(Restrictions.eq("email", email));
			list = cr.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserDietPojo> getDietPlan(String email) {
		List<UserDietPojo> list = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			Criteria cr = session.createCriteria(UserDietPojo.class);
			cr.add(Restrictions.eq("email", email));
			list = cr.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
