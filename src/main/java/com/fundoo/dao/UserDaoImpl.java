
package com.fundoo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fundoo.dto.UserDto;
import com.fundoo.models.User;

@Repository
public  class UserDaoImpl implements UserDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public User create(User user) {
		factory.getCurrentSession().save(user);
		return user;

	}

//		@Override		// 
//		public List<User> listUsers() {
//	    return (List<User>) factory.getCurrentSession().createCriteria(User.class).list();
//			 }

		@SuppressWarnings("rawtypes")
		@Override
		public String getPassword(String emailId) {
			// TODO Auto-generated method stub
			
			Query query =factory.getCurrentSession().createQuery("select password from User where Email_Id=:emailId");
			System.out.println(query);
			query.setParameter("emailId",emailId);
			System.out.println("check1");
			
			String user =  (String) query.uniqueResult();
			System.out.println(user);	    
//	    	
			return emailId;
		}

		    
			@Override
			public boolean read(String userName, String password) {
				boolean userFound=false;
				System.out.println("in dao");
				
				@SuppressWarnings("unchecked")
				Query<User> query = factory.getCurrentSession().createQuery("from User where Username=:userName and Password=:password");
				System.out.println(query);
		    	query.setParameter("userName",userName);
		    	query.setParameter("password",password);
		    	System.out.println("check");
		    	
				
				List<User> list = query.list();
		    	if(list != null && list.size()>0)
		    	{
		    		System.out.println(list);
		    		userFound=true;
		    		//userFound = true;
		    		System.out.println("check2");
		    	}
		    	else
		    	{
		    	  System.out.println("check3");
				  return userFound=false;
		    	}
		    	return userFound;
		    }

			@SuppressWarnings("rawtypes")
			@Override
			public String registerUser(String emailId) {
				// TODO Auto-generated method stub
				Query query = factory.getCurrentSession().createQuery("select userName and password from User where  Email_Id:emailId");
			
				query.setParameter("emailId",emailId);
				
				return emailId;
				
				
			}

			@Override
			public User saveUser(User user) {
				// TODO Auto-generated method stub
			   factory.getCurrentSession().save(user);
			   System.out.println(user);
				return user;
			}

			@Override
			public User getUser(UserDto userDto) {
				// TODO Auto-generated method stub
				System.out.println("dao" + userDto.getUserName());
				@SuppressWarnings("rawtypes")
				Query query = factory.getCurrentSession().createQuery("FROM  User  where userName= :userName");
				System.out.println(query);
				query.setParameter("userName", userDto.getUserName());
			
				System.out.println((boolean) query.list().isEmpty());
				if ((boolean) query.list().isEmpty()) {
					return null;
				} else {
					return (User) query.list().get(0);
				}
			}

			@Override
			public User getUserById(int id) {
				// TODO Auto-generated method stub
				User user = factory.getCurrentSession().get(User.class, id);
				System.out.println(user);
				return null;
			}

			@SuppressWarnings("rawtypes")
			@Override
			public User checkemail(String email) {
				// TODO Auto-generated method stub
				Query query = factory.getCurrentSession().createQuery("FROM User  where email= :email");
				query.setParameter("email",email );
				
				if ((boolean) query.list().isEmpty()) {
					return null;
				} else {
					return (User) query.list().get(0);
				}	}
				
				
				
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public User checkUserByUsername(String userName) {

				try {

					Query query = factory.getCurrentSession().createQuery("from User where Username =:userName");
					query.setParameter("userName", userName);
//					List<User> userList = query.list();
					System.out.println("check");
					User user = (User) query.uniqueResult();
					return user;

				} catch (HibernateException e) {
					e.printStackTrace();
				}
				return null;
			}

			
				
				
			
//			@Override
//			public User checkemail(String email) {
//				Query query = factory.getCurrentSession().createQuery("FROM User  where email= :email");
//				query.setParameter("email",email );
//				
//				if ((boolean) query.list().isEmpty()) {
//					return null;
//				} else {
//					return (User) query.list().get(0);
//				}	}
				
				
				
			
			
			
		

}

			
			


