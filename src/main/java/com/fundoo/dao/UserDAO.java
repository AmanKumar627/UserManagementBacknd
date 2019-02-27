package com.fundoo.dao;






import com.fundoo.dto.UserDto;
import com.fundoo.models.User;

public interface UserDAO {

//	void save(User user); 

	public User create(User user);

	
	//public List<User> listUsers();
	
	public boolean read(String emailId,String password);
	
//	
	public String getPassword(String emailId);
	
	
	public  String registerUser(String  emailId);
	public User saveUser(User user);
	public User getUser(UserDto userDto);
	public User getUserById(int id);
	public User checkemail(String email);
	public User checkUserByUsername(String userName);
}