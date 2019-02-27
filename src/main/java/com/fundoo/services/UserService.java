package com.fundoo.services;



import com.fundoo.dto.UserDto;
import com.fundoo.models.User;


public interface UserService {

	//void save(UserDTO userDTO);
   public User create(User user);
   public String read(User user);
	
   public String forgetPassword(String emailId,User user);
   
   public String  RegisterUser(String emailId);
   public User saveUser(User user);
   
   public String sendmail(User user);
   
   public User verifyUser(UserDto userDto) ;
   public User getUser(String token);
   public UserDto loginUser(User user);
}
	
	
