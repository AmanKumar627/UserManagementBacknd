package com.fundoo.services;









import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.fundoo.dao.UserDAO;
import com.fundoo.dto.UserDto;
import com.fundoo.models.User;
import com.fundoo.utility.UserToken;
import com.fundoo.utility.Utility;


@Service
@Transactional
public  class UserServiceImpl implements UserService {

	@Autowired
   private  UserDAO userDao;
	
//	@Override
//	public void save(UserDTO userDTO) {
//		User user = new User(userDTO);
//		userDAO.save(user);
//	}

	@Autowired
	String key;
	
	@Override
	public User create (User user) {
		
	
		String encryptedpassword=Utility.encrypt(user.getPassword(), key);
		user.setPassword(encryptedpassword);
		userDao.create(user);
		return user;
		
	}



	@Override
	public String read(User user) {
		 //String encrypted=Utility.encrypt(user.getPassword(),key);
		String UserName=user.getUserName();
		String password=Utility.encrypt(user.getPassword(), key);
        
       
		if(userDao.read(UserName, password))
		{
			return "done";
		}
		else
		{
		return "0";
	}
	}


	@Override
	public String forgetPassword(String emailId,User user) {
		
		

	
	String userpassword= userDao.getPassword(emailId);
//     Utility.sendEmail(user, userpassword);
	return emailId;
		
//		System.out.println(emailId);
//		System.out.println("Encrypt" +password);
//	     String decryptedpassword=Utility.decrypt(password, key);
//	     System.out.println("Decode " +decryptedpassword);
//		 Utility.sendEmail(emailId,password);
//		return emailId;
	}



	@Override
	public String RegisterUser(String emailId) {
		// TODO Auto-generated method stub
		userDao.registerUser(emailId);
	
		
		return emailId;
	}



	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
         String username=user.getUserName();
		Utility.sendEmail(user,password,username);
		userDao.saveUser(user);
		return user;
	}



	



	@Override
	public String sendmail(User user) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
//		Utility.sendEmail(user, password);
		
		return "email send";
	}



	@Override
	public User verifyUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=userDao.getUser(userDto);
		System.out.println(user);
		
		return user;
	}



	@Override
	public User getUser(String token) {
		// TODO Auto-generated method stub
		try {
			int id = UserToken.tokenVerify(token);

			return userDao.getUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
		
		
	public UserDto loginUser(User user) {

//		user.setPassword(EncryptDecrypt.encrypt(user.getPassword(), key));
		User tempUser = userDao.checkUserByUsername(user.getUserName());

		if (tempUser != null) {

			if (tempUser.getUserName().equals(user.getUserName())
					&& tempUser.getPassword().equals(user.getPassword())) {
                            System.out.println("aman");
				ModelMapper mapper = new ModelMapper();
				UserDto userDTO = mapper.map(tempUser, UserDto.class);

				return userDTO;
			} else {
				return null;
			}
		} else {
			return null;
		}
	

	
	
	
	
	
	}

	
}

